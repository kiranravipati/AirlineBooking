package airline;

import airline.repositories.CarrierRepository;
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
        // create default cities, carriers and flights
        CityRepository cityRepository = CityRepository.getSharedInstance();
        cityRepository.createDefaultCities();

        CarrierRepository carrierRepository = new CarrierRepository();
        carrierRepository.createDefaultCarriers();

        FlightRepository flightRepository = new FlightRepository();
        flightRepository.createDefaultFlights();

        SpringApplication.run(AirlineBookingApp.class,args);
    }
}
