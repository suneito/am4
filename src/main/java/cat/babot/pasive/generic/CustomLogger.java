package cat.babot.pasive.generic;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.LogRecord;

public class CustomLogger {
  private final Logger logger;

  public CustomLogger(String name) {
    logger = Logger.getLogger(name);
    ConsoleHandler consoleHandler = new ConsoleHandler();
    consoleHandler.setFormatter(new CustomFormatter());
    logger.setUseParentHandlers(false);
    logger.addHandler(consoleHandler);
  }

  public void info(String message) {
    logger.log(Level.INFO, message);
  }

  public void warning(String message) {
    logger.log(Level.WARNING, message);
  }

  public void error(String message) {
    logger.log(Level.SEVERE, message);
  }

  private static class CustomFormatter extends SimpleFormatter {
    @Override
    public synchronized String format(LogRecord record) {
      StringBuilder sb = new StringBuilder();
      sb.append("[").append(record.getLevel().getName()).append("] ");
      sb.append(record.getMessage()).append("\n");
      return sb.toString();
    }
  }
}
