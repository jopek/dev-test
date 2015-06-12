package devtest.entities;

/**
 * Date: 09.06.15
 */
public class Suggestion {
  private int id;
  private String name;
  private String type;
  private double latitude;
  private double longitude;

  public Suggestion(int id, double latitude, double longitude, String name, String type) {
    this.id = id;
    this.latitude = latitude;
    this.longitude = longitude;
    this.name = name;
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String toCsv() {
    return String.format("%d,%s,%s,%f,%f\n", id, name, type, latitude, longitude);
  }

  @Override
  public String toString() {
    return String.format("Id: %d, Name: %s, Type: %s, Latitude: %f, Longitude: %f",
        id, name, type, latitude, longitude);
  }
}
