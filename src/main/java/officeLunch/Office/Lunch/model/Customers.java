package officeLunch.Office.Lunch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "CUSTOMERS")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "CUSTOMERS_NAME")
    private String name;
    private Boolean hasDiscount;
    private double discountAmount;
    @ManyToOne
    private Organisation organisation;
    @Column(columnDefinition = "true")
    private Boolean belongstoOrganisation;
}
