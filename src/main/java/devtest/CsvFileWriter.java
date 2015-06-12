package devtest;

import devtest.Exceptions.FileWriterException;
import devtest.entities.Suggestion;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Date: 09.06.15
 */
public class CsvFileWriter {
  FileWriter fileWriter;

  private String filename;

  public CsvFileWriter() {
  }

  public void init(String filename) throws FileWriterException {
    this.filename = filename;
    try {
      fileWriter = new FileWriter(filename, true);
    } catch (IOException e) {
      throw new FileWriterException("FileWriter failed to initialize...", e);
    }
  }

  public void write(Suggestion suggestion) throws FileWriterException {
    if (fileWriter == null) {
      throw new FileWriterException("FileWriter uninitialized. call " + getClass().getCanonicalName() + ".init()");
    }

    try {
      fileWriter.append(suggestion.toCsv());
    } catch (IOException writeException) {
      throw new FileWriterException("Failed to write to " + filename + "CSV file.");
    }


  }

}
