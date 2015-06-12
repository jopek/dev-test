package devtest.goeuro;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import devtest.goeuro.dto.SuggestDto;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.InputStream;
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
  public List<SuggestDto> getSuggestionByName(String cityName) throws IOException {
    InputStream content = retrieveContent(String.format(API_URL, cityName));
    return mapper.readValue(content, new TypeReference<List<SuggestDto>>() {});
  }

  private InputStream retrieveContent(String url) throws IOException {
    CloseableHttpClient httpClient = httpClientFactory.createHttpClient();
    HttpGet httpGet = new HttpGet(url);

    CloseableHttpResponse response = httpClient.execute(httpGet);
    HttpEntity entity = response.getEntity();

    httpClient.close();
    response.close();
    return entity.getContent();
  }

}
