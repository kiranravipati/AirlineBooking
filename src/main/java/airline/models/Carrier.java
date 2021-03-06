package airline.models;

import java.util.Map;

public class Carrier {
    private CarrierType carrierType;
    private Map<TravelClass, Seating> mapOfSeatsAndPricePerClass;

    public Carrier(CarrierType carrierType, Map<TravelClass, Seating> mapOfSeatsAndPricePerClass) {
        this.carrierType = carrierType;
        this.mapOfSeatsAndPricePerClass = mapOfSeatsAndPricePerClass;
    }

    public CarrierType getCarrierType() {
        return carrierType;
    }

    public Map<TravelClass, Seating> getMapOfSeatsAndPricePerClass() {
        return mapOfSeatsAndPricePerClass;
    }

    public Integer getAvailableSeatsForTravelClass(TravelClass travelClass) {
        if (this.hasTravelClass(travelClass)) {
            return mapOfSeatsAndPricePerClass.get(travelClass).getAvailableSeats();
        }
        else
            return 0;
    }

    public Integer getTotalSeatsForTravelClass(TravelClass travelClass) {
        if (this.hasTravelClass(travelClass)) {
            return mapOfSeatsAndPricePerClass.get(travelClass).getTotalNoOfSeats();
        }
        else
            return 0;
    }

    public float getPriceForTravelClass(TravelClass travelClass) {
        if (this.hasTravelClass(travelClass)) {
            return mapOfSeatsAndPricePerClass.get(travelClass).getPrice();
        }
        else
            return 0.0f;
    }

    public Boolean isValidCarrierType(CarrierType carrierType){
        return (this.carrierType.equals(carrierType)) ? true : false;
    }

    public Boolean hasTravelClass(TravelClass travelClass){
        return this.mapOfSeatsAndPricePerClass.containsKey(travelClass);
    }
}
