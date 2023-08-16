package officeLunch.Office.Lunch.model;

import lombok.Builder;
import lombok.Data;
import officeLunch.Office.Lunch.enums.MainDish;
import officeLunch.Office.Lunch.enums.SecondaryDish;
@Data
@Builder
public class Item {
    private MainDish mainDish;
    private SecondaryDish secondaryDish;
    private long price;


}
