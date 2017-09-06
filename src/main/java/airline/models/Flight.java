package airline.models;

import airline.repositories.CarrierRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Flight {
    private String flightNumber;
    private String source;
    private String destination;
    private LocalDate departureDate;
    private CarrierType carrierType;
    private HashMap<TravelClass,Integer> mapOfSeatsPerClass;

    public Flight() {
    }

    public Flight(String flightNumber, String source, String destination, LocalDate departureDate, CarrierType carrierType) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.carrierType = carrierType;
        this.mapOfSeatsPerClass = new HashMap<TravelClass,Integer>();

        populateMapOfSeatsPerClassBasedOnCarrier();
    }

    public void populateMapOfSeatsPerClassBasedOnCarrier() {
        CarrierRepository carrierRepository = CarrierRepository.getSharedInstance();
        HashMap<TravelClass, Integer> mapOfSeatsPerClassForCarrier = carrierRepository.getCarriers().get(carrierType);

        for (Map.Entry <TravelClass, Integer> travelClassEntry : mapOfSeatsPerClassForCarrier.entrySet()) {
            mapOfSeatsPerClass.put(travelClassEntry.getKey(), travelClassEntry.getValue());
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public CarrierType getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(CarrierType carrierType) {
        this.carrierType = carrierType;
    }

    public HashMap<TravelClass, Integer> getMapOfSeatsPerClass() {
        return mapOfSeatsPerClass;
    }

    public void setMapOfSeatsPerClass(HashMap<TravelClass, Integer> mapOfSeatsPerClass) {
        this.mapOfSeatsPerClass = mapOfSeatsPerClass;
    }
}
