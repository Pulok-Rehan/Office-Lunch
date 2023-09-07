package officeLunch.Office.Lunch.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import officeLunch.Office.Lunch.model.Order;
import officeLunch.Office.Lunch.repository.OrderRepository;
import officeLunch.Office.Lunch.request.BkashPaymentRequest;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import util.Modules;
import util.UtilResponse;
import util.UtilService;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Value("${url.base-url}")
    private String baseUrl;
    private final OrderRepository orderRepository;
    private final UtilService utilService;

    public PaymentServiceImpl(OrderRepository orderRepository, UtilService utilService) {
        this.orderRepository = orderRepository;
        this.utilService = utilService;
    }

    @Override
    public CommonResponse makePayment(BkashPaymentRequest bkashPaymentRequest) {
        Optional<Order> order = orderRepository.findById(bkashPaymentRequest.getOrderId());
        if (order.isEmpty()) {
            return UtilService.universalFailedResponse();
        }
        double paymentAmount = order.get().getTotalAmount();
        UtilResponse utilResponse = utilService.generateTxnAndRefId(Modules.TXNID, Modules.TXNID);
        bkashPaymentRequest.setAmount(paymentAmount);
        bkashPaymentRequest.setReferenceId(utilResponse.getReferenceId());
        bkashPaymentRequest.setTxnId(utilResponse.getTxnId());

        return utilService.callApi(baseUrl, "makePayment", bkashPaymentRequest);
    }
}
