package officeLunch.Office.Lunch.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import officeLunch.Office.Lunch.model.Customers;
import officeLunch.Office.Lunch.model.Organisation;
import officeLunch.Office.Lunch.repository.OrganisationRepository;
import officeLunch.Office.Lunch.repository.UserRepository;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository ;
    private final OrganisationRepository organisationRepository;

    public UserServiceImpl(UserRepository userRepository, OrganisationRepository organisationRepository) {
        this.userRepository = userRepository;
        this.organisationRepository = organisationRepository;
    }

    @Override
    public CommonResponse addUser(Customers customers) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (customers.getOrganisation().getId() == 0){
            return this.universalCustomerFailedResponse();
        }
        Optional<Organisation> customerOrganisation = organisationRepository.findById(customers.getOrganisation().getId());
        if (customerOrganisation.isPresent()){
            customers.setOrganisation(customerOrganisation.get());
            customers.setHasDiscount(customerOrganisation.get().getHasDiscount());
            customers.setDiscountAmount(customers.getDiscountAmount()>customerOrganisation.get().getDiscount()?
                    customers.getDiscountAmount() : Double.valueOf(customerOrganisation.get().getDiscount()));
        }
        else {
            customers.setOrganisation(new Organisation());
            customers.setBelongstoOrganisation(false);
        }
        try {
            Customers newuser = userRepository.save(customers);
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
    public CommonResponse updateUser(Customers customers) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Customers updatedCustomers = userRepository.save(customers);
            return CommonResponse.builder()
                    .message("User updated successfully!")
                    .hasError(false)
                    .content(objectMapper.writeValueAsString(updatedCustomers)).build();
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
    private CommonResponse universalCustomerFailedResponse(){
        return CommonResponse.builder()
                .hasError(true)
                .message("Could not create User!")
                .content(null).build();
    }
}
