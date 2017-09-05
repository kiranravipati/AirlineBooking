package airline.services;

import airline.models.Flight;
import airline.models.SearchCriteria;
import airline.repositories.FlightRepository;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.List;

//@Service
public class FlightSearchService {
    //@Autowired
    FlightRepository flightRepository;

    public List<Flight> search(SearchCriteria searchCriteria) throws ParseException {
        flightRepository = FlightRepository.getSharedInstance();

        List<Flight> flights = flightRepository.getFlights();

        Predicate<Flight> seatsAvailability = (Flight p) -> p.getAvailableSeats() >= searchCriteria.getSeatsRequested();

        return  flights.stream()
                .filter(x -> x.getSource().equals(searchCriteria.getSource()))
                .filter(x -> x.getDestination().equals(searchCriteria.getDestination()))
                .filter(seatsAvailability)
                .filter(x -> compareDates(x.getDepartureDate(), searchCriteria.getDepartureDate()))
                .collect(Collectors.toList());
    }

    private boolean compareDates(LocalDate flightDepartureDate, LocalDate searchCriteriaDate) {
        return (searchCriteriaDate == null) ? true : flightDepartureDate.equals(searchCriteriaDate);
    }
}
