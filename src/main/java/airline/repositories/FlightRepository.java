package airline.repositories;

import airline.models.Carrier;
import airline.models.CarrierType;
import airline.models.Flight;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class FlightRepository {
    private List<Flight> flights;

    public FlightRepository() {
        flights = new ArrayList<Flight>();
        createDefaultFlights();
    }

    private void createDefaultFlights() {
        CarrierRepository carrierRepository = new CarrierRepository();
        Map <CarrierType, Carrier> carrierMap = carrierRepository.getCarriers();
        Flight flight1 = new Flight("F1", "HYD", "BLR", LocalDate.now(), carrierMap.get(CarrierType.BOEING777));
        Flight flight2 = new Flight("F2", "HYD", "PUN", LocalDate.of(2017, Month.SEPTEMBER,8), carrierMap.get(CarrierType.AIRBUS319V2));
        Flight flight3 = new Flight("F3", "BLR", "PUN", LocalDate.of(2017, Month.SEPTEMBER,9), carrierMap.get(CarrierType.BOEING777));
        Flight flight4 = new Flight("F4", "HYD", "BLR", LocalDate.of(2017, Month.SEPTEMBER,8), carrierMap.get(CarrierType.AIRBUS321));

        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
    }

    public List<Flight> getFlights() {
        return flights;
    }
}


