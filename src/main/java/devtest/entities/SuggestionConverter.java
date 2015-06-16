package devtest.entities;

import devtest.goeuro.LocationType;
import devtest.goeuro.dto.GeoPositionDto;
import devtest.goeuro.dto.SuggestDto;

/**
 * helper class to pick out relevant information.
 */
public class SuggestionConverter {

  /**
   * convert {@link SuggestDto} to {@link Suggestion} entity
   *
   * @param dto
   * @return
   */
  public static Suggestion toEntity(SuggestDto dto) {
    if (dto == null) {
      dto = new SuggestDto();
    }

    GeoPositionDto geoPosition = dto.getGeoPosition();
    double latitude;
    double longitude;

    if (geoPosition == null) {
      latitude = 0;
      longitude = 0;
    } else {
      latitude = geoPosition.getLatitude();
      longitude = geoPosition.getLongitude();
    }

    String type;
    LocationType locationType = dto.getType();

    if (locationType == null) {
      type = LocationType.unknown.toString();
    } else {
      type = locationType.toString();
    }

    return new Suggestion(dto.getId(), latitude, longitude, dto.getName(), type);
  }
}
