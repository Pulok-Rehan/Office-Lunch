package officeLunch.Office.Lunch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import officeLunch.Office.Lunch.model.User;
import officeLunch.Office.Lunch.response.CommonResponse;

public interface UserService {
    CommonResponse addUser(User user) throws JsonProcessingException;
    CommonResponse updateUser(User user) throws JsonProcessingException;
    CommonResponse deleteUser(Long id);
}
