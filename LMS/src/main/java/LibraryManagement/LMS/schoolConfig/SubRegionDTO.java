package LibraryManagement.LMS.schoolConfig;

import lombok.Data;

@Data
public class SubRegionDTO {
  private Long id;
  private String name;
  private Long regionId;
  private String regionName;
  private Long provinceId;
  private String provinceName;

  public SubRegionDTO(SubRegion subRegion) {
    this.id = subRegion.getId();
    this.name = subRegion.getName();
    if (subRegion.getRegion() != null) {
      this.regionId = subRegion.getRegion().getId();
      this.regionName = subRegion.getRegion().getName();
      if (subRegion.getRegion().getProvince() != null) {
        this.provinceId = subRegion.getRegion().getProvince().getId();
        this.provinceName = subRegion.getRegion().getProvince().getName();
      }
    }
  }
}
