package airline.services;

import airline.models.Flight;
import airline.models.SearchCriteria;
import airline.repositories.FlightRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
public class FlightSearchService {
    //@Autowired
    FlightRepository flightRepository;

    public List<Flight> search(SearchCriteria searchCriteria) throws ParseException {
        System.out.println(searchCriteria);
        flightRepository = FlightRepository.getSharedInstance();

        List<Flight> flights = flightRepository.getFlights();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<Flight> matchedFlights =  flights.stream()
                .filter(x -> x.getSource().equals(searchCriteria.getSource()))
                .filter(x -> x.getDestination().equals(searchCriteria.getDestination()))
                .filter(x -> x.getAvailableSeats() >= searchCriteria.getNumberOfPassengers())
                .filter(x -> isDateSpecified(searchCriteria.getDepartureDate()))
                .filter(x -> dateFormat.format(x.getDepartureDate()).equals(searchCriteria.getDepartureDate()))
                .collect(Collectors.toList());

        System.out.println("Matched flights" + matchedFlights);
        for (Flight item : matchedFlights){
            System.out.println(item.getFlightNumber());
            System.out.println("Available seats" + item.getAvailableSeats());
        }

        return matchedFlights;
    }

    private boolean isDateSpecified(String departureDate) {
        if (departureDate.isEmpty()) {
            System.out.println("Departure date is empty");
        }

        return departureDate.isEmpty()? false : true;
    }
}

