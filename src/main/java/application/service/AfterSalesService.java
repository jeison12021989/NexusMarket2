package application.service;

import application.domain.models.Refund;
import application.domain.models.ReturnRequest;

public interface AfterSalesService {

    ReturnRequest requestReturn(Long orderId, String reason);

    ReturnRequest approveReturn(Long returnRequestId);

    Refund processRefund(Long returnRequestId);
}
