package airline.models;

import java.util.Date;

public class SearchCriteria {
    private String source;
    private String destination;
    private int numberOfPassengers;
    private String departureDate;

    private static final int DEFAULT_PASSENGERS = 1;

    public SearchCriteria() {
        this.numberOfPassengers = DEFAULT_PASSENGERS;
    }

    public SearchCriteria(String source, String destination, int numberOfPassengers, String departureDate) {
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.numberOfPassengers = numberOfPassengers;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return String.format("Source %s\nDestination %s\n passengers %d departure date %s", source, destination, numberOfPassengers, departureDate);
    }
}
