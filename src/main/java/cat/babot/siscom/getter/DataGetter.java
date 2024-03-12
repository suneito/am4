package cat.babot.siscom.getter;

import cat.babot.pasive.generic.Major;
import cat.babot.price.Consumables;
import cat.babot.pasive.generic.SessionVars;
import cat.babot.routes.Services;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataGetter implements Major {
  private String urlAM4 = "https://www.airlinemanager.com/";

  public String obtainTextValue(Consumables service) throws IOException {
    URL url = new URL(urlAM4.concat(service.getService()));
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("cookie", SessionVars.getValue("cookie"));
    con.setRequestMethod("GET");
    atenea.info("Connection " + con.getResponseCode() + " " + con.getResponseMessage());
    Document document = Jsoup.parse(getContent(con).toString());
    return document.selectXpath(service.getXpath()).text();
  }

  public List<String> obtainMultipleValues(String service, String xpath) throws IOException {
    URL url = new URL(urlAM4.concat(service.replace(" ", "%20")));
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("cookie", SessionVars.getValue("cookie"));
    con.setRequestMethod("GET");
    atenea.info("Connection " + con.getResponseCode() + " " + con.getResponseMessage());
    Document document = Jsoup.parse(getContent(con).toString());
    Elements elements = document.selectXpath(xpath);
    return elements.stream()
      .map(element -> element.attr("value"))
      .collect(Collectors.toList());
  }

  public List<String> obtainMultipleValues(Services service) throws IOException {
    return obtainMultipleValues(service.getService(), service.getXpath());
  }

  public Map<String, String> obtainDemandData(String service, String xpath) throws IOException {
    Map<String, String> keyValueMap = new HashMap<>();
    URL url = new URL(urlAM4.concat(service.replace(" ", "%20")));
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("cookie", SessionVars.getValue("cookie"));
    con.setRequestMethod("GET");
    atenea.info("Connection " + con.getResponseCode() + " " + con.getResponseMessage());
    Document document = Jsoup.parse(getContent(con).toString());
    Elements elements = document.selectXpath(xpath);
    keyValueMap.put("yclass", elements.attr("data-yclass"));
    keyValueMap.put("jclass", elements.attr("data-jclass"));
    keyValueMap.put("fclass", elements.attr("data-fclass"));
    keyValueMap.put("distance", elements.attr("data-distance"));
    return keyValueMap;
  }

  private static StringBuffer getContent(HttpURLConnection con) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer content = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      content.append(inputLine);
    }
    in.close();
//    atenea.info(content.toString());
    return content;
  }
}
