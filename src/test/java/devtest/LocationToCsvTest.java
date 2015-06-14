package devtest;

import devtest.entities.Suggestion;
import devtest.goeuro.LocationType;
import devtest.goeuro.SuggestionApi;
import devtest.goeuro.dto.GeoPositionDto;
import devtest.goeuro.dto.SuggestDto;
import org.apache.http.HttpException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * This test focuses on the {@link LocationToCsv} "glue" functionality and general exception handling.
 */

@RunWith(MockitoJUnitRunner.class)
public class LocationToCsvTest {

  public static final String CITY = "someCity";

  @Mock
  private CsvFileWriter csvFileWriter;

  @Mock
  private SuggestionApi suggestionApi;

  private LocationToCsv locationToCsv;

  @Before
  public void setup() {
    locationToCsv = new LocationToCsv(csvFileWriter, suggestionApi);
  }

  @Test
  public void queryApiAllOk() throws IOException, HttpException {
    when(suggestionApi.getSuggestionByName(anyString())).
        thenReturn(createSuggestDtoList(2));
    locationToCsv.execute(CITY);

    verify(suggestionApi).getSuggestionByName(CITY);
    verify(csvFileWriter, times(2)).write(any(Suggestion.class));
  }

  @Test
  public void queryApiWithoutCityName() throws IOException, HttpException {
    locationToCsv.execute("");

    verify(suggestionApi, never()).getSuggestionByName(anyString());
  }

  @Test
  public void queryApiWithoutResults() throws IOException, HttpException {
    when(suggestionApi.getSuggestionByName(CITY)).thenReturn(anyListOf(SuggestDto.class));
    locationToCsv.execute(CITY);

    verify(suggestionApi).getSuggestionByName(CITY);
    verify(csvFileWriter, never()).write(any(Suggestion.class));
  }

  @Test
  public void queryApiWithNetworkException() throws IOException, HttpException {
    doThrow(new IOException("laaaa")).when(suggestionApi).getSuggestionByName(CITY);
    locationToCsv.execute(CITY);

    verify(suggestionApi).getSuggestionByName(CITY);
    verify(csvFileWriter, never()).write(any(Suggestion.class));
    verify(csvFileWriter, never()).close();
  }

  @Test
  public void queryApiWithHttpException() throws IOException, HttpException {
    doThrow(new HttpException("404: File not Found")).when(suggestionApi).getSuggestionByName(CITY);
    locationToCsv.execute(CITY);

    verify(suggestionApi).getSuggestionByName(CITY);
    verify(csvFileWriter, never()).write(any(Suggestion.class));
    verify(csvFileWriter, never()).close();
  }

  // convenience methods

  private List<SuggestDto> createSuggestDtoList(int count) {
    List<SuggestDto> list = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      SuggestDto dto = createSuggestDto();
      list.add(dto);
    }
    return list;
  }

  private SuggestDto createSuggestDto() {
    SuggestDto dto = new SuggestDto();
    dto.setCoreCountry(true);
    dto.setCountry("Germany");
    dto.setCountryCode("DE");
    dto.setDistance(null);
    dto.setFullName("Berlin Schönefeld (SXF), Germany");
    dto.setGeoPosition(new GeoPositionDto(52.3887261, 13.5180874));
    dto.setIata_airport_code("SXF");
    dto.setInEurope(true);
    dto.setKey(null);
    dto.setLocationId(null);
    dto.setName("Berlin Schönefeld");
    dto.setType(LocationType.airport);
    return dto;
  }
}
