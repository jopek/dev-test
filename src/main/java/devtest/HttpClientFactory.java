package devtest;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created with IntelliJ IDEA.
 * User: pron
 * Date: 12.06.15
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
public class HttpClientFactory {

  public CloseableHttpClient createHttpClient() {
    return HttpClients.createDefault();
  }

}
