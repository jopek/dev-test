package devtest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import devtest.goeuro.SuggestionApi;
import devtest.goeuro.SuggestionApiFactory;
import devtest.goeuro.dto.SuggestDto;

import java.io.IOException;
import java.util.List;

public class Application {

  public static String csvFilename = "goeuro_test.csv";

  private CsvFileWriter csvFileWriter;

  private SuggestionApi suggestionApi;

  public Application(CsvFileWriter csvFileWriter, SuggestionApi suggestionApi) {
    this.csvFileWriter = csvFileWriter;
    this.suggestionApi = suggestionApi;
  }

  public static void main(String[] args) throws IOException {

    if (true) {
      ObjectMapper om;
      om = new ObjectMapper();

      String content = "[\n" +
          "  {\n" +
          "    \"_id\": 406676,\n" +
          "    \"key\": null,\n" +
          "    \"name\": \"Bangkok\",\n" +
          "    \"fullName\": \"Bangkok, Thailand\",\n" +
          "    \"iata_airport_code\": null,\n" +
          "    \"type\": \"location\",\n" +
          "    \"country\": \"Thailand\",\n" +
          "    \"geo_position\": {\n" +
          "      \"latitude\": 13.75398,\n" +
          "      \"longitude\": 100.50144\n" +
          "    },\n" +
          "    \"locationId\": 38910,\n" +
          "    \"inEurope\": false,\n" +
          "    \"countryCode\": \"TH\",\n" +
          "    \"coreCountry\": false,\n" +
          "    \"distance\": null\n" +
          "  }\n" +
          "]";

      List<SuggestDto> suggestListDto = om.readValue(content, new TypeReference<List<SuggestDto>>() {});


      System.out.println(suggestListDto.toString());
      return;
    }

    Application app = new Application(getCsvFileWriter(), getSuggestionApi());
    app.run(args);
  }

  private static SuggestionApi getSuggestionApi() {
    return SuggestionApiFactory.createFake();
  }

  private static CsvFileWriter getCsvFileWriter() {
    CsvFileWriter writer = new CsvFileWriter();
    try {
      writer.init(csvFilename);
    } catch (IOException e) {
      System.err.println(">>> Not starting any API queries. Fix the local IO errors first and try again.");
      System.err.println(">>> " + e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }
    return writer;
  }

  public void run(String[] args) {
    if (args.length != 1) {
      System.err.println("please supply a city name as the only parameter");
      return;
    }

    String cityName = args[0];

    LocationToCsv locationToCsv = new LocationToCsv(csvFileWriter, suggestionApi);
    locationToCsv.execute(cityName);
  }
}
