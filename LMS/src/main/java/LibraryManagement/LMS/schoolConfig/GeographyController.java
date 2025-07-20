package LibraryManagement.LMS.schoolConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin // Allows requests from your Angular frontend
@RestController
@RequestMapping("/api/geography")
public class GeographyController {

  @Autowired
  private GeographyService geoService;

  // === GET Endpoints - These are used to populate your tables and dropdowns ===

  @PreAuthorize("hasAnyRole('SUPER_USER', 'USER')")
  @GetMapping("/provinces")
  public ResponseEntity<List<ProvinceDTO>> listProvinces() {
    return ResponseEntity.ok(geoService.getAllProvinces());
  }

  @PreAuthorize("hasAnyRole('SUPER_USER', 'USER')")
  @GetMapping("/regions")
  public ResponseEntity<List<RegionDTO>> listRegions() {
    return ResponseEntity.ok(geoService.getAllRegions());
  }

  @PreAuthorize("hasAnyRole('SUPER_USER', 'USER')")
  @GetMapping("/sub-regions")
  public ResponseEntity<List<SubRegionDTO>> listSubRegions() {
    return ResponseEntity.ok(geoService.getAllSubRegions());
  }


  // --- THIS IS THE CRITICAL ENDPOINT FOR YOUR CASCADING DROPDOWN ---
  @PreAuthorize("hasAnyRole('SUPER_USER', 'USER')")
  @GetMapping("/provinces/{provinceId}/regions")
  public ResponseEntity<List<RegionDTO>> getRegionsForProvince(@PathVariable Long provinceId) {
    System.out.println("📥 Province ID received: " + provinceId);
    // THE FIX: This now correctly calls the service method that filters regions by province.
    List<RegionDTO> regions = geoService.getRegionsByProvinceId(provinceId);
    return ResponseEntity.ok(regions);
  }

  // --- CASCADING DROPDOWN (SubRegions by Region) ---
  @PreAuthorize("hasAnyRole('SUPER_USER', 'USER')")
  @GetMapping("/regions/{regionId}/sub-regions")
  public ResponseEntity<List<SubRegionDTO>> getSubRegionsForRegion(@PathVariable Long regionId) {
    List<SubRegionDTO> subRegions = geoService.getSubRegionsByRegionId(regionId);
    return ResponseEntity.ok(subRegions);
  }


  // === CREATE Endpoints ===

  @PreAuthorize("hasRole('SUPER_USER')")
  @PostMapping("/provinces")
  public Province addProvince(@RequestBody Province province) {
    return geoService.createProvince(province);
  }

  @PreAuthorize("hasRole('SUPER_USER')")
  @PostMapping("/regions/{provinceId}")
  public Region addRegion(@PathVariable Long provinceId, @RequestBody Region region) {
    return geoService.createRegion(provinceId, region);
  }

  @PreAuthorize("hasRole('SUPER_USER')")
  @PostMapping("/sub-regions/{regionId}")
  public SubRegion addSubRegion(@PathVariable Long regionId, @RequestBody SubRegion subRegion) {
    return geoService.createSubRegion(regionId, subRegion);
  }


  // === DELETE Endpoint ===

  @PreAuthorize("hasRole('SUPER_USER')")
  @DeleteMapping("/sub-regions/{id}")
  public ResponseEntity<Void> deleteSubRegion(@PathVariable Long id) {
    geoService.deleteSubRegion(id);
    return ResponseEntity.noContent().build();
  }
}
