package airline.models;

import airline.repositories.CarrierRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CarrierTest {
    CarrierRepository carrierRepositoryTest;
    Map<CarrierType, Carrier>testMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        carrierRepositoryTest = new CarrierRepository();
        carrierRepositoryTest.createDefaultCarriers();
        testMap = carrierRepositoryTest.getCarriers();
    }

    @Test
    public void testValidityOfAirBus321() {
        boolean validCarrierType = testMap.get(CarrierType.AIRBUS321).isValidCarrierType(CarrierType.AIRBUS321);
        Assert.assertEquals(true, validCarrierType);
    }

    @Test
    public void testValidityOfAirBus319V2() {
        boolean validCarrierType = testMap.get(CarrierType.AIRBUS319V2).isValidCarrierType(CarrierType.AIRBUS319V2);
        Assert.assertEquals(true, validCarrierType);
    }

    @Test
    public void testValidityOfBoeing777() {
        boolean validCarrierType = testMap.get(CarrierType.BOEING777).isValidCarrierType(CarrierType.BOEING777);
        Assert.assertEquals(true, validCarrierType);
    }

    @Test
    public void testNotValidCarrierType() {
        boolean validCarrierType = testMap.get(CarrierType.BOEING777).isValidCarrierType(CarrierType.AIRBUS321);
        Assert.assertEquals(false, validCarrierType);
    }

    @Test
    public void testBoeingHasSeatsInEconomy() {
        Carrier boeingCarrier = testMap.get(CarrierType.BOEING777);
        boolean hasFirstClass = boeingCarrier.hasTravelClass(TravelClass.FIRST);
        Assert.assertEquals(true, hasFirstClass);
    }

    @Test
    public void testAirBus319V2HasOnlyEconomyClass() {
        Carrier airBus319V2Carrier = testMap.get(CarrierType.AIRBUS319V2);
        boolean hasFirstClass = airBus319V2Carrier.hasTravelClass(TravelClass.FIRST);
        boolean hasBusinessClass = airBus319V2Carrier.hasTravelClass(TravelClass.BUSINESS);
        Assert.assertEquals(false, hasFirstClass || hasBusinessClass);
    }

    @Test
    public void testAirBus321HasNoFirstClass() {
        Carrier airBus321Carrier = testMap.get(CarrierType.AIRBUS321);
        boolean hasFirstClass = airBus321Carrier.hasTravelClass(TravelClass.FIRST);
        Assert.assertFalse(hasFirstClass);
    }

    @Test
    public void testAirBus321HasEconomyAndBusinessClass() {
        Carrier airBus321Carrier = testMap.get(CarrierType.AIRBUS321);
        boolean hasBusinessClass = airBus321Carrier.hasTravelClass(TravelClass.BUSINESS);
        boolean hasEconomyClass = airBus321Carrier.hasTravelClass(TravelClass.ECONOMY);

        Assert.assertTrue(hasBusinessClass && hasEconomyClass);
    }
}