package LibraryManagement.LMS.schoolConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/geography")
public class GeographyController {

  @Autowired
  private GeographyService geoService;

  // Create
  @PreAuthorize("hasRole('SUPER_USER')")
  @PostMapping("/provinces")
  public Province addProvince(@RequestBody Province p) {
    return geoService.createProvince(p);
  }

  @PreAuthorize("hasRole('SUPER_USER')")
  @PostMapping("/regions/{provinceId}")
  public Region addRegion(@PathVariable Long provinceId, @RequestBody Region r) {
    return geoService.createRegion(provinceId, r);
  }

  @PreAuthorize("hasRole('SUPER_USER')")
  @PostMapping("/sub-regions/{regionId}")
  public SubRegion addSubRegion(@PathVariable Long regionId, @RequestBody SubRegion sr) {
    return geoService.createSubRegion(regionId, sr);
  }

  // Get All
  @PreAuthorize("hasRole('SUPER_USER')")
  @GetMapping("/provinces")
  public List<Province> listProvinces() {
    return geoService.getAllProvinces();
  }

  @PreAuthorize("hasRole('SUPER_USER')")
  @GetMapping("/regions")
  public List<Region> listRegions() {
    return geoService.getAllRegions();
  }

  @PreAuthorize("hasRole('SUPER_USER')")
  @GetMapping("/sub-regions")
  public List<SubRegionDTO> listSubRegions() {
    return geoService.getAllSubRegions();
  }

}
