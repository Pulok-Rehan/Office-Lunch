package officeLunch.Office.Lunch.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import officeLunch.Office.Lunch.model.Admin;
import officeLunch.Office.Lunch.repository.AdminRepository;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public CommonResponse addAdmin(Admin admin) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Admin newAdmin = adminRepository.save(admin);
            return CommonResponse.builder()
                    .message("Admin created successfully!")
                    .hasError(false)
                    .content(objectMapper.writeValueAsString(newAdmin)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return CommonResponse.builder()
                    .hasError(true)
                    .message("Could not create Admin!").build();
        }
    }

    @Override
    public CommonResponse deleteAdmin(long id) {
        try {
            adminRepository.deleteById(id);
            return CommonResponse.builder()
                    .message("Admin deleted successfully!")
                    .hasError(false)
                    .content("Admin deleted").build();
        }
        catch (Exception e){
            return CommonResponse.builder()
                    .hasError(true)
                    .message("Could not delete Admin!").build();
        }
    }

    @Override
    public CommonResponse updateAdmin(Admin admin) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Admin updatedAdmin = adminRepository.save(admin);
            return CommonResponse.builder()
                    .message("Admin updated successfully!")
                    .hasError(false)
                    .content(objectMapper.writeValueAsString(updatedAdmin)).build();
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
            return CommonResponse.builder()
                    .hasError(true)
                    .message("Could not update Admin!").build();
        }
    }

    @Override
    public CommonResponse getAdmins() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Admin> admins = adminRepository.findAll();
        if (admins.size()<1){
            return CommonResponse.builder()
                    .hasError(true)
                    .message("No admins found")
                    .content(null).build();
        }
        return CommonResponse.builder()
                .hasError(false)
                .message("Admins found successfully!")
                .content(objectMapper.writeValueAsString(admins)).build();
    }
}
