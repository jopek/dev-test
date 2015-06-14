package devtest.goeuro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import devtest.goeuro.LocationType;

/**
 * direct mapping of api endpoint returned JSON to data transfer object / plain old java object
 */
public class SuggestDto {
  private boolean coreCountry;

  private String country;

  private String countryCode;

  private Integer distance;

  private String fullName;

  @JsonProperty("geo_position")
  private GeoPositionDto geoPosition;

  private String iata_airport_code;

  @JsonProperty("_id")
  private int id;

  private boolean inEurope;

  private String key;

  private Integer locationId;

  private String name;

  private LocationType type;

  public SuggestDto() {
  }

  public boolean isCoreCountry() {
    return coreCountry;
  }

  public void setCoreCountry(boolean coreCountry) {
    this.coreCountry = coreCountry;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public Integer getDistance() {
    return distance;
  }

  public void setDistance(Integer distance) {
    this.distance = distance;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public GeoPositionDto getGeoPosition() {
    return geoPosition;
  }

  public void setGeoPosition(GeoPositionDto geoPosition) {
    this.geoPosition = geoPosition;
  }

  public String getIata_airport_code() {
    return iata_airport_code;
  }

  public void setIata_airport_code(String iata_airport_code) {
    this.iata_airport_code = iata_airport_code;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isInEurope() {
    return inEurope;
  }

  public void setInEurope(boolean inEurope) {
    this.inEurope = inEurope;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Integer getLocationId() {
    return locationId;
  }

  public void setLocationId(Integer locationId) {
    this.locationId = locationId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocationType getType() {
    return type;
  }

  public void setType(LocationType type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "SuggestDto {" +
        "coreCountry: " + coreCountry +
        " country: " + country +
        " countryCode: " + countryCode +
        " distance: " + distance +
        " fullName: " + fullName +
        " geoPosition: " + geoPosition +
        " iata_airport_code: " + iata_airport_code +
        " id: " + id +
        " inEurope: " + inEurope +
        " key: " + key +
        " locationId: " + locationId +
        " name: " + name +
        " type: " + type +
        "}";
  }
}
