package airline.models;

public class SearchResult {
    private String flightNumber;
    private CarrierType carrierType;
    private String source;
    private String destination;
    private float pricePerHead;
    private float totalPrice;

    public SearchResult(Flight flight, float pricePerHead, float totalPrice) {
        this.flightNumber = flight.getFlightNumber();
        this.carrierType = flight.getCarrier().getCarrierType();
        this.source = flight.getSource();
        this.destination = flight.getDestination();
        this.pricePerHead = pricePerHead;
        this.totalPrice = totalPrice;
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

    public float getPricePerHead() {
        return pricePerHead;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}


