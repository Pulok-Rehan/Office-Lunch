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
    @GeneratedValue
    private Long id;
    private double totalAmount;
    @JoinColumn(name = "packageId")
    @OneToOne()
    private LunchPackage lunchPackage;
    @JoinColumn(name = "userId")
    @OneToOne
    private Customers customers;
}
