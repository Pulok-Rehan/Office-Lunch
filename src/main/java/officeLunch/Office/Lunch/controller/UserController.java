package officeLunch.Office.Lunch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import officeLunch.Office.Lunch.model.Customers;
import officeLunch.Office.Lunch.model.LunchPackage;
import officeLunch.Office.Lunch.model.Order;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.OrderService;
import officeLunch.Office.Lunch.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping(path = "/users")
//    public CommonResponse getUsers() throws JsonProcessingException {
//        return userService.;
//    }

    @PostMapping(path = "/users")
    public CommonResponse createUser(@RequestBody Customers customers) throws JsonProcessingException {
        return userService.addUser(customers);
    }

    @PutMapping(path = "/users")
    public CommonResponse updateOrder(@RequestBody Customers customers) throws JsonProcessingException {
        return userService.updateUser(customers);
    }

    @DeleteMapping(path = "/users")
    public CommonResponse deleteUser(@RequestBody long user_id){
        return userService.deleteUser(user_id);
    }

//    @GetMapping(path = "/users")
//    public CommonResponse getUser(@RequestParam("id") long user_id) throws JsonProcessingException {
//        return userService.getUser(user_id);
//    }
}
