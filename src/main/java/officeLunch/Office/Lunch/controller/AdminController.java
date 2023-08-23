package officeLunch.Office.Lunch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import officeLunch.Office.Lunch.model.Admin;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(path = "/admins")
    public CommonResponse getAdmins() throws JsonProcessingException {
        return adminService.getAdmins();
    }

    @PostMapping(path = "/admins")
    public CommonResponse addAdmin(@RequestBody Admin admin){
        return adminService.addAdmin(admin);
    }

    @DeleteMapping(path = "/admins")
    public CommonResponse deleteAdmin(@RequestBody long admin_id){
        return adminService.deleteAdmin(admin_id);
    }
    @PutMapping(path = "/admins")
    public CommonResponse updateAdmin(@RequestBody Admin admin){
        return adminService.updateAdmin(admin);
    }
}
