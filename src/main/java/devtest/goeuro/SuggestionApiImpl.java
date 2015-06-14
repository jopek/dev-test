package devtest.goeuro;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import devtest.goeuro.dto.SuggestDto;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
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
//  public static String API_URL = "http://localhost:8000/apiresponses_empty.json";
//  public static String API_URL = "http://localhost:8000/apiresponses_bkk.json";
//  public static String API_URL = "http://localhost:8000/apiresponses.json.nonexistent";

  private HttpClientFactory httpClientFactory;

  private ObjectMapper mapper;

  public SuggestionApiImpl(HttpClientFactory httpClientFactory, ObjectMapper mapper) {
    this.httpClientFactory = httpClientFactory;
    this.mapper = mapper;
  }

  @Override
  public List<SuggestDto> getSuggestionByName(String cityName) throws IOException, HttpException {
    InputStream content = retrieveContent(String.format(API_URL, cityName));
    return mapper.readValue(content, new TypeReference<List<SuggestDto>>() {});
  }

  private InputStream retrieveContent(String url) throws IOException, HttpException {
    CloseableHttpClient httpClient = httpClientFactory.createHttpClient();
    HttpGet httpGet = new HttpGet(url);

    CloseableHttpResponse response = httpClient.execute(httpGet);
    StatusLine statusLine = response.getStatusLine();
    int statusCode = statusLine.getStatusCode();

    if (statusCode > 400) {
      String reasonPhrase = statusLine.getReasonPhrase();
      throw new HttpException(String.format("HTTP status %d: %s", statusCode, reasonPhrase));
    }

    HttpEntity entity = response.getEntity();

    return entity.getContent();
  }

}
