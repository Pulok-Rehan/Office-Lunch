package officeLunch.Office.Lunch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import officeLunch.Office.Lunch.model.Customers;
import officeLunch.Office.Lunch.response.CommonResponse;

public interface UserService {
    CommonResponse addUser(Customers customers) throws JsonProcessingException;
    CommonResponse updateUser(Customers customers) throws JsonProcessingException;
    CommonResponse deleteUser(Long id);
}
