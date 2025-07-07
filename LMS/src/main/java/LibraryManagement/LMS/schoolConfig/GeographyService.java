package LibraryManagement.LMS.schoolConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  // Province CRUD
  public Province createProvince(Province province) {
    return provinceRepo.save(province);
  }

  public List<Province> getAllProvinces() {
    return provinceRepo.findAll();
  }

  public Optional<Province> getProvinceById(Long id) {
    return provinceRepo.findById(id);
  }

  public Province updateProvince(Long id, Province updated) {
    Province existing = provinceRepo.findById(id)
      .orElseThrow(() -> new RuntimeException("Province not found with id: " + id));
    existing.setName(updated.getName());
    return provinceRepo.save(existing);
  }

  public void deleteProvince(Long id) {
    provinceRepo.deleteById(id);
  }

  // Region CRUD
  public Region createRegion(Long provinceId, Region region) {
    Province province = provinceRepo.findById(provinceId)
      .orElseThrow(() -> new RuntimeException("Province not found with id: " + provinceId));
    region.setProvince(province);
    return regionRepo.save(region);
  }


  public List<Region> getAllRegions() {
    return regionRepo.findAll();
  }

  public Region updateRegion(Long id, Region updated) {
    Region existing = regionRepo.findById(id)
      .orElseThrow(() -> new RuntimeException("Region not found with id: " + id));
    existing.setName(updated.getName());
    return regionRepo.save(existing);
  }

  public void deleteRegion(Long id) {
    regionRepo.deleteById(id);
  }

  // SubRegion CRUD
  public SubRegion createSubRegion(Long regionId, SubRegion subRegion) {
    Region region = regionRepo.findById(regionId)
      .orElseThrow(() -> new RuntimeException("Region not found with id: " + regionId));
    subRegion.setRegion(region);
    return subRegionRepo.save(subRegion);
  }

  public List<SubRegionDTO> getAllSubRegions() {
    return subRegionRepo.findAll()
      .stream()
      .map(SubRegionDTO::new)
      .collect(Collectors.toList());
  }


  public SubRegion updateSubRegion(Long id, SubRegion updated) {
    SubRegion existing = subRegionRepo.findById(id)
      .orElseThrow(() -> new RuntimeException("SubRegion not found with id: " + id));
    existing.setName(updated.getName());
    return subRegionRepo.save(existing);
  }

  public void deleteSubRegion(Long id) {
    subRegionRepo.deleteById(id);
  }
}
