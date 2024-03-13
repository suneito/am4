package cat.babot.pasive.generic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
  public final String filePath = "output.csv";

  public CSVWriter() {
    File file = new File(filePath);
    if (file.exists()) {
      file.delete();
    }
  }

  public void writeCSV(String[] data) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
      StringBuilder rowString = new StringBuilder();
      for (int i = 0; i < data.length; i++) {
        rowString.append(data[i]);
        if (i < data.length - 1) {
          rowString.append(",");
        }
      }
      writer.write(rowString.toString());
      writer.newLine();
    }
  }

  public void writeLine(String line) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
      writer.write(line);
      writer.newLine();
    }
  }
}
