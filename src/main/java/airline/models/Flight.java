package airline.models;

public class Flight {
    private String flightNumber;
    private String source;
    private String destination;
    private int totalSeats;

    private FlightDetails flightDetails;

    public Flight() {

    }

    public Flight(String flightNumber, String source, String destination, int totalSeats, FlightDetails flightDetails) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.flightDetails = flightDetails;
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

    public FlightDetails getFlightDetails() {
        return flightDetails;
    }

    public void setFlightDetails(FlightDetails flightDetails) {
        this.flightDetails = flightDetails;
    }
}
