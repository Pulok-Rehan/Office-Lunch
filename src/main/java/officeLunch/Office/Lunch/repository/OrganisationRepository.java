package officeLunch.Office.Lunch.repository;

import officeLunch.Office.Lunch.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
}
