package airline.models;

import java.time.LocalDate;

public class Flight {
    private String flightNumber;
    private String source;
    private String destination;
    private LocalDate departureDate;
    private Carrier carrier;

    public Flight() {
    }

    public Flight(String flightNumber, String source, String destination, LocalDate departureDate, Carrier carrier) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.carrier = carrier;
    }

    public Flight(String flightNumber, String source, String destination) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
    }
//    public void populateMapOfSeatsPerClassBasedOnCarrier() {
//        CarrierRepository carrierRepository = CarrierRepository.getSharedInstance();
//        Carrier carrier = carrierRepository.getCarriers().get(carrierType);
//        Map<TravelClass, Integer> mapOfSeatsPerClassForCarrier = carrier.getMapOfSeatsPerClass();
//
//        for (Map.Entry <TravelClass, Integer> travelClassEntry : mapOfSeatsPerClassForCarrier.entrySet()) {
//            mapOfAvailableSeatsPerClass.put(travelClassEntry.getKey(), travelClassEntry.getValue());
//        }
//    }

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

    public Carrier getCarrier() {
        return carrier;
    }
}
