package officeLunch.Office.Lunch.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import officeLunch.Office.Lunch.model.User;
import officeLunch.Office.Lunch.repository.UserRepository;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository ;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CommonResponse addUser(User user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User newuser = userRepository.save(user);
            return CommonResponse.builder()
                    .message("User created successfully!")
                    .hasError(false)
                    .content(objectMapper.writeValueAsString(newuser)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return CommonResponse.builder()
                    .hasError(true)
                    .message("Could not create User!").build();
        }
    }

    @Override
    public CommonResponse updateUser(User user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User updatedUser = userRepository.save(user);
            return CommonResponse.builder()
                    .message("User updated successfully!")
                    .hasError(false)
                    .content(objectMapper.writeValueAsString(user)).build();
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
            return CommonResponse.builder()
                    .hasError(true)
                    .message("Could not update User!").build();
        }
    }

    @Override
    public CommonResponse deleteUser(Long id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            userRepository.deleteById(id);
            return CommonResponse.builder()
                    .message("User deleted successfully!")
                    .hasError(false)
                    .content("User deleted").build();
        }
        catch (Exception e){
            return CommonResponse.builder()
                    .hasError(true)
                    .message("Could not delete User!").build();
        }
    }
}