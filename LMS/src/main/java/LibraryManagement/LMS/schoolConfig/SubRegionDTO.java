package LibraryManagement.LMS.schoolConfig;

public record SubRegionDTO(Long id, String name, String regionName, String provinceName) {
  /** Convenience constructor to convert from an entity */
  public SubRegionDTO(SubRegion subRegion) {
    this(
      subRegion.getId(),
      subRegion.getName(),
      subRegion.getRegion() != null ? subRegion.getRegion().getName() : null,
      (subRegion.getRegion() != null && subRegion.getRegion().getProvince() != null)
        ? subRegion.getRegion().getProvince().getName()
        : null
    );
  }
}
