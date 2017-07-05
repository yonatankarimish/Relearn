package jsonTransformer;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Yonatan
 * Date: 15/10/16
 * Time: 18:11
 * To change this template use File | Settings | File Templates.
 */

/*
* This program was used to re-create a lost database by parsing three text files, and converting them to JSON
* The JSON was then formatted to a noSQL document format
* it was then uploaded to a mongoDB database to practice noSQL lessons.
* 15/10/2016
* */
public class Main {
    private static final Charset ENCODING = StandardCharsets.UTF_8;
    private static final String AIRPORTS =   "src/jsonTransformer/files/airports.txt";
    private static final String AIRLINES =   "src/jsonTransformer/files/airlines.txt";
    private static final String ROUTES =   "src/jsonTransformer/files/routes.txt";
    private static final String AIRPORTS_JSON =   "src/jsonTransformer/files/airports.json";
    private static final String AIRLINES_JSON =   "src/jsonTransformer/files/airlines.json";
    private static final String ROUTES_JSON =   "src/jsonTransformer/files/routes.json";
    private static final String OPENFLIGHTS_JSON =   "src/jsonTransformer/files/openFlights.json";

    private static final String[] FIX_AIRPORTS = new String[]{"_id", "name", "city", "country", "airportCode", "icaaCode", "latitude", "longitude", "altitude", "offset", "dst", "timezone"};
    private static final String[] FIX_AIRLINES = new String[]{"_id", "name", "alias", "iataCode", "icaoCode", "callsign", "country", "active"};
    private static final String[] FIX_ROUTES= new String[]{"airlineCode", "airlineID", "sourceAirportCode", "sourceAirportID", "destAirportCode", "destAirportID", "codeshare", "stops", "aircraftTypes"};

    private static String fixRow(String row)  {
            String[] keys = FIX_AIRPORTS;
            String[] values = row.split(",");
            StringBuilder fixer = new StringBuilder();
            fixer.append("{");
            for(int i=0; i<values.length; i++){
                fixer.append(keys[i]);
                fixer.append(":");
                fixer.append(values[i]);
                fixer.append(", ");
            }
            fixer.replace(fixer.lastIndexOf(", "),fixer.lastIndexOf(", ")+2,"");
            fixer.append("}\r\n");
            return fixer.toString();
    }

    private static String createRequiredEntry(int entryId, String route, String airline, String sourceAirport, String targetAirport){
        StringBuilder fixer = new StringBuilder();
        String[] aircraft = extractPropertyValue(route,"aircraftTypes").split("}")[0].split(" ");

        fixer.append("{");
        fixer.append("_id:  ");
        fixer.append(entryId);
        fixer.append(", airline: ");
        if(airline!=null){
            fixer.append("{");
            fixer.append("airlineID: ");
            fixer.append(extractId(airline));
            fixer.append(", name: ");
            fixer.append(extractPropertyValue(airline,"name"));
            fixer.append(", country: ");
            fixer.append(extractPropertyValue(airline,"country"));
            fixer.append(", active: ");
            fixer.append(extractPropertyValue(airline,"active").equals("Y")? true : false);
            fixer.append("}");
        }
        else{
            fixer.append("null");
        }
        fixer.append(", origin: ");
        if(sourceAirport != null){
            fixer.append("{");
            fixer.append("code: ");
            fixer.append(extractPropertyValue(sourceAirport,"airportCode"));
            fixer.append(", name: ");
            fixer.append(extractPropertyValue(sourceAirport,"name"));
            fixer.append(", city: ");
            fixer.append(extractPropertyValue(sourceAirport,"city"));
            fixer.append(", country: ");
            fixer.append(extractPropertyValue(sourceAirport,"country"));
            fixer.append("}");
        }
        else{
            fixer.append("null");
        }
        fixer.append(", destination: ");
        if(targetAirport != null){
            fixer.append("{");
            fixer.append("code: ");
            fixer.append(extractPropertyValue(targetAirport,"airportCode"));
            fixer.append(", name: ");
            fixer.append(extractPropertyValue(targetAirport,"name"));
            fixer.append(", city: ");
            fixer.append(extractPropertyValue(targetAirport,"city"));
            fixer.append(", country: ");
            fixer.append(extractPropertyValue(targetAirport,"country"));
            fixer.append("}");
        }
        else{
            fixer.append("null");
        }
        fixer.append(", planes: ");
        if(!aircraft[0].equals("null")){
            fixer.append("[");
            for(String plane : aircraft){
                fixer.append("\"");
                fixer.append(plane);
                fixer.append("\", ");
            }
            fixer.replace(fixer.lastIndexOf(", "),fixer.lastIndexOf(", ")+2,"");
            fixer.append("]");
        }
        else{
            fixer.append("null");
        }
        fixer.append("}");
        return fixer.toString();
    }

