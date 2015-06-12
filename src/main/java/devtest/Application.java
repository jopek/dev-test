package devtest;

import devtest.goeuro.SuggestionApi;
import devtest.goeuro.dto.SuggestDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

  public static String csvFilename = "goeuro_test.csv";

  private CsvFileWriter csvFileWriter;

  private SuggestionApi suggestionApi;

  public static void main(String[] args) {
    Application app = new Application(getCsvFileWriter(), getSuggestionApi());
    app.run(args);
  }

  public Application(CsvFileWriter csvFileWriter,
      SuggestionApi suggestionApi) {
    this.csvFileWriter = csvFileWriter;
    this.suggestionApi = suggestionApi;
  }

  private static SuggestionApi getSuggestionApi() {
    SuggestionApi api = new SuggestionApi() {
      @Override
      public List<SuggestDto> getSuggestionByName(String cityName) {
        return new ArrayList<>();
      }
    };
    return api;
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
