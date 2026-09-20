package application.service;

import application.domain.models.Order;
import application.domain.models.Refund;
import application.domain.models.ReturnRequest;
import application.domain.models.ReturnStatus;
import application.exception.BusinessRuleException;
import org.springframework.stereotype.Service;

@Service
public class AfterSalesServiceImpl implements AfterSalesService {

    private final OrderService orderService;

    public AfterSalesServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ReturnRequest requestReturn(Long orderId, String reason) {
        Order order = orderService.findOrder(orderId);
        return new ReturnRequest(order, reason);
    }

    @Override
    public ReturnRequest approveReturn(ReturnRequest returnRequest) {
        returnRequest.approve();
        return returnRequest;
    }

    @Override
    public Refund processRefund(ReturnRequest returnRequest) {
        if (returnRequest.getStatus() != ReturnStatus.APPROVED) {
            throw new BusinessRuleException("The return request must be approved before refunding.");
        }
        Refund refund = new Refund(returnRequest, returnRequest.getOrder().calculateTotal());
        refund.process();
        return refund;
    }
}
