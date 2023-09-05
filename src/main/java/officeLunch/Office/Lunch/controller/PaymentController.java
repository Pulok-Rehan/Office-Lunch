package officeLunch.Office.Lunch.controller;

import officeLunch.Office.Lunch.request.BkashPaymentRequest;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.PackageService;
import officeLunch.Office.Lunch.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(path = "/makePayment")
    public CommonResponse makePayment(@RequestBody BkashPaymentRequest bkashPaymentRequest){
        return paymentService.makePayment(bkashPaymentRequest);
    }
}
