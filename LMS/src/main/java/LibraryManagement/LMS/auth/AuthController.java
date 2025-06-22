package LibraryManagement.LMS.auth;

import LibraryManagement.LMS.auth.DTO.AuthRequest;
import LibraryManagement.LMS.auth.DTO.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
      User user = new User();
      user.setUsername(request.username);
      user.setPassword(encoder.encode(request.password));

      // Set role based on input
      if (request.role != null) {
        if (request.role.equalsIgnoreCase("LIBRARIAN")) {
          user.setRole(Role.LIBRARIAN);
        } else if (request.role.equalsIgnoreCase("SUPER_USER")) {
          user.setRole(Role.SUPER_USER);
        } else {
          user.setRole(Role.USER); // fallback for unknown roles
        }
      } else {
        user.setRole(Role.USER); // default role if none provided
      }

      userRepository.save(user);
      return "User registered as " + user.getRole();
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username, request.password)
        );
        User user = userRepository.findByUsername(request.username).orElseThrow();
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        return new AuthResponse(token, user);
    }
}

