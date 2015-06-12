package devtest.goeuro;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created with IntelliJ IDEA.
 */
public class HttpClientFactory {

  public CloseableHttpClient createHttpClient() {
    return HttpClients.createDefault();
  }

}
