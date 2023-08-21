package officeLunch.Office.Lunch.repository;

import officeLunch.Office.Lunch.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Customers, Long> {
}
