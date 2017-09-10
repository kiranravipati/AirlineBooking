package airline.services;

import airline.models.*;
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
import java.util.*;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FlightSearchService.class)
public class FlightSearchServiceTest {
    @MockBean
    private FlightRepository flightRepository;

    @Autowired(required = true)
    private FlightSearchService flightSearchService;

    Flight mockFlight1;
    private List<Flight> listOfMockFlights;
    SearchCriteria searchCriteria = new SearchCriteria();

    public Carrier createBoeingCarrier() {
        Map<TravelClass, TravelClassInfo> mapOfSeatsAndPricePerClassForBoeing = new HashMap<>();
        TravelClassInfo economyClassInfo = new TravelClassInfo(60, 4, 5000f);
        TravelClassInfo businessClassInfo = new TravelClassInfo(20, 0, 8000f);
        TravelClassInfo firstClassInfo = new TravelClassInfo(10, 2, 10000f);

        mapOfSeatsAndPricePerClassForBoeing.put(TravelClass.ECONOMY, economyClassInfo);
        mapOfSeatsAndPricePerClassForBoeing.put(TravelClass.BUSINESS, businessClassInfo);
        mapOfSeatsAndPricePerClassForBoeing.put(TravelClass.FIRST, firstClassInfo);

        return new Carrier(CarrierType.BOEING777, mapOfSeatsAndPricePerClassForBoeing);
    }

    @Before
    public void Setup() {
        Carrier boeingCarrier = createBoeingCarrier();
        mockFlight1 = new Flight("SPJ5544", "HYD", "GOA", LocalDate.now(), boeingCarrier);
        listOfMockFlights = new ArrayList<>(Arrays.asList(mockFlight1));
        Mockito.when(flightRepository.getFlights()).thenReturn(listOfMockFlights);
        searchCriteria.setSeatsRequested(Optional.of(1));
    }

    @Test
    public void shouldGetFlightsBetweenCities() throws ParseException {
        searchCriteria.setSource("HYD");
        searchCriteria.setDestination("GOA");
        searchCriteria.setSeatsRequested(Optional.of(1));

        List<Flight> flights;
        flights = flightSearchService.search(searchCriteria);

        assertEquals(flights, listOfMockFlights);
    }

    @Test
    public void shouldGetFlightsBetweenCitiesBasedOnDepartureDate() throws ParseException {
        Mockito.when(flightRepository.getFlights()).thenReturn(listOfMockFlights);

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("HYD");
        searchCriteria.setDestination("GOA");
        searchCriteria.setDepartureDateString(LocalDate.now().toString());

        List<Flight> listOfFlights;
        listOfFlights = flightSearchService.search(searchCriteria);

        assertEquals(listOfFlights, listOfMockFlights);
    }

    @Test
    public void shouldGetFlightsBetweenCitiesForGivenNumberOfPassengersBasedOnTravelClass() throws ParseException {
        Mockito.when(flightRepository.getFlights()).thenReturn(listOfMockFlights);

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("HYD");
        searchCriteria.setDestination("GOA");
        searchCriteria.setSeatsRequested(Optional.of(2));
        searchCriteria.setTravelClass(TravelClass.ECONOMY);

        List<Flight> listOfFlights;
        listOfFlights = flightSearchService.search(searchCriteria);

        assertEquals(listOfFlights, listOfMockFlights);
    }

    @Test
    public void verifyBookingAmountFor2PassengersForEconomyClassOnBoeingCarrier() throws ParseException {
        int numberOfPassengers = 2;
        float basePirce = mockFlight1.getCarrier().getPriceForTravelClass(TravelClass.ECONOMY);
        float expectedBookingAmount = basePirce * numberOfPassengers;

        searchCriteria.setSource("HYD");
        searchCriteria.setDestination("GOA");
        searchCriteria.setSeatsRequested(Optional.of(numberOfPassengers));
        searchCriteria.setTravelClass(TravelClass.ECONOMY);

        List<SearchResult> searchResults = flightSearchService.matchingFlightsWithFareDetails(searchCriteria);
        float actualBookingAmount = searchResults.get(0).getTotalPrice();
        assertEquals(expectedBookingAmount, actualBookingAmount, 0.0f);
    }

    @Test
    public void shouldNotGetFlightsWhenFlightsMatchingSearchCriteriaAreCompletelyBooked() throws ParseException {
        searchCriteria.setSource("HYD");
        searchCriteria.setDestination("GOA");
        searchCriteria.setTravelClass(TravelClass.BUSINESS);
        searchCriteria.setSeatsRequested(Optional.of(2));
        
        List<Flight> listOfFlights = flightSearchService.search(searchCriteria);
        assertTrue(listOfFlights.isEmpty());
    }
}