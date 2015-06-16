package devtest.entities;

import devtest.goeuro.LocationType;
import devtest.goeuro.dto.GeoPositionDto;
import devtest.goeuro.dto.SuggestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link SuggestionConverter}
 */
@RunWith(MockitoJUnitRunner.class)
public class SuggestionConverterTest {

  @Test
  public void allFieldsPresent() {
    SuggestDto dto = createSuggestDto();
    Suggestion entity = SuggestionConverter.toEntity(dto);

    //id, name, type, latitude, longitude
    assertEquals(String.format(Suggestion.CSV_FORMAT,
        dto.getId(),
        dto.getName(),
        dto.getType(),
        dto.getGeoPosition().getLatitude(),
        dto.getGeoPosition().getLongitude()), entity.toCsv());
  }

  @Test
  public void typeMissing() {
    SuggestDto dto = createSuggestDto();
    dto.setType(null);
    Suggestion entity = SuggestionConverter.toEntity(dto);

    //id, name, type, latitude, longitude
    assertEquals(String.format(Suggestion.CSV_FORMAT,
        dto.getId(),
        dto.getName(),
        LocationType.unknown,
        dto.getGeoPosition().getLatitude(),
        dto.getGeoPosition().getLongitude()), entity.toCsv());
  }

  @Test
  public void geoPositionMissing() {
    SuggestDto dto = createSuggestDto();
    dto.setGeoPosition(null);
    Suggestion entity = SuggestionConverter.toEntity(dto);

    //id, name, type, latitude, longitude
    assertEquals(String.format(Suggestion.CSV_FORMAT, dto.getId(), dto.getName(), dto.getType(), 0d, 0d),
        entity.toCsv());
  }

  @Test
  public void nameMissing() {
    SuggestDto dto = createSuggestDto();
    dto.setName(null);
    Suggestion entity = SuggestionConverter.toEntity(dto);

    //id, name, type, latitude, longitude
    assertEquals(String.format(Suggestion.CSV_FORMAT,
        dto.getId(),
        "null",
        dto.getType(),
        dto.getGeoPosition().getLatitude(),
        dto.getGeoPosition().getLongitude()), entity.toCsv());
  }

  @Test
  public void allMissing() {
    SuggestDto dto = new SuggestDto();
    dto.setName(null);
    Suggestion entity = SuggestionConverter.toEntity(dto);

    //id, name, type, latitude, longitude
    assertEquals(String.format(Suggestion.CSV_FORMAT, 0, "null", LocationType.unknown, 0d, 0d), entity.toCsv());
  }

  @Test
  public void dtoIsNull() {
    Suggestion entity = SuggestionConverter.toEntity(null);

    //id, name, type, latitude, longitude
    assertEquals(String.format(Suggestion.CSV_FORMAT, 0, "null", LocationType.unknown, 0d, 0d), entity.toCsv());
  }

  private SuggestDto createSuggestDto() {
    SuggestDto dto = new SuggestDto();
    dto.setId(123);
    dto.setCoreCountry(true);
    dto.setCountry("XISTAN");
    dto.setCountryCode("XX");
    dto.setDistance(null);
    dto.setFullName("some really nice place");
    dto.setGeoPosition(new GeoPositionDto(11.11, 22.22));
    dto.setIata_airport_code("XXX");
    dto.setInEurope(true);
    dto.setKey(null);
    dto.setLocationId(null);
    dto.setName("name");
    dto.setType(LocationType.location);
    return dto;
  }

}
