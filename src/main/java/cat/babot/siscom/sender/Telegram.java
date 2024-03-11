package cat.babot.siscom.sender;

import cat.babot.pasive.generic.Major;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Telegram implements Major {
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
    try {
      URL obj = new URL(prepareUrl().replace("##", encodedMsg));
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setRequestMethod("GET");
      con.disconnect();
      atenea.info(String.format("Message sended - Response code: %s Response msg: %s", con.getResponseCode(), con.getResponseMessage()));
    } catch (IOException ioException) {
      atenea.info("Error sending message.");
    }
  }

  private String urlEncoder(String msg) {
    return msg.replaceAll(" ", "%20").replace("\n", "%0A");
  }
}
