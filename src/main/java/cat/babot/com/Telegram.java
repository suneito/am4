package cat.babot.com;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class Telegram {
    private final Logger atenea = Logger.getLogger("Telegram");
    private String botId;
    private String token;
    private String chatId;

    String prepUrl;

    public Telegram(String botId, String token, String chatId) {
        this.botId = botId;
        this.token = token;
        this.chatId = chatId;
    }

    private String prepareUrl() {
        StringBuffer urlBuffer = new StringBuffer("https://api.telegram.org/");
        urlBuffer.append(botId)
                .append(':')
                .append(token).append('/')
                .append("sendMessage?chat_id=")
                .append(chatId).append("&text=##")
                .append("&parse_mode=HTML");
        return prepUrl = urlBuffer.toString();
    }

    public void sendMsg(String msg) {
        String encodedMsg = urlEncoder(msg);
        System.out.print(encodedMsg);
        try {
            URL obj = new URL(prepareUrl().replace("##", encodedMsg));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.disconnect();
            atenea.info("Sending message. Response: " + con.getResponseCode());
        } catch (IOException ioException) {
            atenea.info("Error sending message.");
        }
    }

    private String urlEncoder(String msg) {
        return msg.replaceAll(" ", "%20").replace("\n", "%0A");
    }
}
