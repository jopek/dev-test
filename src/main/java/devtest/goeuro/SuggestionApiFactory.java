package devtest.goeuro;

import com.fasterxml.jackson.databind.ObjectMapper;
import devtest.goeuro.dto.SuggestDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 */
public class SuggestionApiFactory {

  private static ObjectMapper jsonObjectMapper = new ObjectMapper();

  private static HttpClientFactory httpClientFactory = new HttpClientFactory();

  public static SuggestionApi createFake() {
    return new SuggestionApi() {
      @Override
      public List<SuggestDto> getSuggestionByName(String cityName) {
        return new ArrayList<>();
      }
    };
  }

  public static SuggestionApi createImpl() {
    return new SuggestionApiImpl(httpClientFactory, jsonObjectMapper);
  }
}
