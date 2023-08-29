package officeLunch.Office.Lunch.request;

import lombok.Data;

@Data
public class BkashPaymentRequest {
    private String requestId;
    private String txnId;
    private String mobileNo;
}
