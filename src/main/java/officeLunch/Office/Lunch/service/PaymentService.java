package officeLunch.Office.Lunch.service;

import officeLunch.Office.Lunch.request.BkashPaymentRequest;
import officeLunch.Office.Lunch.response.CommonResponse;

public interface PaymentService {
    CommonResponse makePayment(BkashPaymentRequest bkashPaymentRequest);
}
