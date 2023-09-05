package officeLunch.Office.Lunch.request;

import lombok.Data;

@Data
public class BkashPaymentRequest {
    private String txnId;
    private String referenceId;
    private String mobileNo;
    private long orderId;
    private double amount;
}
