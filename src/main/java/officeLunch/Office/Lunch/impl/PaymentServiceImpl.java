package officeLunch.Office.Lunch.impl;

import officeLunch.Office.Lunch.request.BkashPaymentRequest;
import officeLunch.Office.Lunch.response.BkashPaymentResponse;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PaymentServiceImpl implements PaymentService {
    @Value("url.base-url")
    private String baseUrl;
    private final RestTemplate restTemplate;

    public PaymentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CommonResponse makePayment(String txnId, String mobileNo, double amount) {
//        BkashPaymentRequest bkashPaymentRequest = new BkashPaymentRequest();
//        String url = baseUrl;
//        HttpEntity<BkashPaymentRequest> bkashPaymentRequestHttpEntity =
//                new HttpEntity<>(bkashPaymentRequest, getHeaders());
//        ResponseEntity<BkashPaymentResponse> bkashPaymentResponseResponseEntity =
//                restTemplate.exchange(url, HttpMethod.POST, bkashPaymentRequest, BkashPaymentResponse.class);
        return new CommonResponse();



    }
}
