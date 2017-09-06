package airline.models;

import java.util.Map;

public class Carrier {
    private CarrierType carrierType;
    private Map<TravelClass, Integer> mapOfSeatsPerClass;

    public Carrier(CarrierType carrierType, Map<TravelClass, Integer> mapOfSeatsPerClass) {
        this.carrierType = carrierType;
        this.mapOfSeatsPerClass = mapOfSeatsPerClass;
    }

    public CarrierType getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(CarrierType carrierType) {
        this.carrierType = carrierType;
    }

    public Map<TravelClass, Integer> getMapOfSeatsPerClass() {
        return mapOfSeatsPerClass;
    }
}
