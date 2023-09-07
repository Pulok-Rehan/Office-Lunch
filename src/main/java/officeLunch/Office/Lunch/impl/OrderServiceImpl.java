package officeLunch.Office.Lunch.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jshell.execution.Util;
import officeLunch.Office.Lunch.dto.OrderDto;
import officeLunch.Office.Lunch.model.Customers;
import officeLunch.Office.Lunch.model.LunchPackage;
import officeLunch.Office.Lunch.model.Order;
import officeLunch.Office.Lunch.repository.OrderRepository;
import officeLunch.Office.Lunch.repository.PackageRepository;
import officeLunch.Office.Lunch.repository.UserRepository;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.OrderService;
import org.springframework.stereotype.Service;
import util.UtilService;

import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PackageRepository packageRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PackageRepository packageRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.packageRepository = packageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommonResponse createOrder(OrderDto orderDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        double totalAmount = 0;
        if(orderDto==null){
            return UtilService.universalFailedResponse();
        }
        else {
            Optional<LunchPackage> orderedLunchPackage = packageRepository.findById(orderDto.getLunchPackageId());
            if (orderedLunchPackage.isEmpty()){
                return UtilService.universalFailedResponse();
            }
            Optional<Customers> orderingCustomer = userRepository.findById(orderDto.getCustomerId());
            if (orderingCustomer.isEmpty()){
                return UtilService.universalFailedResponse();
            }
            if (orderingCustomer.get().getHasDiscount()){
                totalAmount = orderedLunchPackage.get().getTotalPrice() - (orderedLunchPackage.get().getTotalPrice()*
                        (orderingCustomer.get().getDiscountAmount()/100)+
                        (orderingCustomer.get().getBelongstoOrganisation() ? 0 :
                                orderedLunchPackage.get().getDiscountAmount()));
            }
            Order newOrder = orderRepository.save(
                    Order.builder()
                    .lunchPackage(orderedLunchPackage.get())
                    .totalAmount(totalAmount == 0 ? orderedLunchPackage.get().getTotalPrice() : totalAmount)
                    .customers(orderingCustomer.get())
                    .build());
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
            return UtilService.universalFailedResponse();
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
                    .message("Order fetch successful!")
                    .content(objectMapper.writeValueAsString(orders)).build();
        }
        else {
            return UtilService.universalFailedResponse();
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
            return UtilService.universalFailedResponse();
        }
    }

    @Override
    public CommonResponse deleteOrder(long orderId) {
        try{
            orderRepository.deleteById(orderId);
            return CommonResponse.builder()
                    .hasError(false)
                    .message("Order deleted successfully!")
                    .content("Deleted ID is "+ orderId).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return UtilService.universalFailedResponse();
        }
    }

}
