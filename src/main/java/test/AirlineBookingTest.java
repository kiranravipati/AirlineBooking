package test;

import airline.models.Flight;
import airline.models.SearchCriteria;
import airline.repositories.FlightRepository;
import airline.services.FlightSearchService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void shouldReturnFlightsFromHydToBlr() {
        List<Flight> searchResults = flightSearchService.search(new SearchCriteria("HYD", "BLR", 5));
        Assert.assertEquals(1, searchResults.size());
    }
}