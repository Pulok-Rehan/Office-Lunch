package officeLunch.Office.Lunch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double totalAmount;
    @JoinColumn(name = "packageId")
    @ManyToOne
    private LunchPackage lunchPackage;
    @JoinColumn(name = "userId")
    @ManyToOne(cascade = CascadeType.ALL)
    private Customers customers;
}
