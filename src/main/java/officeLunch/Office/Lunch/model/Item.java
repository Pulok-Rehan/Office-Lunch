package officeLunch.Office.Lunch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import officeLunch.Office.Lunch.enums.MainDish;
import officeLunch.Office.Lunch.enums.SecondaryDish;
@NoArgsConstructor
@AllArgsConstructor
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
