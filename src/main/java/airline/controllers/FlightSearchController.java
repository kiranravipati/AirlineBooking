package airline.controllers;

import airline.models.City;
import airline.models.Flight;
import airline.models.SearchCriteria;
import airline.services.FlightSearchService;
import airline.repositories.CityRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.*;

/**
 * Created by rajashrk on 8/8/17.
 */

@Controller
public class FlightSearchController {
    public enum ServiceClass { ECONOMY, BUSINESS, FIRST };
    //@Autowired
    CityRepository cityRepository;
    FlightSearchService flightSearchService;

    @RequestMapping(value = "/airlineTicketing", method = RequestMethod.GET)
    public String getCities(Model model) {
        CityRepository cityRepository = CityRepository.getSharedInstance();
        List<City> cities = cityRepository.getCities();

        model.addAttribute("cities", cities);
        model.addAttribute("searchCriteria", new SearchCriteria());

        List<ServiceClass> serviceClassList = Arrays.asList(ServiceClass.values());
        System.out.println(serviceClassList);
        model.addAttribute("serviceClassList", serviceClassList);

        return "flightSearch";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String getFlights(@ModelAttribute(value = "searchCriteria") SearchCriteria searchCriteria, Model model) throws ParseException {
        System.out.println(searchCriteria);
        flightSearchService = new FlightSearchService();
        List<Flight> matchedFlights = flightSearchService.search(searchCriteria);
        model.addAttribute("searchResults", matchedFlights);

        return "flightsView";
    }
}
