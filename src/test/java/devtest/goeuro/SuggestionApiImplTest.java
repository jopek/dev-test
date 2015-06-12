package devtest.goeuro;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import devtest.goeuro.dto.SuggestDto;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 */

@RunWith(MockitoJUnitRunner.class)
public class SuggestionApiImplTest {

  private static String CITY = "some_city";

  @Mock
  private HttpClientFactory httpClientFactory;

  @Mock
  private CloseableHttpClient closeableHttpClient;

  @Captor
  private ArgumentCaptor<HttpGet> httpGetArgumentCaptor;

  @Captor
  private ArgumentCaptor<TypeReference<List<SuggestDto>>> typeReferenceArgumentCaptor;

  @Mock
  private CloseableHttpResponse closeableHttpResponse;

  @Mock
  private HttpEntity httpEntity;

  @Mock
  private InputStream inputStream;

  @Mock
  private ObjectMapper jsonObjectMapper;

  private List<SuggestDto> suggestDtoList;

  private SuggestionApi suggestionApi;

  @Before
  public void setup() {
    suggestionApi = new SuggestionApiImpl(httpClientFactory, jsonObjectMapper);
    when(httpClientFactory.createHttpClient()).thenReturn(closeableHttpClient);
  }

  @Test
  public void makeCall() throws IOException {
    when(closeableHttpClient.execute(any(HttpGet.class))).thenReturn(closeableHttpResponse);
    when(closeableHttpResponse.getEntity()).thenReturn(httpEntity);
    when(httpEntity.getContent()).thenReturn(inputStream);
    when(jsonObjectMapper.readValue(inputStream, TypeReference.class)).thenReturn(suggestDtoList);

    suggestionApi.getSuggestionByName(CITY);
    verify(httpClientFactory).createHttpClient();
    verify(closeableHttpClient).execute(httpGetArgumentCaptor.capture());
    String actualGetUri = httpGetArgumentCaptor.getValue().getURI().toString();
    assertEquals(String.format(SuggestionApiImpl.API_URL, CITY), actualGetUri);
    verify(jsonObjectMapper).readValue(eq(inputStream), typeReferenceArgumentCaptor.capture());
    assertEquals("ll", typeReferenceArgumentCaptor.getValue().getType());
  }

  @Test
  public void makeCallFailsBecauseTransportError() throws IOException {
    doThrow(new IOException()).when(closeableHttpClient).execute(any(HttpGet.class));

    suggestionApi.getSuggestionByName(CITY);
    verify(jsonObjectMapper, never()).readValue(eq(inputStream), any(TypeReference.class));
  }

  @Test
  public void makeCallFailsBecauseJsonParsingError() throws IOException {
    when(closeableHttpClient.execute(any(HttpGet.class))).thenReturn(closeableHttpResponse);
    when(closeableHttpResponse.getEntity()).thenReturn(httpEntity);
    when(httpEntity.getContent()).thenReturn(inputStream);
    doThrow(new JsonParseException("couldn't parse.", JsonLocation.NA)).when(jsonObjectMapper)
        .readValue(eq(inputStream), any(TypeReference.class));

    suggestionApi.getSuggestionByName(CITY);
//    verify(jsonObjectMapper).readValue(eq(inputStream), any(TypeReference.class));
  }

}
