package airline.models;

import javax.naming.directory.SearchResult;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

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

    public Flight(String flightNumber, String source, String destination, LocalDate departureDate) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
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

    public Carrier getCarrier() {
        return carrier;
    }

    public boolean isSeatAvailableForTravelClass(SearchCriteria searchCriteria) {
        int availableSeats = this.getCarrier().getAvailableSeatsForTravelClass(searchCriteria.getTravelClass());
        return (availableSeats >= searchCriteria.getSeatsRequested()) ? true : false;
    }

    public float basePriceForTravelClass(TravelClass travelClass) {
        return this.carrier.getPriceForTravelClass(travelClass);
    }

    public float bookingsRate(TravelClass travelClass) {
        int availableSeats = this.getCarrier().getAvailableSeatsForTravelClass(travelClass);
        int totalSeats = this.getCarrier().getTotalSeatsForTravelClass(travelClass);
        int bookingsCount = totalSeats - availableSeats;
        float rate = ((float)bookingsCount / totalSeats) * 100.0f;
        //System.out.println(rate);
        return rate;
    }
}
