package airline.models;

public class EconomyClassFareCalculator extends FareCalculator {
    public float getPricePerHead(Flight flight, SearchCriteria searchCriteria) {
        float basePrice = flight.basePriceForTravelClass(searchCriteria.getTravelClass());
        float bookingsRate = flight.bookingsRate(searchCriteria.getTravelClass());

        if (bookingsRate <= 40.0f)
            return basePrice;
        else if ((bookingsRate > 40.0f) && (bookingsRate < 90.0f))
            return basePrice * 1.3f;
        else
            return basePrice * 1.6f;
    }
}
