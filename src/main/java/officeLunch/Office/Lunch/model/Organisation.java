package officeLunch.Office.Lunch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import officeLunch.Office.Lunch.enums.Location;
import org.springframework.data.relational.core.mapping.Table;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ORGNISATIONS")
public class Organisation {
    @Id
    private long id;
    private String name;
    private Boolean hasDiscount;
    @Enumerated(EnumType.STRING)
    private Location location;
    @OneToOne
    private Address address;


}
