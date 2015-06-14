package devtest.Exceptions;

import java.io.IOException;

/**
 * Exception to be thrown whenever the {@link devtest.CsvFileWriter} encounters an error.
 */
public class FileWriterException extends IOException {
  public FileWriterException(String message) {
    super(message);
  }

  public FileWriterException(String message, Throwable cause) {
    super(message, cause);
  }
}
