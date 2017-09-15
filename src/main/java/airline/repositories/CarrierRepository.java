package airline.repositories;

import airline.models.Carrier;
import airline.models.CarrierType;
import airline.models.TravelClass;
import airline.models.Seating;
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
        Map<TravelClass, Seating> mapOfSeatsAndPricePerClassForCarrier1 = new HashMap<>();

        Seating economyClassInfo = new Seating(100, 40, 5000f);
        Seating businessClassInfo = new Seating(20, 5, 8000f);
        Seating firstClassInfo = new Seating(10, 10, 10000f);

        mapOfSeatsAndPricePerClassForCarrier1.put(TravelClass.ECONOMY, economyClassInfo);
        mapOfSeatsAndPricePerClassForCarrier1.put(TravelClass.BUSINESS, businessClassInfo);
        mapOfSeatsAndPricePerClassForCarrier1.put(TravelClass.FIRST, firstClassInfo);

        Carrier carrier1 = new Carrier(CarrierType.BOEING777, mapOfSeatsAndPricePerClassForCarrier1);
        carriers.put(CarrierType.BOEING777, carrier1);

        /****************************************************************/
        Map<TravelClass, Seating> mapOfSeatsAndPricePerClassForCarrier2 = new HashMap<>();

        economyClassInfo = new Seating(100, 30, 4000f);
        businessClassInfo = new Seating(20, 20, 7000f);

        mapOfSeatsAndPricePerClassForCarrier2.put(TravelClass.ECONOMY, economyClassInfo);
        mapOfSeatsAndPricePerClassForCarrier2.put(TravelClass.BUSINESS, businessClassInfo);

        Carrier carrier2 = new Carrier(CarrierType.AIRBUS321, mapOfSeatsAndPricePerClassForCarrier2);
        carriers.put(CarrierType.AIRBUS321, carrier2);

        /****************************************************************/

        Map<TravelClass, Seating> mapOfSeatsAndPricePerClassForCarrier3 = new HashMap<>();

        economyClassInfo = new Seating(60, 10, 3000f);
        mapOfSeatsAndPricePerClassForCarrier3.put(TravelClass.ECONOMY, economyClassInfo);

        Carrier carrier3 = new Carrier(CarrierType.AIRBUS319V2, mapOfSeatsAndPricePerClassForCarrier3);
        carriers.put(CarrierType.AIRBUS319V2, carrier3);
    }


    public void printCarriers() {
        for (Map.Entry <CarrierType, Carrier> entry : carriers.entrySet()){
            System.out.println(entry.getKey());
            Carrier carrier = entry.getValue();
            Map<TravelClass, Seating> mapOfSeatsAndPricePerClass = carrier.getMapOfSeatsAndPricePerClass();
            for (Map.Entry <TravelClass, Seating> travelClassEntry : mapOfSeatsAndPricePerClass.entrySet()) {
                System.out.println(travelClassEntry.getKey() + " - Seats: "  + travelClassEntry.getValue().getAvailableSeats() + ",  Price: " + travelClassEntry.getValue().getPrice());
            }
            System.out.println();
        }
    }

    public Map<CarrierType, Carrier> getCarriers() {
        return carriers;
    }
}