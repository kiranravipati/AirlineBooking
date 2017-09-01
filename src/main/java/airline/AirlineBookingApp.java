package airline;

import airline.repositories.CityRepository;
import airline.repositories.FlightRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by rajashrk on 8/8/17.
 */

@SpringBootApplication
public class AirlineBookingApp {
    public static void main(String []args) {
        // create default cities and flights
        CityRepository cityRepository = CityRepository.getSharedInstance();
        cityRepository.createDefaultCities();

        FlightRepository flightRepository = FlightRepository.getSharedInstance();
        flightRepository.createDefaultFlights();

        SpringApplication.run(AirlineBookingApp.class,args);
    }
}
