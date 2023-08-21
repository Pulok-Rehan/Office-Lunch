package officeLunch.Office.Lunch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "PACKAGES")
@Entity
public class LunchPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Item> items;
    private long totalPrice;
    private long discount;
}
