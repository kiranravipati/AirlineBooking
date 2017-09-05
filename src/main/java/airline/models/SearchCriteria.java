package airline.models;

import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;

public class SearchCriteria {
    public enum TravelClass { ECONOMY, BUSINESS, FIRST };

    private String source;
    private String destination;
    private int seatsRequested;
    private String departureDateString;
    private LocalDate departureDate;
    @NotEmpty(message = "{class required}")
    private TravelClass travelClass;

    private static final int DEFAULT_PASSENGERS = 1;

    public SearchCriteria() {
        this.seatsRequested = DEFAULT_PASSENGERS;
    }

    public SearchCriteria(String source, String destination, int seatsRequested, String departureDateString, String serviceClass) {
        this.source = source;
        this.destination = destination;
        this.seatsRequested = seatsRequested;
        this.departureDateString = departureDateString;
        this.departureDate = (departureDateString.isEmpty()) ? null : LocalDate.parse(departureDateString);
        this.travelClass = travelClass;
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

    public int getSeatsRequested() {
        return seatsRequested;
    }

    public void setSeatsRequested(int seatsRequested) {
        if (seatsRequested <= 0)
            this.seatsRequested = 1;
        else
            this.seatsRequested = seatsRequested;
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
        this.departureDate = (departureDateString.isEmpty()) ? null : LocalDate.parse(departureDateString);
    }

    public TravelClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    @Override
    public String toString() {
        return String.format("Source %s\nDestination %s\n passengers %d departure date %s travel class %s", source, destination, seatsRequested, departureDateString, travelClass);
    }
}
