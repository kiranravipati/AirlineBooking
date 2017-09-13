package airline.models;

import java.time.DayOfWeek;

public class FareCalculator {
    private float basePrice;
    private Flight flight;
    private SearchCriteria searchCriteria;

    public FareCalculator(Flight flight, SearchCriteria searchCriteria) {
        this.flight = flight;
        this.searchCriteria = searchCriteria;
        this.basePrice = flight.basePriceForTravelClass(searchCriteria.getTravelClass());
    }

    public float getPricePerHead() {
        TravelClass travelClass = searchCriteria.getTravelClass();
        float bookingsRate = flight.bookingsRate(travelClass);

        switch (travelClass) {
            case ECONOMY:
                if (bookingsRate <= 40.0f)
                    return basePrice;
                else if ((bookingsRate > 40.0f) && (bookingsRate < 90.0f))
                    return basePrice * 1.3f;
                else
                    return basePrice * 1.6f;

            case BUSINESS:
                if ((flight.getDepartureDate().getDayOfWeek() == DayOfWeek.MONDAY) ||
                   (flight.getDepartureDate().getDayOfWeek() == DayOfWeek.FRIDAY) ||
                           (flight.getDepartureDate().getDayOfWeek() == DayOfWeek.MONDAY)) {
                    return basePrice * 1.4f;
                } else {
                    return basePrice;
                }
            case FIRST:
                return basePrice;

            default:
                return 0.0f;
        }
    }
}


//    public float getBookingPrice() {
//        return basePrice * searchCriteria.getSeatsRequested();
//    }

//    public float getPricePerHead() {
//        return basePrice;
//    }
