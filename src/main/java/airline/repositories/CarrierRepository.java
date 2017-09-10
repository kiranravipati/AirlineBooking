package airline.repositories;

import airline.models.Carrier;
import airline.models.CarrierType;
import airline.models.TravelClass;
import airline.models.TravelClassInfo;
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
        Map<TravelClass, TravelClassInfo> mapOfSeatsAndPricePerClassForCarrier1 = new HashMap<>();

        TravelClassInfo economyClassInfo = new TravelClassInfo(60, 60, 5000f);
        TravelClassInfo businessClassInfo = new TravelClassInfo(20, 20, 8000f);
        TravelClassInfo firstClassInfo = new TravelClassInfo(10, 10, 10000f);

        mapOfSeatsAndPricePerClassForCarrier1.put(TravelClass.ECONOMY, economyClassInfo);
        mapOfSeatsAndPricePerClassForCarrier1.put(TravelClass.BUSINESS, businessClassInfo);
        mapOfSeatsAndPricePerClassForCarrier1.put(TravelClass.FIRST, firstClassInfo);

        Carrier carrier1 = new Carrier(CarrierType.BOEING777, mapOfSeatsAndPricePerClassForCarrier1);
        carriers.put(CarrierType.BOEING777, carrier1);

        /****************************************************************/
        Map<TravelClass, TravelClassInfo> mapOfSeatsAndPricePerClassForCarrier2 = new HashMap<>();

        economyClassInfo = new TravelClassInfo(60, 60, 4000f);
        businessClassInfo = new TravelClassInfo(20, 20, 7000f);

        mapOfSeatsAndPricePerClassForCarrier2.put(TravelClass.ECONOMY, economyClassInfo);
        mapOfSeatsAndPricePerClassForCarrier2.put(TravelClass.BUSINESS, businessClassInfo);

        Carrier carrier2 = new Carrier(CarrierType.AIRBUS321, mapOfSeatsAndPricePerClassForCarrier2);
        carriers.put(CarrierType.AIRBUS321, carrier2);

        /****************************************************************/

        Map<TravelClass, TravelClassInfo> mapOfSeatsAndPricePerClassForCarrier3 = new HashMap<>();

        economyClassInfo = new TravelClassInfo(60, 60, 3000f);
        mapOfSeatsAndPricePerClassForCarrier3.put(TravelClass.ECONOMY, economyClassInfo);

        Carrier carrier3 = new Carrier(CarrierType.AIRBUS319V2, mapOfSeatsAndPricePerClassForCarrier3);
        carriers.put(CarrierType.AIRBUS319V2, carrier3);
    }


    public void printCarriers() {
        for (Map.Entry <CarrierType, Carrier> entry : carriers.entrySet()){
            System.out.println(entry.getKey());
            Carrier carrier = entry.getValue();
            Map<TravelClass, TravelClassInfo> mapOfSeatsAndPricePerClass = carrier.getMapOfSeatsAndPricePerClass();
            for (Map.Entry <TravelClass, TravelClassInfo> travelClassEntry : mapOfSeatsAndPricePerClass.entrySet()) {
                System.out.println(travelClassEntry.getKey() + " - Seats: "  + travelClassEntry.getValue().getAvailableSeats() + ",  Price: " + travelClassEntry.getValue().getPrice());
            }
            System.out.println();
        }
    }

    public Map<CarrierType, Carrier> getCarriers() {
        return carriers;
    }
}