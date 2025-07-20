package LibraryManagement.LMS.schoolConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

  @Autowired
  private SchoolService schoolService;

  @PreAuthorize("hasRole('SUPER_USER')")
  @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<School> createSchool(@RequestBody SchoolDTO schoolDTO) throws IOException {
    // Delegate all creation logic to the service layer
    School savedSchool = schoolService.createSchool(schoolDTO);
    return ResponseEntity.ok(savedSchool);
  }

  @PreAuthorize("hasRole('SUPER_USER')")
  @GetMapping
  public List<School> getAll() {
    return schoolService.getAllSchools();
  }
  @PreAuthorize("hasRole('SUPER_USER')")
  @GetMapping("/{id}")
  public ResponseEntity<School> getById(@PathVariable Long id){
    School school = schoolService.getSchoolById(id);
    return ResponseEntity.ok(school);
  }

  @PreAuthorize("hasRole('SUPER_USER')")
  @PutMapping("/{id}")
  public ResponseEntity<School> update(@PathVariable Long id, @RequestBody SchoolDTO schoolDTO) {
    // Pass the DTO to the service to control what can be updated
    School updatedSchool = schoolService.updateSchool(id, schoolDTO);
    return ResponseEntity.ok(updatedSchool);
  }

  @PreAuthorize("hasRole('SUPER_USER')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    schoolService.deleteSchool(id);
    return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
  }
}
