package officeLunch.Office.Lunch.dto;

import lombok.Data;

import java.util.List;
@Data
public class PackageDto {
    private List<Long> itemIdlist ;
    private double discount;
}
