package officeLunch.Office.Lunch.model;

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
public class LunchPackage {
    private long id;
    private List<Item> items;
    private long totalPrice;
    private long discount;
}
