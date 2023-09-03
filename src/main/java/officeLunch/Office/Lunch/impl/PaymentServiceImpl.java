package officeLunch.Office.Lunch.impl;

import officeLunch.Office.Lunch.model.Order;
import officeLunch.Office.Lunch.repository.OrderRepository;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class PaymentServiceImpl implements PaymentService {
    @Value("url.base-url")
    private String baseUrl;
    private final RestTemplate restTemplate;
    private final OrderRepository orderRepository;

    public PaymentServiceImpl(String baseUrl, RestTemplate restTemplate, OrderRepository orderRepository) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
        this.orderRepository = orderRepository;
    }

    @Override
    public CommonResponse makePayment(String mobileNo, long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            return this.universalResponseForFailedPayment();
        }
        double paymentAmount = order.get().getTotalAmount();
//        BkashPaymentRequest bkashPaymentRequest = new BkashPaymentRequest();
//        String url = baseUrl;
//        HttpEntity<BkashPaymentRequest> bkashPaymentRequestHttpEntity =
//                new HttpEntity<>(bkashPaymentRequest, getHeaders());
//        ResponseEntity<BkashPaymentResponse> bkashPaymentResponseResponseEntity =
//                restTemplate.exchange(url, HttpMethod.POST, bkashPaymentRequest, BkashPaymentResponse.class);
        return new CommonResponse();
    }
    private CommonResponse universalResponseForFailedPayment(){
        return CommonResponse.builder()
                .hasError(true)
                .message("Could not make payment")
                .content(null).build();
    }
}
