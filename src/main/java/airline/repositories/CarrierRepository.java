package airline.repositories;

import airline.models.Carrier;
import airline.models.CarrierType;
import airline.models.TravelClass;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CarrierRepository {
    private Map<CarrierType, Carrier> carriers;

    public CarrierRepository() {
        carriers = new HashMap<>();
        createDefaultCarriers();
    }

    private void createDefaultCarriers() {
        Map<TravelClass, Integer> mapOfSeatsPerClassForCarrier1 = new HashMap<>();
        mapOfSeatsPerClassForCarrier1.put(TravelClass.FIRST, 10);
        mapOfSeatsPerClassForCarrier1.put(TravelClass.BUSINESS, 20);
        mapOfSeatsPerClassForCarrier1.put(TravelClass.ECONOMY, 80);

        Map<TravelClass, Float> mapOfPricePerClassForCarrier1 = new HashMap<>();
        mapOfPricePerClassForCarrier1.put(TravelClass.FIRST, 10000f);
        mapOfPricePerClassForCarrier1.put(TravelClass.BUSINESS, 8000f);
        mapOfPricePerClassForCarrier1.put(TravelClass.ECONOMY, 4000f);

        Carrier carrier1 = new Carrier(CarrierType.BOEING777, mapOfSeatsPerClassForCarrier1, mapOfPricePerClassForCarrier1);
        carriers.put(CarrierType.BOEING777, carrier1);

        Map<TravelClass, Integer> mapOfSeatsPerClassForCarrier2 = new HashMap<>();
        mapOfSeatsPerClassForCarrier2.put(TravelClass.BUSINESS, 20);
        mapOfSeatsPerClassForCarrier2.put(TravelClass.ECONOMY, 60);

        Map<TravelClass, Float> mapOfPricePerClassForCarrier2 = new HashMap<>();
        mapOfPricePerClassForCarrier2.put(TravelClass.BUSINESS, 7500f);
        mapOfPricePerClassForCarrier2.put(TravelClass.ECONOMY, 3000f);

        Carrier carrier2 = new Carrier(CarrierType.AIRBUS321, mapOfSeatsPerClassForCarrier2, mapOfPricePerClassForCarrier2);
        carriers.put(CarrierType.AIRBUS321, carrier2);

        Map<TravelClass, Integer> mapOfSeatsPerClassForCarrier3 = new HashMap<>();
        mapOfSeatsPerClassForCarrier3.put(TravelClass.ECONOMY, 60);

        Map<TravelClass, Float> mapOfPricePerClassForCarrier3 = new HashMap<>();
        mapOfPricePerClassForCarrier3.put(TravelClass.ECONOMY, 4000f);

        Carrier carrier3 = new Carrier(CarrierType.AIRBUS319V2, mapOfSeatsPerClassForCarrier3, mapOfPricePerClassForCarrier3);
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