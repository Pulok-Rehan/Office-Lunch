package officeLunch.Office.Lunch.repository;

import officeLunch.Office.Lunch.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
