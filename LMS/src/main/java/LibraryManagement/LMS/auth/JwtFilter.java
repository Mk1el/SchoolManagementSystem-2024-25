package LibraryManagement.LMS.auth;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserDetailsService userDetailsService;

  // A simple prefix for our logs to easily find them in the console
  private static final String LOG_PREFIX = "[JWT FILTER] ==> ";

  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain)
    throws ServletException, IOException {

    // 1. Get the "Authorization" header from the incoming request.
    final String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      // The code enters here and stops.
      filterChain.doFilter(request, response);
      return;
    }

    String jwt = null;
    String username = null;

    try {
      // 3. Extract the JWT string by removing "Bearer ".
      jwt = authHeader.substring(7);

      // 4. Extract the username from the token. This is where exceptions can happen!
      username = jwtUtil.extractUsername(jwt);
      System.out.println(LOG_PREFIX + "Extracted username: " + username);

    } catch (ExpiredJwtException e) {
      System.out.println(LOG_PREFIX + "JWT Token has expired: " + e.getMessage());
    } catch (SignatureException e) {
      System.out.println(LOG_PREFIX + "JWT Signature is invalid: " + e.getMessage());
    } catch (MalformedJwtException e) {
      System.out.println(LOG_PREFIX + "JWT Token is malformed: " + e.getMessage());
    } catch (Exception e) {
      System.out.println(LOG_PREFIX + "An error occurred during JWT parsing: " + e.getMessage());
    }


    // 5. If we have a username AND the user is not already authenticated in the security context...
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      System.out.println(LOG_PREFIX + "User '" + username + "' is not yet authenticated. Proceeding with validation.");

      try {
        // 6. Load the user details from the database via UserDetailsService.
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println(LOG_PREFIX + "Authorities for user '" + username + "': " + userDetails.getAuthorities());

        // 7. Validate the token. Does it match the user details and is it not expired?
        if (jwtUtil.validateToken(jwt, userDetails)) {
          System.out.println(LOG_PREFIX + "Token is valid for user '" + username + "'.");

          // 8. If the token is valid, create an Authentication object.
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null, // We don't need credentials
            userDetails.getAuthorities() // This is where roles are passed!
          );

          // 9. Enhance the authToken with details from the original web request.
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          // 10. IMPORTANT: Set the authentication in the SecurityContext.
          // This is the line that tells Spring Security "This user is now logged in".
          SecurityContextHolder.getContext().setAuthentication(authToken);
          System.out.println(LOG_PREFIX + "Authentication set in SecurityContext for user '" + username + "'.");
        } else {
          System.out.println(LOG_PREFIX + "Token validation failed for user '" + username + "'.");
        }
      } catch (UsernameNotFoundException e) {
        System.out.println(LOG_PREFIX + "User '" + username + "' found in JWT but not in the database.");
      }
    } else {
      if (username == null) {
        System.out.println(LOG_PREFIX + "Username could not be extracted from JWT.");
      } else {
        System.out.println(LOG_PREFIX + "User '" + username + "' is already authenticated.");
      }
    }

    // 11. Always continue the filter chain.
    filterChain.doFilter(request, response);
  }
}
