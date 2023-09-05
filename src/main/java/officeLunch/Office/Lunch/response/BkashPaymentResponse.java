package officeLunch.Office.Lunch.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BkashPaymentResponse {
    private String code;
    private String txnId;
    private double amount;
    private String senderId;
}
