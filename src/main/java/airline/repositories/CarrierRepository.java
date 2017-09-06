package airline.repositories;

import airline.models.CarrierType;
import airline.models.TravelClass;
import java.util.HashMap;
import java.util.Map;

public class CarrierRepository {
    private static final CarrierRepository sharedInstance = new CarrierRepository();

    private Map<CarrierType, HashMap<TravelClass, Integer>> carriers;

    private CarrierRepository() {
        carriers = new HashMap<CarrierType, HashMap<TravelClass, Integer>>();
    }

    public static CarrierRepository getSharedInstance() {
        return sharedInstance;
    }


    public void createDefaultCarriers() {
        Map mapOfSeatsPerClassForBoeing = new HashMap<TravelClass, Integer>();
        Map mapOfSeatsPerClassForAirbus = new HashMap<TravelClass, Integer>();
        Map mapOfSeatsPerClassForAirbus1 = new HashMap<TravelClass, Integer>();

        mapOfSeatsPerClassForBoeing.put(TravelClass.FIRST, 0);
        mapOfSeatsPerClassForBoeing.put(TravelClass.BUSINESS, 0);
        mapOfSeatsPerClassForBoeing.put(TravelClass.ECONOMY, 80);
        carriers.put(CarrierType.BOEING777, (HashMap<TravelClass, Integer>) mapOfSeatsPerClassForBoeing);

        mapOfSeatsPerClassForAirbus.put(TravelClass.FIRST, 0);
        mapOfSeatsPerClassForAirbus.put(TravelClass.BUSINESS, 20);
        mapOfSeatsPerClassForAirbus.put(TravelClass.ECONOMY, 60);
        carriers.put(CarrierType.AIRBUS321, (HashMap<TravelClass, Integer>) mapOfSeatsPerClassForAirbus);

        mapOfSeatsPerClassForAirbus1.put(TravelClass.FIRST, 10);
        mapOfSeatsPerClassForAirbus1.put(TravelClass.BUSINESS, 10);
        mapOfSeatsPerClassForAirbus1.put(TravelClass.ECONOMY, 60);
        carriers.put(CarrierType.AIRBUS319V2, (HashMap<TravelClass, Integer>) mapOfSeatsPerClassForAirbus1);
    }


    public void printCarriers() {
        for (Map.Entry <CarrierType, HashMap<TravelClass, Integer>> entry : carriers.entrySet()){
            System.out.println(entry.getKey());
            HashMap<TravelClass, Integer> mapOfSeatsPerClass = entry.getValue();
            for (Map.Entry <TravelClass, Integer> travelClassEntry : mapOfSeatsPerClass.entrySet()) {
                System.out.println(travelClassEntry.getKey() + " " + travelClassEntry.getValue());
            }
            System.out.println();
        }
    }

    public Map<CarrierType, HashMap<TravelClass, Integer>> getCarriers() {
        return carriers;
    }


}


// Carrier carrier3 = new Carrier(CarrierType.AIRBUS319V2, mapOfSeatsPerClassForAirbus1);
// System.out.println("Inside creating default carriers" + carriers.size());
