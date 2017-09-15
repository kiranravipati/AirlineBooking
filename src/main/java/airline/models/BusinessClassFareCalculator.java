package airline.models;

import java.time.DayOfWeek;

public class BusinessClassFareCalculator extends FareCalculator {
    public float getPricePerHead(Flight flight, SearchCriteria searchCriteria) {
        float basePrice = flight.basePriceForTravelClass(searchCriteria.getTravelClass());

        if ((flight.getDepartureDate().getDayOfWeek() == DayOfWeek.MONDAY) ||
                (flight.getDepartureDate().getDayOfWeek() == DayOfWeek.FRIDAY) ||
                        (flight.getDepartureDate().getDayOfWeek() == DayOfWeek.SUNDAY))
            return basePrice * 1.4f;
        else
            return basePrice;
    }
}
