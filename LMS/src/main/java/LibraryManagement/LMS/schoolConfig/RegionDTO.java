package LibraryManagement.LMS.schoolConfig;

public record RegionDTO(Long id, String name, String provinceName) {
  /** Convenience constructor to convert from an entity */
  public RegionDTO(Region region) {
    this(
      region.getId(),
      region.getName(),
      region.getProvince() != null ? region.getProvince().getName() : null
    );
  }
}
