package LibraryManagement.LMS.auth.DTO;

import LibraryManagement.LMS.auth.Role;
import LibraryManagement.LMS.auth.User;
import lombok.Data;


@Data
public class UserDTO {
  private Long id;
  private String username;
  private String role;

  public UserDTO(User user){
    this.id = user.getId();
    this.username = user.getUsername();
    this.role = user.getRole().name();
  }

}
