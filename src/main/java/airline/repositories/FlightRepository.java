package airline.repositories;

import airline.models.Flight;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import org.springframework.stereotype.Service;


//@Service
public class FlightRepository {
    private static final FlightRepository sharedInstance = new FlightRepository();
    private static List<Flight> flights;

    private FlightRepository() {
        flights = new ArrayList<Flight>();
    }

    public static FlightRepository getSharedInstance() {
        return sharedInstance;
    }

    public void createDefaultFlights() {
        Date departureDate = new Date();

        Flight flight1 = new Flight("F1", "HYD", "BLR", 50, 20, departureDate);
        Flight flight2 = new Flight("F2", "HYD", "PUN", 40, 10, departureDate);
        Flight flight3 = new Flight("F3", "BLR", "PUN", 60, 5, departureDate);
        Flight flight4 = new Flight("F4", "HYD", "BLR", 45, 2, departureDate);

        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
    }

    public List<Flight> getFlights() {
        return flights;
    }
}


