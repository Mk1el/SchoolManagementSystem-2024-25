package LibraryManagement.LMS.schoolConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/geography")
public class GeographyController {
  @Autowired
  private GeographyService geoService;
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
//  @PreAuthorize("hasAuthority('SUPER_USER')")
  @PostMapping("/sub-regions/{regionId}")
  public SubRegion addSubRegion(@PathVariable Long regionId, @RequestBody SubRegion sr) {
    return geoService.createSubRegion(regionId, sr);
  }
//  @PreAuthorize("hasAuthority('SUPER_USER')")
  @GetMapping("/provinces")
  public List<Province> listProvinces() {
    return geoService.getAllProvinces();
  }
}
