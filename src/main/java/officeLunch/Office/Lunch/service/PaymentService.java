package officeLunch.Office.Lunch.service;

import officeLunch.Office.Lunch.response.CommonResponse;

public interface PaymentService {
    CommonResponse makePayment(String txnId, String mobileNo, double amount);
}
