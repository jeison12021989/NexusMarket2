package application.service;

import application.domain.models.Order;
import application.domain.models.Refund;
import application.domain.models.ReturnRequest;
import application.domain.models.ReturnStatus;
import application.exception.BusinessRuleException;
import application.repository.RefundRepository;
import application.repository.ReturnRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AfterSalesServiceImpl implements AfterSalesService {

    private final OrderService orderService;
    private final ReturnRequestRepository returnRequestRepository;
    private final RefundRepository refundRepository;

    public AfterSalesServiceImpl(OrderService orderService,
                                  ReturnRequestRepository returnRequestRepository,
                                  RefundRepository refundRepository) {
        this.orderService = orderService;
        this.returnRequestRepository = returnRequestRepository;
        this.refundRepository = refundRepository;
    }

    @Override
    public ReturnRequest requestReturn(Long orderId, String reason) {
        Order order = orderService.findOrder(orderId);
        return returnRequestRepository.save(new ReturnRequest(order, reason));
    }

    @Override
    public ReturnRequest approveReturn(Long returnRequestId) {
        ReturnRequest returnRequest = findReturnRequest(returnRequestId);
        returnRequest.approve();
        return returnRequest;
    }

    @Override
    public Refund processRefund(Long returnRequestId) {
        ReturnRequest returnRequest = findReturnRequest(returnRequestId);
        if (returnRequest.getStatus() != ReturnStatus.APPROVED) {
            throw new BusinessRuleException("The return request must be approved before refunding.");
        }
        Refund refund = new Refund(returnRequest, returnRequest.getOrder().calculateTotal());
        refund.process();
        return refundRepository.save(refund);
    }

    private ReturnRequest findReturnRequest(Long returnRequestId) {
        return returnRequestRepository.findById(returnRequestId)
                .orElseThrow(() -> new BusinessRuleException("Return request " + returnRequestId + " does not exist."));
    }
}
