package devtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import devtest.goeuro.SuggestionApi;
import devtest.goeuro.SuggestionApiImpl;
import devtest.goeuro.dto.SuggestDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pron
 * Date: 12.06.15
 * Time: 12:29
 * To change this template use File | Settings | File Templates.
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
