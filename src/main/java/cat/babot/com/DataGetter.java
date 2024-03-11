package cat.babot.com;

import cat.babot.pasive.generic.Major;
import cat.babot.pasive.Consumables;
import cat.babot.pasive.generic.SessionVars;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataGetter implements Major {

  public String obtainData(Consumables service) throws IOException {
    URL url = new URL("https://www.airlinemanager.com/".concat(service.getService()));
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("cookie", SessionVars.getValue("cookie"));
    con.setRequestMethod("GET");
    atenea.info("Connection " + con.getResponseCode() + " " + con.getResponseMessage());
    Document document = Jsoup.parse(getContent(con).toString());
    return document.selectXpath(service.getXpath()).text();
  }

  private static StringBuffer getContent(HttpURLConnection con) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer content = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      content.append(inputLine);
    }
    in.close();
    return content;
  }
}
