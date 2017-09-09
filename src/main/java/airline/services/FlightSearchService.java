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
                .filter(x -> compareDates(x.getDepartureDate(), searchCriteria.getDepartureDate()))
                .filter(x -> x.isSeatAvailableForTravelClass(searchCriteria.getTravelClass(), searchCriteria.getSeatsRequested()))
                .collect(Collectors.toList());
        return matchingFlightsWithFareDetails(matchingFlights, searchCriteria);
    }

    private boolean compareDates(LocalDate flightDepartureDate, LocalDate searchCriteriaDate) {
        return (searchCriteriaDate == null) ? true : flightDepartureDate.equals(searchCriteriaDate);
    }

    public List<SearchResult> matchingFlightsWithFareDetails(List<Flight> flights, SearchCriteria searchCriteria) {
        List<SearchResult> searchResults = new ArrayList<>();
        SearchResult searchResult;
        float pricePerHead, totalPrice = 0.0f;

        for(Flight flight : flights) {
            PriceCalculator priceCalculator = new PriceCalculator(flight, searchCriteria);
            totalPrice = priceCalculator.getBookingPrice();
            pricePerHead = priceCalculator.getPricePerHead();

            searchResult = new SearchResult(flight, pricePerHead, totalPrice);
            searchResults.add(searchResult);
        }

        return searchResults;
    }
}