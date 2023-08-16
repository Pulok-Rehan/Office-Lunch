package officeLunch.Office.Lunch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import officeLunch.Office.Lunch.model.LunchPackage;
import officeLunch.Office.Lunch.model.Order;
import officeLunch.Office.Lunch.response.CommonResponse;

public interface OrderService {
    CommonResponse createOrder(LunchPackage lunchPackage) throws JsonProcessingException;
    CommonResponse editOrder(Order order) throws JsonProcessingException;
    CommonResponse getAllOrders() throws JsonProcessingException;
    CommonResponse getOrder(long orderId) throws JsonProcessingException;
}
