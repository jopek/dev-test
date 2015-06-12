package devtest.entities;

import devtest.goeuro.dto.SuggestDto;

/**
 * Created with IntelliJ IDEA.
 * User: pron
 * Date: 09.06.15
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class SuggestionConverter {
  public static Suggestion toEntity(SuggestDto dto){
    return new Suggestion(
        dto.getId(),
        dto.getGeoPosition().getLatitude(),
        dto.getGeoPosition().getLongitude(),
        dto.getName(),
        dto.getType().toString());
  }
}
