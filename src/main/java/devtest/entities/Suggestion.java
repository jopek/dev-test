package devtest.entities;

/**
 * POJO containing only information to be written to CSV file.
 */
public class Suggestion {

  public static final String CSV_FORMAT = "%d,%s,%s,%f,%f\n";

  public static final String STRING_FORMAT = "Id: %d, Name: %s, Type: %s, Latitude: %f, Longitude: %f";

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
    return String.format(CSV_FORMAT, id, name, type, latitude, longitude);
  }

  @Override
  public String toString() {
    return String.format(STRING_FORMAT,
        id, name, type, latitude, longitude);
  }
}
