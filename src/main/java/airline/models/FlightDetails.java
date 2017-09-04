package airline.models;

public class FlightDetails {
    private int availableSeats;
    //private date departureDate;
    //private flightNumber;

    public FlightDetails(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
