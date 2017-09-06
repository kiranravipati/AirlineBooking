package airline.services;

import airline.models.Flight;
import airline.models.SearchCriteria;
import airline.models.TravelClass;
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
        flightRepository = FlightRepository.getSharedInstance();
        List<Flight> flights = flightRepository.getFlights();

        return  flights.stream()
                .filter(x -> x.getSource().equals(searchCriteria.getSource()))
                .filter(x -> x.getDestination().equals(searchCriteria.getDestination()))
                .filter(x -> isSeatAvailableForTravelClass(x, searchCriteria.getTravelClass(), searchCriteria.getSeatsRequested()))
                .filter(x -> compareDates(x.getDepartureDate(), searchCriteria.getDepartureDate()))
                .collect(Collectors.toList());
    }

    private boolean compareDates(LocalDate flightDepartureDate, LocalDate searchCriteriaDate) {
        return (searchCriteriaDate == null) ? true : flightDepartureDate.equals(searchCriteriaDate);
    }

    private boolean isSeatAvailableForTravelClass(Flight flight, TravelClass travelClass, int seatsRequested) {
        int availableSeats = flight.getCarrier().getNoOfSeatsForTravelClass(travelClass);
        if (availableSeats >= seatsRequested)
            return true;
        else
            return false;
    }
}


//        Predicate<Flight> seatsAvailability = (Flight p) -> p.getAvailableSeats() >= searchCriteria.getSeatsRequested();
//
//        return  flights.stream()
//                .filter(x -> x.getSource().equals(searchCriteria.getSource()))
//                .filter(x -> x.getDestination().equals(searchCriteria.getDestination()))
//                .filter(seatsAvailability)
//                .filter(x -> compareDates(x.getDepartureDate(), searchCriteria.getDepartureDate()))
//                .collect(Collectors.toList());