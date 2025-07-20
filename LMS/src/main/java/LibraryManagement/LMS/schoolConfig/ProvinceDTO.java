package LibraryManagement.LMS.schoolConfig;

public record ProvinceDTO(Long id, String name) {
  /** Convenience constructor to convert from an entity */
  public ProvinceDTO(Province province) {
    this(province.getId(), province.getName());
  }
}
