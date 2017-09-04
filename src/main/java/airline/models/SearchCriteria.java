package airline.models;

public class SearchCriteria {
    private String source;
    private String destination;
    private int numberOfPassengers;

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

    public SearchCriteria(String source, String destination, int numberOfPassengers) {
        this.source = source;
        this.destination = destination;
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
