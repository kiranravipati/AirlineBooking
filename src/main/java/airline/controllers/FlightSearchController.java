package airline.controllers;

import airline.models.*;
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
        model.addAttribute("searchCriteria", new SearchCriteria());
        model.addAttribute("serviceClassList", TravelClass.values());

        return "flightSearch";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String getFlights(@ModelAttribute(value = "searchCriteria") SearchCriteria searchCriteria, Model model, BindingResult bindingResult) throws ParseException {
        List<SearchResult> searchResults = flightSearchService.matchingFlightsWithFareDetails(searchCriteria);
        boolean resultsFound = searchResults.size() > 0;
        model.addAttribute("serviceClassList", TravelClass.values());
        model.addAttribute("resultsFound", resultsFound);
        model.addAttribute("searchResults", searchResults);

        return "flightSearch";
    }


    // List<City> cities = cityRepository.getCities();
    // model.addAttribute("cities", cities);
    @ModelAttribute("cities")
    public List<City> getCities() {
        return cityRepository.getCities();
    }
}
