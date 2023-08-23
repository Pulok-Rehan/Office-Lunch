package officeLunch.Office.Lunch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import officeLunch.Office.Lunch.model.Admin;
import officeLunch.Office.Lunch.response.CommonResponse;

public interface AdminService {
    CommonResponse addAdmin(Admin admin);
    CommonResponse deleteAdmin(long id);
    CommonResponse updateAdmin(Admin admin);
    CommonResponse getAdmins() throws JsonProcessingException;
}
