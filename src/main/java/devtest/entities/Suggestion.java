package devtest.entities;

/**
 * POJO containing only information to be written to CSV file.
 */
public class Suggestion {
  private final int id;
  private final String name;
  private final String type;
  private final double latitude;
  private final double longitude;

  public Suggestion(int id, double latitude, double longitude, String name, String type) {
    this.id = id;
    this.latitude = latitude;
    this.longitude = longitude;
    this.name = name;
    this.type = type;
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
