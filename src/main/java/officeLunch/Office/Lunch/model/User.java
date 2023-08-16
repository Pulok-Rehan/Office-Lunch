package officeLunch.Office.Lunch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "USERS")
@Entity
public class User {
    @Id
    private long id;
    private String name;
    private Boolean hasDiscount;
    private long discountAmount;
    @JoinColumn(name = "orgId")
    @OneToOne
    private Organisation organisation;
}
