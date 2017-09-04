package airline.services;

public class SearchCriteria {
    public String source;
    public String destination;
    public int numberOfPassengers;

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

    }

    public SearchCriteria(String source, String destination, int numberOfPassengers) {
        this.source = source;
        this.destination = destination;
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
}
