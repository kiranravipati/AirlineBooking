package airline.services;

import airline.models.*;
import airline.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class FlightSearchService {
    @Autowired
    FlightRepository flightRepository;

    public List<SearchResult> searchResultsMatchingCriteria(SearchCriteria searchCriteria) throws ParseException {
        List<Flight> flights = findFlightsMatchingCriteria(searchCriteria);

        List<SearchResult> searchResults = new ArrayList<>();
        SearchResult searchResult;
        float pricePerHead, totalPrice = 0.0f;
        FareCalculator fareCalculator = new EconomyClassFareCalculator();

        for(Flight flight : flights) {
            switch (searchCriteria.getTravelClass()) {
                case ECONOMY:
                   fareCalculator = new EconomyClassFareCalculator();
                   break;

                case BUSINESS:
                    fareCalculator = new BusinessClassFareCalculator();
                    break;

                case FIRST:
                    fareCalculator = new FirstClassFareCalculator();
                    break;
            }

            pricePerHead = fareCalculator.getPricePerHead(flight, searchCriteria);
            totalPrice = pricePerHead * searchCriteria.getSeatsRequested();

            searchResult = new SearchResult(flight, pricePerHead, totalPrice);
            searchResults.add(searchResult);
        }

        return searchResults;
    }


    public List<Flight> findFlightsMatchingCriteria(SearchCriteria searchCriteria) throws ParseException {
        List<Flight> flights = flightRepository.getFlights();

        return flights.stream()
                .filter(sourceMatches(searchCriteria.getSource()))
                .filter(destinationMatches(searchCriteria.getDestination()))
                .filter(departureDateMatches(searchCriteria.getDepartureDate(), searchCriteria.getTravelClass()))
                .filter(seatsAvailabilityMatches(searchCriteria))
                .collect(Collectors.toList());
    }

    private static Predicate<Flight> sourceMatches(String source) {
        return flight -> source == null || flight.getSource().equalsIgnoreCase(source);
    }

    private static Predicate<Flight> destinationMatches(String destination) {
        return flight -> destination == null || flight.getDestination().equalsIgnoreCase(destination);
    }


    private static Predicate<Flight> seatsAvailabilityMatches(SearchCriteria searchCriteria) {
        return flight -> searchCriteria.getTravelClass() == null || flight.isSeatAvailableForTravelClass(searchCriteria);
    }

    private static Predicate<Flight> departureDateMatches(LocalDate searchCriteriaDate, TravelClass travelClass) {
        Long diffInDays = ChronoUnit.DAYS.between(LocalDate.now(), searchCriteriaDate);

        if ((travelClass == TravelClass.FIRST) && (diffInDays > 10)) {
            return flight -> false;
        }

        return flight -> searchCriteriaDate == null || flight.getDepartureDate().equals(searchCriteriaDate);
    }
}