package airline.controllers;

import airline.models.City;
import airline.models.Flight;
import airline.models.SearchCriteria;
import airline.models.TravelClass;
import airline.services.FlightSearchService;
import airline.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.*;

@Controller
public class FlightSearchController {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    FlightSearchService flightSearchService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCities(Model model) {
        List<City> cities = cityRepository.getCities();

        model.addAttribute("cities", cities);
        model.addAttribute("searchCriteria", new SearchCriteria());
        model.addAttribute("serviceClassList", TravelClass.values());

        return "flightSearch";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String getFlights(@ModelAttribute(value = "searchCriteria") SearchCriteria searchCriteria, Model model, BindingResult bindingResult) throws ParseException {
        List<Flight> matchedFlights = flightSearchService.search(searchCriteria);
        boolean flightsFound;
        flightsFound = matchedFlights.size() > 0;

        List<City> cities = cityRepository.getCities();
        model.addAttribute("cities", cities);
        model.addAttribute("searchCriteria", new SearchCriteria());
        model.addAttribute("serviceClassList", TravelClass.values());
        model.addAttribute("flightsFound", flightsFound);
        model.addAttribute("searchResults", matchedFlights);

        return "flightSearch";
    }
}
