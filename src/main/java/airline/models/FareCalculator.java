package airline.models;

public class PriceCalculator {
    private float basePrice;
    private Flight flight;
    private SearchCriteria searchCriteria;

    public PriceCalculator(Flight flight, SearchCriteria searchCriteria) {
        this.flight = flight;
        this.searchCriteria = searchCriteria;
        this.basePrice = flight.basePriceForTravelClass(searchCriteria.getTravelClass());
    }

    public float getBookingPrice() {
        return basePrice * searchCriteria.getSeatsRequested();
    }

    public float getPricePerHead() {
        return basePrice;
    }
}