    private static int extractId(String row){
        return Integer.parseInt(row.split(",")[0].split(":")[1]);
    }

    private static String extractPropertyValue(String row, String propertyName){
        try{
            return row.split(propertyName+":")[1].split(",")[0];
        }
        catch (Exception e){
            System.out.println("for row: "+row);
            System.out.println("for proprty: "+propertyName);
            e.printStackTrace();
            return "null";
        }
    }

    public static void main(String[] args) throws Exception{
        //createJsonFromRawData();
        createJsonInRequiredFormat();
    }

    private static void createJsonInRequiredFormat() throws Exception{
        File airports = new File(AIRPORTS_JSON);
        File airlines = new File(AIRLINES_JSON);
        File routes = new File(ROUTES_JSON);

        Map<Integer, String> airportMap = new LinkedHashMap<Integer, String>();
        Map<Integer, String> airlineMap = new LinkedHashMap<Integer, String>();
        Map<Integer, String> routeMap = new LinkedHashMap<Integer, String>();

        BufferedReader airportReader = new BufferedReader(new FileReader(airports));
        BufferedReader airlineReader = new BufferedReader(new FileReader(airlines));
        BufferedReader routeReader = new BufferedReader(new FileReader(routes));

        for(String readRow = airportReader.readLine(); readRow!=null; readRow=airportReader.readLine())
            airportMap.put(extractId(readRow),readRow);
        for(String readRow = airlineReader.readLine(); readRow!=null; readRow=airlineReader.readLine())
            airlineMap.put(extractId(readRow), readRow);
        int index = 1;
        for(String readRow = routeReader.readLine(); readRow!=null; readRow=routeReader.readLine()){
            routeMap.put(index, readRow);
            index++;
        }
        System.out.println("Finished constructing maps");

        File outputJson = new File(OPENFLIGHTS_JSON);
        if (!outputJson.exists()) {
            outputJson.createNewFile();
        }
        FileWriter fw = new FileWriter(outputJson.getAbsoluteFile());
        BufferedWriter writer = new BufferedWriter(fw);

        for(Integer  key : routeMap.keySet()){
            String airlineEntry,  sourceAirportEntry, targetAirportEntry;
            try{
                airlineEntry = airlineMap.get(Integer.parseInt(extractPropertyValue(routeMap.get(key),"airlineID")));
            }catch (NumberFormatException e){
                airlineEntry = null;
            }
            try{
                sourceAirportEntry = airportMap.get(Integer.parseInt(extractPropertyValue(routeMap.get(key), "sourceAirportID")));
            }catch (NumberFormatException e){
                sourceAirportEntry = null;
            }
            try{
                targetAirportEntry = airportMap.get(Integer.parseInt(extractPropertyValue(routeMap.get(key), "destAirportID")));
            }catch (NumberFormatException e){
                targetAirportEntry = null;
            }

            String formattedEntry = createRequiredEntry(key, routeMap.get(key), airlineEntry,sourceAirportEntry,targetAirportEntry);
            writer.write(formattedEntry);
        }
        writer.close();
        System.out.println("Done Writing to file "+outputJson.getAbsolutePath());

    }

    //This method was run 3 times, once for each source file.
    private static void createJsonFromRawData() throws Exception{
        File inputFile = new File(AIRPORTS);
        List<String> textRows = new LinkedList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        for(String readRow = reader.readLine(); readRow!=null; readRow=reader.readLine()){
            try{
                textRows.add(fixRow(readRow));
            }
            catch (Exception e){
                System.out.println("Encountered exception: "+e.getStackTrace().toString());
                System.out.println("Exception encountered on following row: ");
                System.out.println(readRow);
            }
        }
        System.out.println("Done scanning. list has "+textRows.size()+" rows");

        File outputJson = new File(AIRPORTS_JSON);
        if (!outputJson.exists()) {
            outputJson.createNewFile();
        }
        FileWriter fw = new FileWriter(outputJson.getAbsoluteFile());
        BufferedWriter writer = new BufferedWriter(fw);
        for(String row : textRows){
            writer.write(row);
        }
        writer.close();

        System.out.println("Done Writing to file "+outputJson.getAbsolutePath());
    }
}
