package LibraryManagement.LMS.schoolConfig;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "region_id")
  @JsonBackReference
  private Region region;
}
