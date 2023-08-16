package officeLunch.Office.Lunch.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommonResponse {
    private Boolean hasError;
    //private String code;
    private String content;
    private String message;

}
