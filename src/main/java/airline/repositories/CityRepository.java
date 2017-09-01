package airline.repositories;
import airline.models.City;

import java.util.ArrayList;
import java.util.List;

public class CityRepository {
    private static final CityRepository sharedInstance = new CityRepository();
    private static List<City> cities;

    private CityRepository() {
        cities = new ArrayList<City>();
    }

    public static CityRepository getSharedInstance() {
        return sharedInstance;
    }

    public void createDefaultCities() {
        cities.add(new City("HYD", "Hyderabad"));
        cities.add(new City("BLR", "Bengaluru"));
        cities.add(new City("PUN", "Pune"));
        cities.add(new City("DLH", "Delhi"));
    }

    public List<City> getCities() {
        return cities;
    }
}
