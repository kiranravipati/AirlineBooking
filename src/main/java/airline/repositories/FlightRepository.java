package airline.repositories;

import airline.models.Flight;
import airline.models.FlightDetails;

import java.util.ArrayList;
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
        Flight flight1 = new Flight("F1", "HYD", "BLR", 50, new FlightDetails(30));
        Flight flight2 = new Flight("F2", "HYD", "PUN", 40, new FlightDetails(5));
        Flight flight3 = new Flight("F3", "BLR", "PUN", 60, new FlightDetails(20));
        Flight flight4 = new Flight("F4", "HYD", "BLR", 45, new FlightDetails(2));

        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
    }

    public List<Flight> getFlights() {
        return flights;
    }
}


