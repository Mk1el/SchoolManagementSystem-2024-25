package LibraryManagement.LMS.schoolConfig;

import lombok.Data;

@Data
public class SchoolDTO {
  private String name;
  private String level;
  private String email;
  private String phone;
  private Long subRegionId;
  private String imageBase64; // Field for the Base64 string
  private String motto;
  private String colors;
}
