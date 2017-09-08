package airline.models;

public class SearchResult {
    private String flightNumber;
    private CarrierType carrierType;
    private String source;
    private String destination;
    private float bookingPrice;

    public SearchResult(String flightNumber, CarrierType carrierType, String source, String destination, float bookingPrice) {
        this.flightNumber = flightNumber;
        this.carrierType = carrierType;
        this.source = source;
        this.destination = destination;
        this.bookingPrice = bookingPrice;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public CarrierType getCarrierType() {
        return carrierType;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public float getBookingPrice() {
        return bookingPrice;
    }

}


