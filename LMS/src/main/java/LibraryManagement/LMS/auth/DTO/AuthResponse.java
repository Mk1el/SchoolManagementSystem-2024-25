package LibraryManagement.LMS.auth.DTO;

import LibraryManagement.LMS.auth.User;

public class AuthResponse {
    public String token;
    public UserDTO user;
    public AuthResponse(String token, User user){

      this.token = token;
      this.user = new UserDTO(user);
    }
}
