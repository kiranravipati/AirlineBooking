package airline.repositories;

import airline.models.Carrier;
import airline.models.CarrierType;
import airline.models.TravelClass;
import java.util.HashMap;
import java.util.Map;

public class CarrierRepository {
    private Map<CarrierType, Carrier> carriers;
    public CarrierRepository() {
        carriers = new HashMap<CarrierType, Carrier>();
    }

    public void createDefaultCarriers() {
        HashMap<TravelClass, Integer> mapOfSeatsPerClassForCarrier1 = new HashMap<TravelClass, Integer>();
        mapOfSeatsPerClassForCarrier1.put(TravelClass.FIRST, 10);
        mapOfSeatsPerClassForCarrier1.put(TravelClass.BUSINESS, 20);
        mapOfSeatsPerClassForCarrier1.put(TravelClass.ECONOMY, 80);
        Carrier carrier1 = new Carrier(CarrierType.BOEING777, mapOfSeatsPerClassForCarrier1);
        carriers.put(CarrierType.BOEING777, carrier1);

        HashMap<TravelClass, Integer> mapOfSeatsPerClassForCarrier2 = new HashMap<TravelClass, Integer>();
        //mapOfSeatsPerClassForCarrier2.put(TravelClass.FIRST, 0);
        mapOfSeatsPerClassForCarrier2.put(TravelClass.BUSINESS, 20);
        mapOfSeatsPerClassForCarrier2.put(TravelClass.ECONOMY, 60);
        Carrier carrier2 = new Carrier(CarrierType.AIRBUS321, mapOfSeatsPerClassForCarrier2);
        carriers.put(CarrierType.AIRBUS321, carrier2);

        HashMap<TravelClass, Integer> mapOfSeatsPerClassForCarrier3 = new HashMap<TravelClass, Integer>();
        //mapOfSeatsPerClassForCarrier3.put(TravelClass.FIRST, 0);
        //mapOfSeatsPerClassForCarrier3.put(TravelClass.BUSINESS, 0);
        mapOfSeatsPerClassForCarrier3.put(TravelClass.ECONOMY, 60);
        Carrier carrier3 = new Carrier(CarrierType.AIRBUS319V2, mapOfSeatsPerClassForCarrier3);
        carriers.put(CarrierType.AIRBUS319V2, carrier3);
    }


    public void printCarriers() {
        for (Map.Entry <CarrierType, Carrier> entry : carriers.entrySet()){
            System.out.println(entry.getKey());
            Carrier carrier = entry.getValue();
            Map<TravelClass, Integer> mapOfSeatsPerClass = carrier.getMapOfSeatsPerClass();
            for (Map.Entry <TravelClass, Integer> travelClassEntry : mapOfSeatsPerClass.entrySet()) {
                System.out.println(travelClassEntry.getKey() + " " + travelClassEntry.getValue());
            }
            System.out.println();
        }
    }

    public Map<CarrierType, Carrier> getCarriers() {
        return carriers;
    }


}