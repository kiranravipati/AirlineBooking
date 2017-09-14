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
import java.time.Month;
import java.util.*;
import java.util.function.Predicate;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FlightSearchService.class)
public class FlightSearchServiceTest {
    @MockBean
    private FlightRepository flightRepository;

    @Autowired(required = true)
    private FlightSearchService flightSearchService;

    Carrier boeingCarrier, airbusCarrier;
    Flight mockFlight1, mockFlight2, mockFlight3;
    private List<Flight> listOfMockFlights;
    List<Flight> expectedFlightList;
    SearchCriteria searchCriteria = new SearchCriteria();

    public Carrier createBoeingCarrier() {
        Map<TravelClass, Seating> mapOfSeatsAndPricePerClassForBoeing = new HashMap<>();
        Seating economyClassInfo = new Seating(60, 50, 5000f);
        Seating businessClassInfo = new Seating(20, 0, 8000f);
        Seating firstClassInfo = new Seating(10, 2, 10000f);

        mapOfSeatsAndPricePerClassForBoeing.put(TravelClass.ECONOMY, economyClassInfo);
        mapOfSeatsAndPricePerClassForBoeing.put(TravelClass.BUSINESS, businessClassInfo);
        mapOfSeatsAndPricePerClassForBoeing.put(TravelClass.FIRST, firstClassInfo);

        return new Carrier(CarrierType.BOEING777, mapOfSeatsAndPricePerClassForBoeing);
    }

    public Carrier createAirbusCarrier() {
        Map<TravelClass, Seating> mapOfSeatsAndPricePerClassForAirbus = new HashMap<>();
        Seating economyClassInfo = new Seating(60, 30, 4000f);
        Seating businessClassInfo = new Seating(20, 0, 7000f);

        mapOfSeatsAndPricePerClassForAirbus.put(TravelClass.ECONOMY, economyClassInfo);
        mapOfSeatsAndPricePerClassForAirbus.put(TravelClass.BUSINESS, businessClassInfo);

        return new Carrier(CarrierType.AIRBUS321, mapOfSeatsAndPricePerClassForAirbus);
    }

    @Before
    public void Setup() {
        boeingCarrier = createBoeingCarrier();
        airbusCarrier = createAirbusCarrier();
        mockFlight1 = new Flight("SPJ5544", "HYD", "GOA", LocalDate.now(), boeingCarrier);
        mockFlight2 = new Flight("SPJ4747", "HYD", "GOA", LocalDate.of(2017, Month.SEPTEMBER, 12), airbusCarrier);
        mockFlight3 = new Flight("SPJ3232", "HYD", "BLR", LocalDate.of(2017, Month.SEPTEMBER, 12), boeingCarrier);

        listOfMockFlights = new ArrayList<>(Arrays.asList(mockFlight1, mockFlight2, mockFlight3));
        Mockito.when(flightRepository.getFlights()).thenReturn(listOfMockFlights);
        searchCriteria.setSeatsRequested(Optional.of(1));
    }

    @Test
    public void shouldGetFlightsBetweenCities() throws ParseException {
        searchCriteria.setSource("HYD");
        searchCriteria.setDestination("GOA");
        searchCriteria.setSeatsRequested(Optional.of(1));

        List<Flight> actualFlightsList = flightSearchService.search(searchCriteria);
        expectedFlightList = new ArrayList<Flight>(Arrays.asList(mockFlight1, mockFlight2));

        assertEquals(actualFlightsList, expectedFlightList);
    }

    @Test
    public void shouldGetFlightsBetweenCitiesBasedOnDepartureDate() throws ParseException {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("HYD");
        searchCriteria.setDestination("GOA");
        searchCriteria.setDepartureDateString(LocalDate.now().toString());

        List<Flight> actualFlightsList = flightSearchService.search(searchCriteria);
        expectedFlightList = new ArrayList<Flight>(Arrays.asList(mockFlight1));

        assertEquals(expectedFlightList, actualFlightsList);
    }

    @Test
    public void shouldGetFlightsBetweenCitiesForGivenNumberOfPassengersBasedOnTravelClass() throws ParseException {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("HYD");
        searchCriteria.setDestination("GOA");
        searchCriteria.setSeatsRequested(Optional.of(2));
        searchCriteria.setTravelClass(TravelClass.ECONOMY);

        List<Flight> actualFlightsList= flightSearchService.search(searchCriteria);
        expectedFlightList = new ArrayList<Flight>(Arrays.asList(mockFlight1, mockFlight2));

        assertEquals(expectedFlightList, actualFlightsList);
    }

    @Test
    public void verifyBookingAmountFor2PassengersForEconomyClassOnBoeingCarrier() throws ParseException {
        searchCriteria.setSource("HYD");
        searchCriteria.setDestination("GOA");
        searchCriteria.setSeatsRequested(Optional.of(2));
        searchCriteria.setTravelClass(TravelClass.ECONOMY);

        List<SearchResult> searchResults = flightSearchService.matchingFlightsWithFareDetails(searchCriteria);
        Predicate <SearchResult> flightWithNumber = searchResult -> (searchResult.getFlightNumber().equals(mockFlight1.getFlightNumber()));
        Optional <SearchResult> searchResult = searchResults.stream().filter(flightWithNumber).findFirst();
        float actualBookingAmount = searchResult.get().getTotalPrice();
        assertEquals(10000f, actualBookingAmount,0.0f);
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

    @Test
    public void verifyBookingAmountForFirst40PercentIsSameAsBasePriceForEconomyClassOnAnyCarrier() {
        searchCriteria.setSource("HYD");
        searchCriteria.setDestination("GOA");
        searchCriteria.setTravelClass(TravelClass.ECONOMY);

    }

    @Test
    public void verifyBookingAmountForNext50PercentIs30PercentExtraOfBasePriceForEconomyClassOnAnyCarrier() {
        searchCriteria.setSource("HYD");
        searchCriteria.setDestination("GOA");
        searchCriteria.setTravelClass(TravelClass.ECONOMY);
    }

    @Test
    public void verifyBookingAmountForLast10PercentIs60PercentExtraOfBasePriceForEconomyClassOnAnyCarrier() {
        searchCriteria.setSource("HYD");
        searchCriteria.setDestination("GOA");
        searchCriteria.setTravelClass(TravelClass.ECONOMY);
    }
}