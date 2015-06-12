package devtest.goeuro;

import com.fasterxml.jackson.databind.ObjectMapper;
import devtest.goeuro.dto.SuggestDto;
import devtest.goeuro.dto.SuggestListDto;
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
import java.util.ArrayList;

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

  @Mock
  private CloseableHttpResponse closeableHttpResponse;

  @Mock
  private HttpEntity httpEntity;

  @Mock
  private InputStream inputStream;

  @Mock
  private ObjectMapper jsonObjectMapper;

  @Mock
  private SuggestListDto suggestListDto;

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
    when(jsonObjectMapper.readValue(inputStream, SuggestListDto.class)).thenReturn(suggestListDto);
    when(suggestListDto.getSuggestions()).thenReturn(new ArrayList<SuggestDto>());

    suggestionApi.getSuggestionByName(CITY);
    verify(httpClientFactory).createHttpClient();
    verify(closeableHttpClient).execute(httpGetArgumentCaptor.capture());
    String actualGetUri = httpGetArgumentCaptor.getValue().getURI().toString();
    assertEquals(String.format(SuggestionApiImpl.API_URL, CITY), actualGetUri);
    verify(jsonObjectMapper).readValue(eq(inputStream), eq(SuggestListDto.class));
    verify(suggestListDto).getSuggestions();
  }

  @Test
  public void makeCallFailsBecauseTransportError() throws IOException {
    doThrow(new IOException()).when(closeableHttpClient).execute(any(HttpGet.class));

    suggestionApi.getSuggestionByName(CITY);
    verify(jsonObjectMapper, never()).readValue(eq(inputStream), eq(SuggestListDto.class));
    verify(suggestListDto, never()).getSuggestions();
  }

}
