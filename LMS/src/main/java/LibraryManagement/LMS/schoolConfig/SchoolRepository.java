package LibraryManagement.LMS.schoolConfig;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SchoolRepository extends JpaRepository<School, Long> {
  @Query("SELECT s FROM School s WHERE s.subRegion.region.id = :regionId")
  List<School> findSchoolsByRegionId(@Param("regionId") Long regionId);
}
