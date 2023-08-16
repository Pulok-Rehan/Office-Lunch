package officeLunch.Office.Lunch.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ADMINS")
@Entity
public class Admin {
    @Id
    private long id;
    private String name;
}
