package devtest;

import devtest.goeuro.LocationType;
import devtest.goeuro.SuggestionApi;
import devtest.goeuro.dto.GeoPositionDto;
import devtest.goeuro.dto.SuggestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.FileWriter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * This test focuses on the {@link LocationToCsv} converter functionality.
 */

@RunWith(MockitoJUnitRunner.class)
public class TestApplicationTests {

  @Mock
  private FileWriter fileWriter;

  @Mock
  private SuggestionApi suggestionApi;

  private LocationToCsv locationToCsv;

  @Before
  public void setup() {
    when(suggestionApi.getSuggestionByName(anyString())).thenReturn(createSuggestDto(true));
    locationToCsv = new LocationToCsv(suggestionApi, fileWriter);
  }

  // ==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ====

  @Test
  public void writeFileOn() {
  }

  // ==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ====

  private SuggestDto createSuggestDto(boolean inEurope) {
    SuggestDto dto = new SuggestDto();

    if (inEurope) {
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
      dto.setType(LocationType.LOCATION);

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
      dto.setType(LocationType.AIRPORT);
    }

    return dto;
  }
}
