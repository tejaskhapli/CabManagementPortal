package com.cmp.testdata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.cmp.model.Cab;
import com.cmp.model.CabStatus;
import com.cmp.model.City;

@Service
public class Inventory {

  private List<Cab> cabs;
  private List<City> cities;

  public Inventory() {
    cabs = new ArrayList<>();
    cities = new ArrayList<>();
  }

  public void addCab(Cab c) {
    cabs.add(c);
  }

  public List<Cab> fetchCabs() {
    return cabs;
  }

  public List<Cab> fetchIdleCabs(String sourceCity) {
    return cabs.stream()
            .filter(c -> c.getCity().getName().equalsIgnoreCase(sourceCity))
            .filter(c -> c.getStatus() == CabStatus.IDLE)
            .collect(Collectors.toList());
  }

  
  
  public void addCity(City c) {
    cities.add(c);
  }

  public List<City> fetchCities() {
    return cities;
  }

}

/*

    {
        "regNumber": "MH-12-AA-1111",
        "city": {
            "name": "Pune",
            "state": "MH"
        },
        "status": "IDLE"
    }
   
   {
      "name": "Chennai",
      "state": "TN"
    }

 */
