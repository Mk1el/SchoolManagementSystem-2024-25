package LibraryManagement.LMS.schoolConfig;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SubRegion {
  @Id
  @GeneratedValue
  private Long id;
  private String name;

  @ManyToOne
  @JoinColumn(name = "region_id")
  private Region region;
}
