package cat.babot.pasive.generic;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
  public static void writeCSV(String[] data, String filePath) throws IOException {
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(filePath, true));
      StringBuilder rowString = new StringBuilder();
      for (int i = 0; i < data.length; i++) {
        rowString.append(data[i]);
        if (i < data.length - 1) {
          rowString.append(",");
        }
      }
      writer.write(rowString.toString());
      writer.newLine();
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
  }
}
