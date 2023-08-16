package officeLunch.Office.Lunch.repository;

import officeLunch.Office.Lunch.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
