package devtest.goeuro;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * HTTP client factory - convenience class for possibility of returning mocks during tests
 */
public class HttpClientFactory {

  public CloseableHttpClient createHttpClient() {
    return HttpClients.createDefault();
  }

}
