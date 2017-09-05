package airline.models;

import java.time.LocalDate;

public class SearchCriteria {
    private String source;
    private String destination;
    private int numberOfPassengers;
    private String departureDateString;
    private LocalDate departureDate;

    private static final int DEFAULT_PASSENGERS = 1;

    public SearchCriteria() {
        this.numberOfPassengers = DEFAULT_PASSENGERS;
    }

    public SearchCriteria(String source, String destination, int numberOfPassengers, String departureDateString) {
        this.source = source;
        this.destination = destination;
        this.numberOfPassengers = numberOfPassengers;
        this.departureDateString = departureDateString;
        this.departureDate = (departureDateString.isEmpty()) ? null : LocalDate.parse(departureDateString);
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

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureDateString() {
        return departureDateString;
    }

    public void setDepartureDateString(String departureDateString) {
        this.departureDateString = departureDateString;
    }

    @Override
    public String toString() {
        return String.format("Source %s\nDestination %s\n passengers %d departure date %s", source, destination, numberOfPassengers, departureDateString);
    }
}
