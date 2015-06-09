package devtest.goeuro;

import devtest.goeuro.dto.SuggestDto;

/**
 * Created with IntelliJ IDEA.
 * User: pron
 * Date: 09.06.15
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */
public interface SuggestionApi {
  SuggestDto getSuggestionByName(String cityName);
}
