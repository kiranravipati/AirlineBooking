package airline.models;

import airline.repositories.CarrierRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Flight {
    private String flightNumber;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private LocalDate departureDate;
    private CarrierType carrierType;
    private HashMap<TravelClass,Integer> mapOfSeatsPerClassForFlight;

    public Flight() {

    }

    public Flight(String flightNumber, String source, String destination, int totalSeats, int availableSeats, LocalDate departureDate, CarrierType carrierType) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.departureDate = departureDate;
        this.carrierType = carrierType;
        this.mapOfSeatsPerClassForFlight = new HashMap<TravelClass,Integer>();

        populateMapOfSeatsPerClassBasedOnCarrier();
    }

    public void populateMapOfSeatsPerClassBasedOnCarrier() {
        CarrierRepository carrierRepository = CarrierRepository.getSharedInstance();

        HashMap<TravelClass, Integer> mapOfSeatsPerClassForCarrier = carrierRepository.getCarriers().get(carrierType);
        System.out.println(mapOfSeatsPerClassForCarrier.size());

        for (Map.Entry <TravelClass, Integer> travelClassEntry : mapOfSeatsPerClassForCarrier.entrySet()) {
            mapOfSeatsPerClassForFlight.put(travelClassEntry.getKey(), travelClassEntry.getValue());
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

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
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
        return mapOfSeatsPerClassForFlight;
    }

    public void setMapOfSeatsPerClassForFlight(HashMap<TravelClass, Integer> mapOfSeatsPerClass) {
        this.mapOfSeatsPerClassForFlight = mapOfSeatsPerClassForFlight;
    }
}
