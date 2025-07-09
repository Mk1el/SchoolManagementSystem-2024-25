package LibraryManagement.LMS.schoolConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
  @Autowired
  private SchoolRepository schoolRepo;
  @Autowired
  private SubRegionRepository subRegionRepo;
  // In SchoolService.java

  // The method signature now only takes a School object
  School createSchool(School school) {
    // 1. Get the subRegionId from the incoming School object.
    //    It is assumed that the SubRegion object within the School object has its ID set.
    if (school.getSubRegion() == null || school.getSubRegion().getId() == null) {
      throw new IllegalArgumentException("SubRegion ID must be provided to create a school.");
    }
    Long subRegionId = school.getSubRegion().getId();

    // 2. Find the SubRegion from the database using the ID.
    //    This is a crucial step to ensure you are associating with a real, managed entity.
    SubRegion subRegion = subRegionRepo.findById(subRegionId)
      .orElseThrow(() -> new RuntimeException("SubRegion not found with id: " + subRegionId));

    // 3. Set the managed SubRegion entity on the school object.
    school.setSubRegion(subRegion);

    // 4. Save the school.
    return schoolRepo.save(school);
  }
  public List<School> getAllSchools(){
    return schoolRepo.findAll();
  }
  public void deleteSchool(Long id) {
    schoolRepo.deleteById(id);
  }

  public School updateSchool(Long id, School updated) {
    School school = schoolRepo.findById(id).orElseThrow();
    school.setName(updated.getName());
    school.setLevel(updated.getLevel());
    return schoolRepo.save(school);
  }
}
