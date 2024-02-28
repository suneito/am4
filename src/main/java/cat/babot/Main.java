package cat.babot;

import cat.babot.com.Telegram;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class Main {
    private static final java.util.logging.Logger atenea = Logger.getLogger("Main");

    public static void main(String[] args) throws IOException {
        Telegram telegram = new Telegram(args[1], args[2], args[3]);
        URL url = new URL("https://www.airlinemanager.com/fuel.php");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("cookie", args[0]);
        con.setRequestMethod("GET");
        atenea.info(con.getResponseCode() + " " + con.getResponseMessage());
        Document document = org.jsoup.Jsoup.parse(getContent(con).toString());
        String price = document.selectXpath("//div[@id='fuelMain']//span[@class='text-danger']").text();
        atenea.info(price);
        telegram.sendMsg(formatMsg(price));
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

    private static String formatMsg(String price) {
        StringBuffer content = new StringBuffer();
        content.append("Last price change:\n")
                .append(price)
                .append("\n")
                .append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        return content.toString();
    }

}