package application.service;

import application.domain.models.Buyer;
import application.domain.models.Invoice;
import application.domain.models.Order;
import application.domain.models.PhysicalProduct;
import application.domain.models.Refund;
import application.domain.models.RefundStatus;
import application.domain.models.ReturnRequest;
import application.domain.models.Seller;
import application.domain.models.SellerWarehouse;
import application.domain.models.Shipment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration test that exercises every use case exposed by the
 * service layer against the REAL MySQL database (nexusmarket_db),
 * mirroring the business flow from the functional specification
 * (Section 6.1): onboarding -> catalog -> inventory -> publication ->
 * purchase -> transaction -> logistics -> closure.
 *
 * @Transactional + @Rollback make the whole test run inside one
 * transaction that is rolled back at the end, so running this test
 * repeatedly never leaves leftover data in the database.
 */
@SpringBootTest
@Transactional
@Rollback
class ServiceLayerFlowTest {

    @Autowired
    private SellerService sellerService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AfterSalesService afterSalesService;
    @Autowired
    private ReportService reportService;

    @Test
    void runsFullUseCaseFlow() {
        Seller seller = sellerService.registerSeller("Pedro Seller", "pedro@gmail.com", "124356789");
        Buyer buyer = buyerService.registerBuyer("Juan Buyer", "juan@gmail.com", "132456789");

        PhysicalProduct product = catalogService.registerPhysicalProduct(
                seller.getIdentifier(), "Computer", new BigDecimal("450"), 2.5);

        SellerWarehouse warehouse = warehouseService.createSellerWarehouse(
                seller.getIdentifier(), "Main Warehouse", "Medellin");
        warehouseService.registerInventory(warehouse.getIdentifier(), product.getIdentifier(), 10);

        cartService.addProduct(buyer.getIdentifier(), product.getIdentifier(), 2);
        assertEquals(0, cartService.calculateTotal(buyer.getIdentifier()).compareTo(new BigDecimal("900")));

        Order order = orderService.checkout(buyer.getIdentifier());
        assertTrue(warehouseService.reserveStock(warehouse.getIdentifier(), product.getIdentifier(), 2));

        orderService.payOrder(order.getIdentifier());
        Shipment shipment = orderService.dispatchOrder(order.getIdentifier(), "National Carrier");
        orderService.deliverOrder(order.getIdentifier());
        orderService.completeOrder(order.getIdentifier());

        Invoice invoice = orderService.issueInvoice(order.getIdentifier(), "INV-001");
        assertEquals(0, invoice.getTotal().compareTo(new BigDecimal("900")));

        ReturnRequest returnRequest = afterSalesService.requestReturn(order.getIdentifier(), "Changed my mind");
        afterSalesService.approveReturn(returnRequest.getId());
        Refund refund = afterSalesService.processRefund(returnRequest.getId());
        assertEquals(RefundStatus.PROCESSED, refund.getStatus());

        assertTrue(reportService.generateReport("General Sales Report"));
        assertEquals("National Carrier", shipment.getCarrier());
    }
}
