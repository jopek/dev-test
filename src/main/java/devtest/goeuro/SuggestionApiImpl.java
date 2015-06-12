package devtest.goeuro;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import devtest.goeuro.dto.SuggestDto;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 */

public class SuggestionApiImpl implements SuggestionApi {
  public static String API_URL = "http://api.goeuro.com/api/v2/position/suggest/en/%s";

  private HttpClientFactory httpClientFactory;

  private ObjectMapper mapper;

  public SuggestionApiImpl(HttpClientFactory httpClientFactory, ObjectMapper mapper) {
    this.httpClientFactory = httpClientFactory;
    this.mapper = mapper;
  }

  @Override
  public List<SuggestDto> getSuggestionByName(String cityName) {
    return executeAndHandle(String.format(API_URL, cityName));
  }

  private List<SuggestDto> executeAndHandle(String url) {
    CloseableHttpClient httpClient = httpClientFactory.createHttpClient();

    HttpGet httpGet = new HttpGet(url);

    try {
      CloseableHttpResponse response = httpClient.execute(httpGet);
      HttpEntity entity = response.getEntity();
      InputStream content = entity.getContent();

      return mapper.readValue(content, new TypeReference<List<SuggestDto>>() {});

    } catch (ClientProtocolException e) {
      e.printStackTrace();
      return new ArrayList<>();

    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }

  }

}
