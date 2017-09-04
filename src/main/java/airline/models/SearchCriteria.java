package airline.models;

import java.util.Date;

public class SearchCriteria {
    private String source;
    private String destination;
    private int numberOfPassengers;
    private String departureDate;

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public SearchCriteria() {
        this.numberOfPassengers = 1;
    }

    public SearchCriteria(String source, String destination, int numberOfPassengers, String departureDate) {
        this.source = source;
        this.destination = destination;
        this.numberOfPassengers = numberOfPassengers;
        this.departureDate = departureDate;
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

    public void setDepartureDate(String departureDate) {
        //System.out.println("departureDate" + departureDate);
        this.departureDate = departureDate;
    }
}
