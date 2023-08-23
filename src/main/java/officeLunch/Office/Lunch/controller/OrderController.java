package officeLunch.Office.Lunch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import officeLunch.Office.Lunch.model.LunchPackage;
import officeLunch.Office.Lunch.model.Order;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/orders")
    public CommonResponse getOrders() throws JsonProcessingException {
        return orderService.getAllOrders();
    }

    @PostMapping(path = "/orders")
    public CommonResponse createOrder(@RequestBody LunchPackage lunchPackage) throws JsonProcessingException {
        return orderService.createOrder(lunchPackage);
    }

    @PutMapping(path = "/orders")
    public CommonResponse updateOrder(@RequestBody Order order) throws JsonProcessingException {
        return orderService.editOrder(order);
    }

    @DeleteMapping(path = "/orders")
    public CommonResponse deleteOrder(@RequestBody long order_id){
        return orderService.deleteOrder(order_id);
    }

    @GetMapping(path = "/orders/{id}")
    public CommonResponse getorder(@RequestParam("id") long id) throws JsonProcessingException {
        return orderService.getOrder(id);
    }

}
