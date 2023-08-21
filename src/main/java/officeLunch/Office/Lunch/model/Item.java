package officeLunch.Office.Lunch.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import officeLunch.Office.Lunch.enums.MainDish;
import officeLunch.Office.Lunch.enums.SecondaryDish;
@Data
@Builder
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Enumerated(EnumType.STRING)
    private MainDish mainDish;
    @Enumerated(EnumType.STRING)
    private SecondaryDish secondaryDish;
    private long price;


}
