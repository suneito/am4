package cat.babot.pasive;

public enum Consumables {
  FUEL("fuel.php", "//div[@id='fuelMain']//span[@class='text-danger']", 450, "⛽"),
  CO2("co2.php", "//div[@id='co2Main']//span[@class='text-danger']", 130, "\uD83C\uDF3F");

  final String service;
  final String xpath;
  final int maxPrice;
  final String icon;

  Consumables(String service, String xpath, int maxPrice, String icon) {
    this.service = service;
    this.xpath = xpath;
    this.maxPrice = maxPrice;
    this.icon = icon;
  }

  public String getService() {
    return this.service;
  }

  public String getXpath() {
    return this.xpath;
  }

  public int getMaxPrice() {
    return this.maxPrice;
  }

  public String getIcon() {
    return this.icon;
  }

}