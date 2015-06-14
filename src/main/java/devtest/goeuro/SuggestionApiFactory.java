package devtest.goeuro;

import com.fasterxml.jackson.databind.ObjectMapper;
import devtest.goeuro.dto.SuggestDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for returning implementations of the {@link SuggestionApi} interface
 */
public class SuggestionApiFactory {

  private static ObjectMapper jsonObjectMapper = new ObjectMapper();

  private static HttpClientFactory httpClientFactory = new HttpClientFactory();

  /**
   * create fake / null implementation: getSuggestionByName(String cityName) always returns empty list
   * @return
   */
  public static SuggestionApi createFake() {
    return new SuggestionApi() {
      @Override
      public List<SuggestDto> getSuggestionByName(String cityName) {
        return new ArrayList<>();
      }
    };
  }

  /**
   * create implementation that queries endpoints via HTTP
   * @return
   */
  public static SuggestionApi createImpl() {
    return new SuggestionApiImpl(httpClientFactory, jsonObjectMapper);
  }
}
