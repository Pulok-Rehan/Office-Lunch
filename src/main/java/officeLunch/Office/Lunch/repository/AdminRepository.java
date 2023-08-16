package officeLunch.Office.Lunch.repository;

import officeLunch.Office.Lunch.model.Admin;
import officeLunch.Office.Lunch.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
