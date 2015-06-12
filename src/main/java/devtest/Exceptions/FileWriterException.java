package devtest.Exceptions;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: pron
 * Date: 10.06.15
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
public class FileWriterException extends IOException {
  public FileWriterException(String message) {
    super(message);
  }

  public FileWriterException(String message, Throwable cause) {
    super(message, cause);
  }
}
