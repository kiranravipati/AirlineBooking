package airline.models;

import java.util.Map;

public class Carrier {
    private CarrierType carrierType;
    private Map<TravelClass, Integer> mapOfSeatsPerClass;
    private Map<TravelClass, Float> mapOfPricePerClass;

    public Carrier(CarrierType carrierType, Map<TravelClass, Integer> mapOfSeatsPerClass, Map<TravelClass, Float> mapOfPricePerClass) {
        this.carrierType = carrierType;
        this.mapOfSeatsPerClass = mapOfSeatsPerClass;
        this.mapOfPricePerClass = mapOfPricePerClass;
    }

    public CarrierType getCarrierType() {
        return carrierType;
    }

    public Map<TravelClass, Integer> getMapOfSeatsPerClass() {
        return mapOfSeatsPerClass;
    }

    public Map<TravelClass, Float> getMapOfPricePerClass() {
        return mapOfPricePerClass;
    }

    public Integer getNoOfSeatsForTravelClass(TravelClass travelClass) {
        if (this.hasTravelClass(travelClass))
            return mapOfSeatsPerClass.get(travelClass);
        else
            return 0;
    }

    public float getPriceForTravelClass(TravelClass travelClass) {
        if (mapOfPricePerClass.containsKey(travelClass))
            return mapOfPricePerClass.get(travelClass);
        else
            return 0.0f;
    }

    public Boolean isValidCarrierType(CarrierType carrierType){
        return (this.carrierType.equals(carrierType)) ? true : false;
    }

    public Boolean hasTravelClass(TravelClass travelClass){
        return this.mapOfSeatsPerClass.containsKey(travelClass);
    }
}
