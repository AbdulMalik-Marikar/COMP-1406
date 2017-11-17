/**
 * Abdul-Malik Marikar
 * 101042166
 */
import java.util.*;

public class DispatchCenter {
    public static String[] AREA_NAMES = {"Downtown", "Airport", "North", "South", "East", "West"};

    private int[][]  stats; // You'll need this for the last part of the assignment
    private HashMap<Integer,Taxi>  taxis = new HashMap<Integer, Taxi>();
    private HashMap<String,ArrayList<Taxi>> areas = new HashMap<String,ArrayList<Taxi>>();
    public HashMap<Integer, Taxi> getTaxis() {
        return taxis;
    }
    public HashMap<String, ArrayList<Taxi>> getAreas() {
        return areas;
    }

    // Constructor
    public DispatchCenter() {
        // You'll need this for the last part of the assignment
        stats = new int[AREA_NAMES.length][AREA_NAMES.length];
        for(int i=0;i<AREA_NAMES.length;i++){
            areas.put(AREA_NAMES[i], new ArrayList<>());
        }
        for(int i=0;i<50;i++){
            int number = (int)(Math.random()*999);
            while(number<100){
                number = (int)(Math.random()*999);
            }
            int newNumber = (int) (Math.random()*6);
            addTaxi(new Taxi(number), AREA_NAMES[newNumber]);
        }
    }


    // You'll need this for the last part of the assignment
    public int[][]   getStats() { return stats; }


    // Update the statistics for a taxi going from the pickup location to the dropoff location
    public void updateStats(String pickup, String dropOff) {
        for (int i=0;i<AREA_NAMES.length;i++){
            if(AREA_NAMES[i].equals(pickup)){
                for (int j=0;j<AREA_NAMES.length;j++){
                    if(AREA_NAMES[j].equals(dropOff)){
                        stats[j][i]++;
                    }
                }
            }
        }

    }

    // Determine the travel times from one area to another
    public static int computeTravelTimeFrom(String pickup, String dropOff) {
        int pickup1=0;
        int dropoff1=0;
        int[][] Timetravel = {{10,20,40,40,40,60},//Airport
                {40,20,10,20,40,20},//North
                {20,10,20,20,20,40},//East
                {40,20,20,10,20,20},//Downtown
                {40,20,40,20,10,20},//South
                {60,40,20,20,20,10}};//West

        switch (pickup){
            case "Airport": pickup1=0; break;
            case "North": pickup1=1; break;
            case "East": pickup1=2; break;
            case "Downtown": pickup1=3; break;
            case "South": pickup1=4; break;
            case "West": pickup1=5; break;
        }
        switch (dropOff){
            case "Airport": dropoff1=0; break;
            case "North": dropoff1=1; break;
            case "East": dropoff1=2; break;
            case "Downtown": dropoff1=3; break;
            case "South": dropoff1=4; break;
            case "West": dropoff1=5; break;
        }
        return Timetravel[pickup1][dropoff1];

    }

    // Add a taxi to the hashmaps
    public void addTaxi(Taxi aTaxi, String area) {
        taxis.put(aTaxi.getPlateNumber(),aTaxi);
        if(!areas.containsKey(area))
            areas.put(area,new ArrayList<Taxi>());
        areas.get(area).add(aTaxi);

    }

    // Return a list of all available taxis within a certain area
    private ArrayList<Taxi> availableTaxisInArea(String s) {
        ArrayList<Taxi> result = new ArrayList<Taxi>();
        for(Taxi t: areas.get(s)){
            if(t.getAvailable()){
                result.add(t);
            }
        }

        return result;
    }

    // Return a list of all busy taxis
    public ArrayList<Taxi> getBusyTaxis() {
        ArrayList<Taxi> result = new ArrayList<Taxi>();
        for(String s:AREA_NAMES){
            for(int i=0;i<areas.get(s).size();i++){
                if(!areas.get(s).get(i).getAvailable()){
                    result.add(areas.get(s).get(i));
                }
            }
        }

        return result;
    }

    // Find a taxi to satisfy the given request
    public Taxi sendTaxiForRequest(ClientRequest request) {
        if(!availableTaxisInArea(request.getPickupLocation()).isEmpty()){
            Taxi requestedTaxi= availableTaxisInArea(request.getPickupLocation()).get(0);
            requestedTaxi.setEstimatedTimeToDest(computeTravelTimeFrom(request.getPickupLocation(),request.getDropoffLocation()));
            areas.get(request.getDropoffLocation()).add(requestedTaxi);
            areas.get(request.getPickupLocation()).remove(requestedTaxi);
            requestedTaxi.setAvailable(false);
            updateStats(request.getPickupLocation(),request.getDropoffLocation());
            return requestedTaxi;
        }
        else{
            for(int i=0;i<AREA_NAMES.length;i++){
                if(availableTaxisInArea(AREA_NAMES[i]).size() !=0){
                    Taxi requestedTaxi= availableTaxisInArea(AREA_NAMES[i]).get(0);
                    areas.get(request.getDropoffLocation()).add(requestedTaxi);
                    areas.get(request.getPickupLocation()).remove(requestedTaxi);
                    requestedTaxi.setEstimatedTimeToDest(computeTravelTimeFrom(request.getPickupLocation(),request.getDropoffLocation())+computeTravelTimeFrom(AREA_NAMES[i],request.getPickupLocation()));
                    requestedTaxi.setAvailable(false);
                    updateStats(request.getPickupLocation(),request.getDropoffLocation());
                    return requestedTaxi;
                }
            }
        }

        return null;
    }
}