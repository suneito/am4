package cat.babot.routes;

import cat.babot.pasive.generic.CSVWriter;
import cat.babot.pasive.generic.Major;
import cat.babot.siscom.getter.DataGetter;
import cat.babot.pasive.generic.SessionVars;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ObtainRoutes implements Major {
  public static void main(String[] args) throws IOException {
    SessionVars.setValue("cookie", args[0]);
    String departureID = args[1];
    DataGetter dg = new DataGetter();
    CSVWriter writer = new CSVWriter();
    writer.writeLine("COUNTRY,CITY,AIRPORT_ID,Y_CLASS,J_CLASS,F_CLASS,DISTANCE,BASE;");
    List<String> countriesList = dg.obtainMultipleValues(Services.COUNTRY);

    for (String country : countriesList) {
      List<String> airportsList = dg.obtainMultipleValues(Services.AIRPORT.getService().concat(country), Services.AIRPORT.getXpath());
      for (String airport : airportsList) {
        String[] content = airport.split(",");
        Map<String, String> demandList = dg.obtainDemandData(
          Services.DEMAND.getService().replace("#DEP",
            departureID).replace("#ARR",
            content[0]),
          Services.DEMAND.getXpath());
        StringBuilder sb = new StringBuilder();
        sb.append(country).append(",");
        sb.append(content[1]).append(",");
        sb.append(content[0]).append(",");
        sb.append(demandList.get("yclass")).append(",");
        sb.append(demandList.get("jclass")).append(",");
        sb.append(demandList.get("fclass")).append(",");
        sb.append(demandList.get("distance"));
        sb.append(demandList.get(departureID));
        try {
          writer.writeCSV(new String[]{country,content[0],content[1],demandList.get("yclass"),demandList.get("jclass"),demandList.get("fclass"),demandList.get("distance")});
        } catch (IOException e) {
          atenea.info("An error occurred while writing the CSV file: " + e.getMessage());
        }
      }
    }
  }
}
