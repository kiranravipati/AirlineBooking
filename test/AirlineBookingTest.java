

import airline.models.Flight;
import airline.models.SearchCriteria;
import airline.repositories.FlightRepository;
import airline.services.FlightSearchService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class AirlineBookingTest {
    FlightRepository flightRepository;
    FlightSearchService flightSearchService;

    @Before
    public void setUp() {
        flightRepository = FlightRepository.getSharedInstance();
        flightRepository.createDefaultFlights();
        flightSearchService = new FlightSearchService();
    }

    @Test
    public void shouldRetrieveAllFlights() {
        Assert.assertEquals(4, flightRepository.getFlights().size());
    }


    @Test
    public void shouldReturnFlightsFromHydToBlr() throws ParseException {
        List<Flight> searchResults = flightSearchService.search(new SearchCriteria("HYD", "BLR", 7, "04-09-2017"));
        Assert.assertEquals(1, searchResults.size());
    }
}