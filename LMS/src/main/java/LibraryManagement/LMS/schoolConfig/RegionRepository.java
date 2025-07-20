package LibraryManagement.LMS.schoolConfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {
  @Query("SELECT r FROM Region r JOIN FETCH r.province")
//  @Override
//  List<Region> findAll();
  List<Region> findByProvince_Id(Long provinceId);

}
