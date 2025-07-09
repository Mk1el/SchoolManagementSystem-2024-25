package LibraryManagement.LMS.schoolConfig;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
  @JsonBackReference
  @JoinColumn(name = "province_id")
  private Province province;

  @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<SubRegion> subRegions;
}
