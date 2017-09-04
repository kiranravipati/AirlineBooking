package airline.services;

import airline.models.Flight;
import airline.models.SearchCriteria;
import airline.repositories.FlightRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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


        List<Flight> matchedFlights =  flights.stream()
                .filter(x -> x.getSource().equals(searchCriteria.getSource()))
                .filter(x -> x.getDestination().equals(searchCriteria.getDestination()))
                .filter(x -> x.getAvailableSeats() >= searchCriteria.getNumberOfPassengers())
                .filter(x -> compateDates(x.getDepartureDate(), searchCriteria.getDepartureDate()))
                .collect(Collectors.toList());

        return matchedFlights;
    }

    private boolean compateDates(Date departureDate, String queryDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (queryDate.isEmpty()) {
            return true;
        }

        return dateFormat.format(departureDate).equals(queryDate);
    }
}


//        System.out.println("Matched flights" + matchedFlights);
//        for (Flight item : matchedFlights){
//            System.out.println(item.getFlightNumber());
//            System.out.println("Available seats" + item.getAvailableSeats());
//        }
