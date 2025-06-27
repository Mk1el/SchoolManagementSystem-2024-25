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
  School createSchool(Long subRegionId, School school){
    SubRegion subRegion = subRegionRepo.findById(subRegionId).orElseThrow();
    school.setSubRegion(subRegion);
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
