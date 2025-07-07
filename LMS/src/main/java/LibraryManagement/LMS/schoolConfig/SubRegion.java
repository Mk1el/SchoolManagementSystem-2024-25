package LibraryManagement.LMS.schoolConfig;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @JsonIgnore
  @JoinColumn(name = "region_id")
  private Region region;
}
