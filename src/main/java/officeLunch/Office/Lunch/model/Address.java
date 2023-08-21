package officeLunch.Office.Lunch.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String roadNo;
    private String houseNo;
    private String floorNo;
}
