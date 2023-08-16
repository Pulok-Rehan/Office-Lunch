package officeLunch.Office.Lunch.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.dynalink.linker.LinkerServices;
import officeLunch.Office.Lunch.model.LunchPackage;
import officeLunch.Office.Lunch.model.Order;
import officeLunch.Office.Lunch.repository.OrderRepository;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public CommonResponse createOrder(LunchPackage lunchPackage) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        if(lunchPackage==null){
            return CommonResponse.builder()
                    .hasError(true)
                    .message("Could not create order").build();
        }
        else {
            Order newOrder = Order.builder()
                    .lunchPackage(lunchPackage)
                    .totalAmount(lunchPackage.getTotalPrice()).build();
            newOrder = orderRepository.save(newOrder);
            return CommonResponse.builder()
                    .hasError(false)
                    .message("Order created!")
                    .content(objectMapper.writeValueAsString(newOrder)).build();
        }
    }

    @Override
    public CommonResponse editOrder(Order order) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (order == null){
            return CommonResponse.builder()
                    .message("Could not change the order")
                    .hasError(true).build();
        }
        else {
            Order editedOrder = orderRepository.save(order);
            return CommonResponse.builder()
                    .hasError(false)
                    .message("Order edited successfully!")
                    .content(objectMapper.writeValueAsString(editedOrder)).build();
        }
    }

    @Override
    public CommonResponse getAllOrders() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Order> orders = orderRepository.findAll();
        if ( orders.size() > 0){
            return CommonResponse.builder()
                    .hasError(false)
                    .message("Order edited successfully!")
                    .content(objectMapper.writeValueAsString(orders)).build();
        }
        else {
            return CommonResponse.builder()
                    .message("Could not load orders")
                    .hasError(true).build();
        }
    }

    @Override
    public CommonResponse getOrder(long orderId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()){
            return CommonResponse.builder()
                    .hasError(false)
                    .content(objectMapper.writeValueAsString(order)).build();
        }
        else {
            return CommonResponse.builder()
                    .message("Could not get order")
                    .hasError(true).build();
        }
    }
}
