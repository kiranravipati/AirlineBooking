package airline.services;

import airline.models.*;
import airline.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class FlightSearchService {
    @Autowired
    FlightRepository flightRepository;

    public List<SearchResult> search(SearchCriteria searchCriteria) throws ParseException {
        List<Flight> flights = flightRepository.getFlights();

        List<Flight> matchingFlights = flights.stream()
                .filter(x -> x.getSource().equals(searchCriteria.getSource()))
                .filter(x -> x.getDestination().equals(searchCriteria.getDestination()))
                .filter(x -> x.isSeatAvailableForTravelClass(searchCriteria.getTravelClass(), searchCriteria.getSeatsRequested()))
                .filter(x -> compareDates(x.getDepartureDate(), searchCriteria.getDepartureDate()))
                .collect(Collectors.toList());
        return buildResultsFrom(matchingFlights, searchCriteria);
    }

    private boolean compareDates(LocalDate flightDepartureDate, LocalDate searchCriteriaDate) {
        return (searchCriteriaDate == null) ? true : flightDepartureDate.equals(searchCriteriaDate);
    }

    public List<SearchResult> buildResultsFrom(List<Flight> flights, SearchCriteria searchCriteria) {
        List<SearchResult> searchResults = new ArrayList<>();
        SearchResult searchResult;
        float bookingPrice = 0.0f;

        for(Flight flight : flights) {
            switch(searchCriteria.getTravelClass()) {
                case ECONOMY:
                    float basePriceForEconomyClass = flight.basePriceForTravelClass(TravelClass.ECONOMY);
                    EconomyPriceCalculator economyPriceCalculator = new EconomyPriceCalculator(basePriceForEconomyClass);
                    bookingPrice = economyPriceCalculator.getBookingPrice(searchCriteria.getSeatsRequested());
                    break;

                case BUSINESS:
                    break;

                case FIRST:
                    break;

                default:
                    break;
            }

            searchResult = new SearchResult(flight.getFlightNumber(), flight.getCarrier().getCarrierType(),
                                            flight.getSource(), flight.getDestination(), bookingPrice);
            searchResults.add(searchResult);
        }

        return searchResults;
    }
}