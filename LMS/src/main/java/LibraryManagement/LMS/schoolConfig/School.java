package LibraryManagement.LMS.schoolConfig;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class School {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  @Enumerated(EnumType.STRING)
  private SchoolLevel level;
  @ManyToOne
  private SubRegion subRegion;
}
