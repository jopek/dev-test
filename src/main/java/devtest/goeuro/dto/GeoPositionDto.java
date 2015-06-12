package devtest.goeuro.dto;

/**
 * Date: 09.06.15
 */
public class GeoPositionDto {

  private double latitude;

  private double longitude;

  public GeoPositionDto(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public GeoPositionDto() {
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  @Override
  public String toString() {
    return "GeoPositionDto {" +
        "latitude: " + latitude +
        "longitude: " + longitude +
        "}";
  }

}
