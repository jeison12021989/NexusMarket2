package application.service;

import application.domain.models.Refund;
import application.domain.models.ReturnRequest;

/**
 * After-sales flow: returns and refunds (OBJ-11).
 */
public interface AfterSalesService {

    ReturnRequest requestReturn(Long orderId, String reason);

    ReturnRequest approveReturn(ReturnRequest returnRequest);

    Refund processRefund(ReturnRequest returnRequest);
}
