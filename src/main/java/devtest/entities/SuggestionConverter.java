package devtest.entities;

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
    return new Suggestion(dto.getId(),
        dto.getGeoPosition().getLatitude(),
        dto.getGeoPosition().getLongitude(),
        dto.getName(),
        dto.getType().toString());
  }
}
