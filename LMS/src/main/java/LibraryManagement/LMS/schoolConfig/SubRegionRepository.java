package LibraryManagement.LMS.schoolConfig;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubRegionRepository extends JpaRepository<SubRegion, Long> {
  List<SubRegion> findByRegionId(Long regionId);
}
