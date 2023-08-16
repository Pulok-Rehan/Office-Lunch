package officeLunch.Office.Lunch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.NoArgsConstructor;
import officeLunch.Office.Lunch.enums.Location;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ORGNISATIONS")
public class Organisation {
    @Id
    private long id;
    private String name;
    private Boolean hasDiscount;
    private Location location;
    private Address address;


}
