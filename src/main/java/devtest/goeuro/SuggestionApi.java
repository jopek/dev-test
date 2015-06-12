package devtest.goeuro;

import devtest.goeuro.dto.SuggestDto;

import java.util.List;

/**
 * Date: 09.06.15
 */
public interface SuggestionApi {
  List<SuggestDto> getSuggestionByName(String cityName);
}
