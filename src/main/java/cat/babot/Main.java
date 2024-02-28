package cat.babot;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.airlinemanager.com/fuel.php");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("cookie", args[0]);
        con.setRequestMethod("GET");
        logger.info(con.getResponseCode() + " " + con.getResponseMessage());
        Document document = org.jsoup.Jsoup.parse(getContent(con).toString());
        logger.info(document.selectXpath("//div[@id='fuelMain']//span[@class='text-danger']").text());
    }

    private static StringBuffer getContent(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine+"\n");
        }
        in.close();
        return content;
    }

}