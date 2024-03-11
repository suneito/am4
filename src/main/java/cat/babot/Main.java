package cat.babot;

import cat.babot.com.Telegram;
import cat.babot.pasive.Consumables;
import cat.babot.pasive.generic.Major;
import cat.babot.com.Price;
import cat.babot.pasive.generic.SessionVars;

public class Main implements Major {
  public static void main(String[] args) {
    SessionVars.setValue("cookie", args[0]);
    SessionVars.setValue("bot", args[1]);
    SessionVars.setValue("token", args[2]);
    SessionVars.setValue("chat", args[3]);

    Telegram telegram = new Telegram(
      SessionVars.getValue("bot"),
      SessionVars.getValue("token"),
      SessionVars.getValue("chat"));

    Price fuelPrice = new Price(Consumables.FUEL);
    Price co2Price = new Price(Consumables.CO2);
    StringBuilder sbMSG = new StringBuilder();

    if(fuelPrice.goodPrice()) {
      sbMSG.append(fuelPrice);
      sbMSG.append("\n");
    }
    if(co2Price.goodPrice()) {
      sbMSG.append(co2Price);
      sbMSG.append("\n");
    }

    if (!sbMSG.isEmpty()) {
    telegram.sendMsg(sbMSG.toString());
    } else {
      atenea.info("No message sended.");
    }
  }
}