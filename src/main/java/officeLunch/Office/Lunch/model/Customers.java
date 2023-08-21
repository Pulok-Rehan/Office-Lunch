package officeLunch.Office.Lunch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "USERS")
@Entity
public class Customers {
    @Id
    private long id;
    private String name;
    private Boolean hasDiscount;
    private long discountAmount;
    @OneToOne
    private Organisation organisation;
}
