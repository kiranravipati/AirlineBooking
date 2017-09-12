package airline.services;

import airline.models.*;
import airline.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class FlightSearchService {
    @Autowired
    FlightRepository flightRepository;

    public List<Flight> search(SearchCriteria searchCriteria) throws ParseException {
        List<Flight> flights = flightRepository.getFlights();

        return flights.stream()
                .filter(sourceMatches(searchCriteria.getSource()))
                .filter(destinationMatches(searchCriteria.getDestination()))
                .filter(departureDateMatches(searchCriteria.getDepartureDate()))
                .filter(seatsAvailabilityMatches(searchCriteria.getTravelClass(), searchCriteria.getSeatsRequested()))
                .collect(Collectors.toList());
    }

    private static Predicate<Flight> sourceMatches(String source) {
        return flight -> source == null || flight.getSource().equalsIgnoreCase(source);
    }

    private static Predicate<Flight> destinationMatches(String destination) {
        return flight -> destination == null || flight.getDestination().equalsIgnoreCase(destination);
    }

    private static Predicate<Flight> seatsAvailabilityMatches(TravelClass travelClass, int seatsRequested) {
        return flight -> travelClass == null || flight.isSeatAvailableForTravelClass(travelClass, seatsRequested);
    }

    private static Predicate<Flight> departureDateMatches(LocalDate searchCriteriaDate) {
        return flight -> searchCriteriaDate == null || flight.getDepartureDate().equals(searchCriteriaDate);
    }

    public List<SearchResult> matchingFlightsWithFareDetails(SearchCriteria searchCriteria) throws ParseException {
        List<Flight> flights = search(searchCriteria);

        List<SearchResult> searchResults = new ArrayList<>();
        SearchResult searchResult;
        float pricePerHead, totalPrice = 0.0f;

        for(Flight flight : flights) {
            FareCalculator fareCalculator = new FareCalculator(flight, searchCriteria);
            totalPrice = fareCalculator.getBookingPrice();
            pricePerHead = fareCalculator.getPricePerHead();

            searchResult = new SearchResult(flight, pricePerHead, totalPrice);
            searchResults.add(searchResult);
        }

        return searchResults;
    }
}