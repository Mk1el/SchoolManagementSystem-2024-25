package LibraryManagement.LMS.schoolConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/geography")
public class GeographyController {
  @Autowired
  private GeographyService geoService;

  @PostMapping("/provinces")
  public Province addProvince(@RequestBody Province p) {
    return geoService.createProvince(p);
  }

  @PostMapping("/regions/{provinceId}")
  public Region addRegion(@PathVariable Long provinceId, @RequestBody Region r) {
    return geoService.createRegion(provinceId, r);
  }

  @PostMapping("/sub-regions/{regionId}")
  public SubRegion addSubRegion(@PathVariable Long regionId, @RequestBody SubRegion sr) {
    return geoService.createSubRegion(regionId, sr);
  }

  @GetMapping("/provinces")
  public List<Province> listProvinces() {
    return geoService.getAllProvinces();
  }
}
