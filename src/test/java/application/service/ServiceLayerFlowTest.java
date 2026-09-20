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
import application.repository.BuyerRepository;
import application.repository.OrderRepository;
import application.repository.ProductRepository;
import application.repository.SellerRepository;
import application.repository.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Exercises every use case exposed by the service layer, mirroring the
 * business flow from the functional specification (Section 6.1):
 * onboarding -> catalog -> inventory -> publication -> purchase ->
 * transaction -> logistics -> closure.
 */
class ServiceLayerFlowTest {

    private SellerService sellerService;
    private BuyerService buyerService;
    private CatalogService catalogService;
    private WarehouseService warehouseService;
    private CartService cartService;
    private OrderService orderService;
    private AfterSalesService afterSalesService;
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        sellerService = new SellerServiceImpl(new SellerRepository());
        buyerService = new BuyerServiceImpl(new BuyerRepository());
        ProductRepository productRepository = new ProductRepository();
        catalogService = new CatalogServiceImpl(productRepository, sellerService);
        warehouseService = new WarehouseServiceImpl(new WarehouseRepository(), productRepository, sellerService);
        cartService = new CartServiceImpl(buyerService, productRepository);
        orderService = new OrderServiceImpl(new OrderRepository(), buyerService);
        afterSalesService = new AfterSalesServiceImpl(orderService);
        reportService = new ReportServiceImpl();
    }

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
        assertEquals(new BigDecimal("900"), cartService.calculateTotal(buyer.getIdentifier()));

        Order order = orderService.checkout(buyer.getIdentifier());
        assertTrue(warehouseService.reserveStock(warehouse.getIdentifier(), product.getIdentifier(), 2));

        orderService.payOrder(order.getIdentifier());
        Shipment shipment = orderService.dispatchOrder(order.getIdentifier(), "National Carrier");
        orderService.deliverOrder(order.getIdentifier());
        orderService.completeOrder(order.getIdentifier());

        Invoice invoice = orderService.issueInvoice(order.getIdentifier(), "INV-001");
        assertEquals(new BigDecimal("900"), invoice.getTotal());

        ReturnRequest returnRequest = afterSalesService.requestReturn(order.getIdentifier(), "Changed my mind");
        afterSalesService.approveReturn(returnRequest);
        Refund refund = afterSalesService.processRefund(returnRequest);
        assertEquals(RefundStatus.PROCESSED, refund.getStatus());

        assertTrue(reportService.generateReport("General Sales Report"));
        assertEquals("National Carrier", shipment.getCarrier());
    }
}
