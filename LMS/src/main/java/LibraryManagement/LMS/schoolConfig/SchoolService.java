package LibraryManagement.LMS.schoolConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import Transactional

import java.util.Base64;
import java.util.List;

@Service
public class SchoolService {

  @Autowired
  private SchoolRepository schoolRepo;

  @Autowired
  private SubRegionRepository subRegionRepo;

  // The create method now accepts the DTO and contains all logic
  @Transactional // Ensures all database operations succeed or fail together
  public School createSchool(SchoolDTO schoolDTO) {
    School school = new School();
    school.setName(schoolDTO.getName());
    school.setEmail(schoolDTO.getEmail());
    school.setPhone(schoolDTO.getPhone());
    school.setMotto(schoolDTO.getMotto());
    school.setColors(schoolDTO.getColors());

    // Validate and set SchoolLevel
    try {
      SchoolLevel level = SchoolLevel.valueOf(schoolDTO.getLevel().toUpperCase());
      school.setLevel(level);
    } catch (IllegalArgumentException e) {
      throw new RuntimeException("Invalid school level provided: " + schoolDTO.getLevel());
    }

    // Find and associate the SubRegion
    SubRegion subRegion = subRegionRepo.findById(schoolDTO.getSubRegionId())
      .orElseThrow(() -> new RuntimeException("SubRegion not found with id: " + schoolDTO.getSubRegionId()));
    school.setSubRegion(subRegion);

    // Decode and set the image
    if (schoolDTO.getImageBase64() != null && !schoolDTO.getImageBase64().isEmpty()) {
      byte[] imageBytes = Base64.getDecoder().decode(schoolDTO.getImageBase64());
      school.setImage(imageBytes);
    }

    return schoolRepo.save(school);
  }

  public List<School> getAllSchools() {
    return schoolRepo.findAll();
  }

  @Transactional
  public void deleteSchool(Long id) {
    if (!schoolRepo.existsById(id)) {
      throw new RuntimeException("School not found with id: " + id);
    }
    schoolRepo.deleteById(id);
  }

  // The update method is now complete and works with the DTO
  @Transactional
  public School updateSchool(Long id, SchoolDTO schoolDTO) {
    School schoolToUpdate = schoolRepo.findById(id)
      .orElseThrow(() -> new RuntimeException("School not found with id: " + id));

    // Update all fields from the DTO
    schoolToUpdate.setName(schoolDTO.getName());
    schoolToUpdate.setEmail(schoolDTO.getEmail());
    schoolToUpdate.setPhone(schoolDTO.getPhone());
    schoolToUpdate.setMotto(schoolDTO.getMotto());
    schoolToUpdate.setColors(schoolDTO.getColors());
    schoolToUpdate.setLevel(SchoolLevel.valueOf(schoolDTO.getLevel().toUpperCase()));

    // Update SubRegion if it's different
    if (!schoolToUpdate.getSubRegion().getId().equals(schoolDTO.getSubRegionId())) {
      SubRegion newSubRegion = subRegionRepo.findById(schoolDTO.getSubRegionId())
        .orElseThrow(() -> new RuntimeException("SubRegion not found with id: " + schoolDTO.getSubRegionId()));
      schoolToUpdate.setSubRegion(newSubRegion);
    }

    // Update image if a new one is provided
    if (schoolDTO.getImageBase64() != null && !schoolDTO.getImageBase64().isEmpty()) {
      byte[] imageBytes = Base64.getDecoder().decode(schoolDTO.getImageBase64());
      schoolToUpdate.setImage(imageBytes);
    }

    return schoolRepo.save(schoolToUpdate);
  }
}
