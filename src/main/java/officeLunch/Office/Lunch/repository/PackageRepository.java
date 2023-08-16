package officeLunch.Office.Lunch.repository;

import officeLunch.Office.Lunch.model.LunchPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<LunchPackage, Long> {
}
