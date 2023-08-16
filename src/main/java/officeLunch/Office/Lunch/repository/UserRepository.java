package officeLunch.Office.Lunch.repository;

import officeLunch.Office.Lunch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
