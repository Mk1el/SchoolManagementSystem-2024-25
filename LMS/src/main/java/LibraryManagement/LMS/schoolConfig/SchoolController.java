package LibraryManagement.LMS.schoolConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {
  @Autowired
  private SchoolService schoolService;
  @PreAuthorize("hasRole('SUPER_USER')")
  @PostMapping("/add")
  public School createSchool(@RequestBody School school) {
    return schoolService.createSchool( school);
  }

  @PreAuthorize("hasRole('SUPER_USER')")
  @GetMapping
  public List<School> getAll() {
    return schoolService.getAllSchools();
  }

  @PutMapping("/{id}")
  public School update(@PathVariable Long id, @RequestBody School updated) {
    return schoolService.updateSchool(id, updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    schoolService.deleteSchool(id);
  }
}

