package airline.models;

import java.time.LocalDate;
import java.util.Optional;

public class SearchCriteria {
    private String source;
    private String destination;
    private Optional<Integer> seatsRequested;
    private String departureDateString;
    private TravelClass travelClass;

    public SearchCriteria() {
        this.seatsRequested = Optional.of(1);
    }

    public SearchCriteria(String source, String destination, Optional <Integer> seatsRequested, String departureDateString, String travelClass) {
        this.source = source;
        this.destination = destination;
        this.seatsRequested = seatsRequested;
        this.departureDateString = departureDateString;
        this.travelClass = TravelClass.valueOf(travelClass);
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
        return seatsRequested.get();
    }

    public void setSeatsRequested(Optional<Integer> seatsRequested) {
        if (!seatsRequested.isPresent())
            this.seatsRequested = Optional.of(1);
        else
            this.seatsRequested = seatsRequested;
    }

    public void setDepartureDateString(String departureDateString) {
        this.departureDateString = departureDateString;
    }

    public String getDepartureDateString() {
        return departureDateString;
    }

    public LocalDate getDepartureDate() {
        if (departureDateString == null || departureDateString.isEmpty()) {
            return null;
        } else {
            return LocalDate.parse(departureDateString);
        }
    }

    public TravelClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    @Override
    public String toString() {
        return String.format("Source %s\nDestination %s\n passengers %d departure date %s travel class %s", source, destination, seatsRequested.get(), departureDateString, travelClass);
    }
}
