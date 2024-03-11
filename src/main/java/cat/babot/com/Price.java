package cat.babot.com;

import cat.babot.pasive.generic.Major;
import cat.babot.pasive.Consumables;

import java.io.IOException;

public class Price implements Major {
  private final DataGetter dataGetter;
  private final Consumables value;
  private int price;

  public Price(Consumables consumable) {
    this.dataGetter = new DataGetter();
    this.value = consumable;
    refreshPrice();
  }

  private void refreshPrice() {
    int actualPrice;
    try {
      String price = dataGetter.obtainData(value);
      actualPrice = Integer.parseInt(price.replaceAll("\\D", ""));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    setPrice(actualPrice);
    atenea.info(String.format("Actual price for %s: %s", value.name(), "$" + actualPrice));
  }

  public boolean goodPrice() {
    return getPrice() <= this.value.getMaxPrice();
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return this.value.getIcon() + " $" + this.getPrice();
  }
}
