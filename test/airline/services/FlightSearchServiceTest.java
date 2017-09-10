package airline.services;

import airline.models.Flight;
import airline.models.SearchCriteria;
import airline.models.SearchResult;
import airline.models.TravelClass;
import airline.repositories.FlightRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FlightSearchService.class)
public class FlightSearchServiceTest {
    @MockBean
    private FlightRepository flightRepository;

    @Autowired(required = true)
    private FlightSearchService flightSearchService;

    Flight mockFlight = new Flight("123", "src", "dest", LocalDate.now());
    private List<Flight> listOfMockFlights = new ArrayList<>(Arrays.asList(mockFlight));
    SearchCriteria searchCriteria = new SearchCriteria();

    @Before
    public void Setup() {
        Mockito.when(flightRepository.getFlights()).thenReturn(listOfMockFlights);
        searchCriteria.setSeatsRequested(Optional.of(1));
    }

    @Test
    public void shouldGetFlightsBetweenCities() throws ParseException {
        searchCriteria.setSource("src");
        searchCriteria.setDestination("dest");
        searchCriteria.setSeatsRequested(Optional.of(1));

        List<Flight> flights;
        flights = flightSearchService.search(searchCriteria);

        assertEquals(flights, listOfMockFlights);
    }


    @Test
    public void shouldGetFlightsBetweenCitiesBasedOnDepartureDate() throws ParseException {
        Mockito.when(flightRepository.getFlights()).thenReturn(listOfMockFlights);

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("src");
        searchCriteria.setDestination("dest");
        searchCriteria.setDepartureDateString(LocalDate.now().toString());

        List<Flight> listOfFlights;
        listOfFlights = flightSearchService.search(searchCriteria);

        assertEquals(listOfFlights, listOfMockFlights);
    }

    @Test
    public void shouldGetFlightsBetweenCitiesForGivenNumberOfPassengersBasedOnTravelClass() throws ParseException {
        Mockito.when(flightRepository.getFlights()).thenReturn(listOfMockFlights);

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("src");
        searchCriteria.setDestination("dest");
        searchCriteria.setSeatsRequested(Optional.of(2));
        searchCriteria.setTravelClass(TravelClass.ECONOMY);

        List<Flight> listOfFlights;
        listOfFlights = flightSearchService.search(searchCriteria);

        assertEquals(listOfFlights, listOfMockFlights);
    }
}