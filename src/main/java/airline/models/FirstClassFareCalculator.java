package airline.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FirstClassFareCalculator extends FareCalculator {
    public float getPricePerHead(Flight flight, SearchCriteria searchCriteria) {
        float basePrice = flight.basePriceForTravelClass(searchCriteria.getTravelClass());

        Long diffInDays = ChronoUnit.DAYS.between(LocalDate.now(), searchCriteria.getDepartureDate());

        if (diffInDays < 10)
            return basePrice * (1 + (10 - diffInDays) * 0.1f);
        else
            return 0.0f;
    }
}
