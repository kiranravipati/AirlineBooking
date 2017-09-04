package airline.services;

import airline.models.Flight;
import airline.models.SearchCriteria;
import airline.repositories.FlightRepository;
import java.util.stream.Collectors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
public class FlightSearchService {
    //@Autowired
    FlightRepository flightRepository;

    public List<Flight> search(SearchCriteria searchCriteria) {
        flightRepository = FlightRepository.getSharedInstance();

        if (searchCriteria.getNumberOfPassengers() == 0) {
            searchCriteria.setNumberOfPassengers(1);
        }

        List<Flight> flights = flightRepository.getFlights();

        return flights.stream()
                .filter(x -> x.getSource().equals(searchCriteria.getSource()))
                .filter(x -> x.getDestination().equals(searchCriteria.getDestination()))
                .filter(x -> x.getFlightDetails().getAvailableSeats() >= searchCriteria.getNumberOfPassengers())
                .collect(Collectors.toList());
    }
}

