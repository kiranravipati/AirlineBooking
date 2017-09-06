package airline.repositories;

import airline.models.CarrierType;
import airline.models.Flight;
import java.time.LocalDate;
import java.time.Month;
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
        Flight flight1 = new Flight("F1", "HYD", "BLR", 50, 20, LocalDate.now(), CarrierType.BOEING777);
        Flight flight2 = new Flight("F2", "HYD", "PUN", 40, 10, LocalDate.of(2017, Month.SEPTEMBER,7), CarrierType.AIRBUS319V2);
        Flight flight3 = new Flight("F3", "BLR", "PUN", 60, 5, LocalDate.of(2017, Month.SEPTEMBER,6), CarrierType.BOEING777);
        Flight flight4 = new Flight("F4", "HYD", "BLR", 45, 2, LocalDate.of(2017, Month.SEPTEMBER,5), CarrierType.AIRBUS321);

        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
    }

    public List<Flight> getFlights() {
        return flights;
    }
}


