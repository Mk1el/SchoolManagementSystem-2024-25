package LibraryManagement.LMS.schoolConfig;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Province {
  @Id @GeneratedValue
  private Long id;
  private String name;

  // Relationships
  @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
  @JsonBackReference
  private List<Region> regions;
}
