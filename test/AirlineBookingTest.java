import airline.models.Flight;
import airline.models.SearchCriteria;
import airline.models.SearchResult;
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
        flightRepository = new FlightRepository();
        flightSearchService = new FlightSearchService();
    }

    @Test
    public void shouldRetrieveAllFlights() {
        Assert.assertEquals(4, flightRepository.getFlights().size());
    }


    @Test
    public void shouldReturnFlightsBasedOnSourceAndDestinationAndDepartureDate() throws ParseException {
        SearchCriteria searchCriteria = new SearchCriteria("HYD", "BLR", 7, "2017-09-04", "ECONOMY");
        List<SearchResult> searchResults = flightSearchService.search(searchCriteria);
        Assert.assertEquals(1, searchResults.size());
    }

    @Test
    public void shouldRetrieveFlightsBasedOnSourceAndDestinationIfDepartureDateIsNotGiven() throws ParseException {
        SearchCriteria searchCriteria = new SearchCriteria("HYD", "BLR", 2, "", "ECONOMY");
        List<SearchResult> searchResults = flightSearchService.search(searchCriteria);
        Assert.assertEquals(2, searchResults.size());
    }

    @Test
    public void shouldReturnFlightsIfPassengersCountNotSpecified() throws ParseException {
        SearchCriteria searchCriteria = new SearchCriteria("HYD", "BLR", 0, "2017-09-04", "ECONOMY");
        List<SearchResult> searchResults = flightSearchService.search(searchCriteria);
        Assert.assertEquals(2, searchResults.size());
    }
}