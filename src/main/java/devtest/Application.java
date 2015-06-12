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
    Application app = new Application(getCsvFileWriter(), getSuggestionApi());
    app.run(args);
  }

  private static SuggestionApi getSuggestionApi() {
//    return SuggestionApiFactory.createFake();
    return SuggestionApiFactory.createImpl();
  }

  private static CsvFileWriter getCsvFileWriter() {
    CsvFileWriter writer = new CsvFileWriter();
    try {
      writer.init(csvFilename);
    } catch (IOException e) {
      System.out.println("Not starting any API queries. Fix the local IO errors first and try again.");
      System.out.println("> " + e.getCause());
      System.exit(1);
    }
    return writer;
  }

  public void run(String[] args) {
    if (args.length != 1) {
      System.out.println("please supply a city name as the only parameter");
      return;
    }

    String cityName = args[0];

    LocationToCsv locationToCsv = new LocationToCsv(csvFileWriter, suggestionApi);
    locationToCsv.execute(cityName);
  }
}
