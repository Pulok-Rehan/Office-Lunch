package officeLunch.Office.Lunch.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String grantType;
    private  String username;
    private String password;
}
