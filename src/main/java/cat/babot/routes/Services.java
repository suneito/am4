package cat.babot.routes;

public enum Services {
  COUNTRY("research_main.php", "//select[@id='countrySelector']//option[@value]"),
  AIRPORT("flight_infoair.php?mode=cityList&country=", "//select[@id='citySelectorView']//option[@value]"),
  DEMAND("research_main.php?mode=search&rwy=1000&dist=2000&depId=#DEP&arrId=#ARR", "//div[@id='list']/div");

  private final String service;
  private final String xpath;

  Services(String service, String xpath) {
    this.service = service;
    this.xpath = xpath;
  }

  public String getService() {
    return this.service;
  }

  public String getXpath() {
    return this.xpath;
  }

}
