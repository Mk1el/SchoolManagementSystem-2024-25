package LibraryManagement.LMS.schoolConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeographyService {

  @Autowired
  private ProvinceRepository provinceRepo;

  @Autowired
  private RegionRepository regionRepo;

  @Autowired
  private SubRegionRepository subRegionRepo;

  // === Province Methods ===

  @Transactional
  public Province createProvince(Province province) {
    return provinceRepo.save(province);
  }

  // UPDATED: Now returns DTOs
  @Transactional(readOnly = true)
  public List<ProvinceDTO> getAllProvinces() {
    return provinceRepo.findAll()
      .stream()
      .map(ProvinceDTO::new)
      .collect(Collectors.toList());
  }

  // ... other Province methods (update, delete) remain the same ...

  // === Region Methods ===

  @Transactional
  public Region createRegion(Long provinceId, Region region) {
    Province province = provinceRepo.findById(provinceId)
      .orElseThrow(() -> new RuntimeException("Province not found with id: " + provinceId));
    region.setProvince(province);
    return regionRepo.save(region);
  }

  // UPDATED: Now returns DTOs
  @Transactional(readOnly = true)
  public List<RegionDTO> getAllRegions() {
    return regionRepo.findAll()
      .stream()
      .map(RegionDTO::new)
      .collect(Collectors.toList());
  }

  // UPDATED: Now returns DTOs and uses the correct repository method name
  @Transactional(readOnly = true)
  public List<RegionDTO> getRegionsByProvinceId(Long provinceId) {
    List<Region> regions = regionRepo.findByProvince_Id(provinceId);
    return regions.stream().map(RegionDTO::new).toList();
  }


  // ... other Region methods ...

  // === SubRegion Methods ===

  @Transactional
  public SubRegion createSubRegion(Long regionId, SubRegion subRegion) {
    Region region = regionRepo.findById(regionId)
      .orElseThrow(() -> new RuntimeException("Region not found with id: " + regionId));
    subRegion.setRegion(region);
    return subRegionRepo.save(subRegion);
  }

  @Transactional(readOnly = true)
  public List<SubRegionDTO> getAllSubRegions() {
    return subRegionRepo.findAll()
      .stream()
      .map(SubRegionDTO::new)
      .collect(Collectors.toList());
  }

  // UPDATED: Now returns DTOs and uses the correct repository method name
  @Transactional(readOnly = true)
  public List<SubRegionDTO> getSubRegionsByRegionId(Long regionId) {
    // Ensure SubRegionRepository has the method: List<SubRegion> findByRegion_Id(Long regionId);
    return subRegionRepo.findByRegionId(regionId)
      .stream()
      .map(SubRegionDTO::new)
      .collect(Collectors.toList());
  }

  @Transactional
  public void deleteSubRegion(Long id) {
    subRegionRepo.deleteById(id);
  }

  // ... other update methods ...
}
