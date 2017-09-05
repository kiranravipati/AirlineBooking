package airline.services;

import airline.models.Flight;
import airline.models.SearchCriteria;
import airline.repositories.FlightRepository;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.List;

//@Service
public class FlightSearchService {
    //@Autowired
    FlightRepository flightRepository;

    public List<Flight> search(SearchCriteria searchCriteria) throws ParseException {
        System.out.println(searchCriteria);
        flightRepository = FlightRepository.getSharedInstance();

        List<Flight> flights = flightRepository.getFlights();

        return  flights.stream()
                .filter(x -> x.getSource().equals(searchCriteria.getSource()))
                .filter(x -> x.getDestination().equals(searchCriteria.getDestination()))
                .filter(x -> x.getAvailableSeats() >= searchCriteria.getNumberOfPassengers())
                .filter(x -> compareDates(x.getDepartureDate(), searchCriteria.getDepartureDate()))
                .collect(Collectors.toList());
    }

    private boolean compareDates(LocalDate departureDate, LocalDate searchCriteriaDate) {
        return (searchCriteriaDate == null) ? true : departureDate.equals(searchCriteriaDate);
    }
}
