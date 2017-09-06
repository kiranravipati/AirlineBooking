package airline.services;

import airline.models.Flight;
import airline.models.SearchCriteria;
import airline.repositories.FlightRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FlightSearchService.class)
public class FlightSearchServiceTest {


    @MockBean
    private FlightRepository flightRepository;

    @Autowired(required = true)
    private FlightSearchService flightSearchService;

    Flight mockFlight = new Flight("123", "src", "dest");
    private List<Flight> listOfMockFlights = new ArrayList<>(Arrays.asList(mockFlight));

    @Test
    public void testGetFlightsBetweenCities() {
        Mockito.when(flightRepository.getFlights()).thenReturn(listOfMockFlights);

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("src");
        searchCriteria.setDestination("dest");

        List<Flight> listOfFlights;

        try {
            listOfFlights = flightSearchService.search(searchCriteria);
        }
        catch (Exception e) {
            return ;
        }

        assertEquals(listOfFlights, listOfMockFlights);
    }

}