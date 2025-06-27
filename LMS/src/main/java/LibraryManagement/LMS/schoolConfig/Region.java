package LibraryManagement.LMS.schoolConfig;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Region {
  @Id
  @GeneratedValue
  private Long id;
  private String name;

  @ManyToOne
  @JoinColumn(name = "province_id")
  private Province province;

  @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
  private List<SubRegion> subRegions;
}
