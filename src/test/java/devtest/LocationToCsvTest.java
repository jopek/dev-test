package devtest;

import devtest.Exceptions.FileWriterException;
import devtest.entities.Suggestion;
import devtest.goeuro.LocationType;
import devtest.goeuro.SuggestionApi;
import devtest.goeuro.dto.GeoPositionDto;
import devtest.goeuro.dto.SuggestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * This test focuses on the {@link LocationToCsv} converter functionality.
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
  public void queryApiAllOk() throws FileWriterException {
    when(suggestionApi.getSuggestionByName(anyString())).thenReturn(createSuggestDtoList(2));
    locationToCsv.execute(CITY);

    verify(suggestionApi).getSuggestionByName(CITY);
    verify(csvFileWriter, times(2)).write(any(Suggestion.class));
  }

  @Test
  public void queryApiWithoutCityName() throws FileWriterException {
    locationToCsv.execute("");

    verify(suggestionApi, never()).getSuggestionByName(anyString());
  }

  @Test
  public void queryApiWithoutResults() throws FileWriterException {
    when(suggestionApi.getSuggestionByName(CITY)).thenReturn(anyListOf(SuggestDto.class));
    locationToCsv.execute(CITY);

    verify(suggestionApi).getSuggestionByName(CITY);
    verify(csvFileWriter, never()).write(any(Suggestion.class));
  }

  // convenience methods

  private List<SuggestDto> createSuggestDtoList(int count) {
    List<SuggestDto> list = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      SuggestDto dto = createSuggestDto(true);
      list.add(dto);
    }
    return list;
  }

  private SuggestDto createSuggestDto(boolean inEurope) {
    SuggestDto dto = new SuggestDto();

    if (!inEurope) {
      dto.setCoreCountry(false);
      dto.setCountry("Thailand");
      dto.setCountryCode("TH");
      dto.setDistance(null);
      dto.setFullName("Bangkok, Thailand");
      dto.setGeoPosition(new GeoPositionDto(13.6923, 100.7507));
      dto.setIata_airport_code(null);
      dto.setInEurope(false);
      dto.setKey(null);
      dto.setLocationId(38910);
      dto.setName("Bangkok");
      dto.setType(LocationType.location);

    } else {
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
    }

    return dto;
  }
}
