package LibraryManagement.LMS.schoolConfig;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.http.ResponseEntity;
@Data
@Entity
public class School {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String email;
  private String phone;
  @Enumerated(EnumType.STRING)
  private SchoolLevel level;
  @ManyToOne
  private SubRegion subRegion;
  @Lob
  @Column(length = Integer.MAX_VALUE)
  private byte[] image;
  private String motto;
  private String colors;
}
