package com.cmp.web;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cmp.model.Cab;
import com.cmp.model.CabStatus;
import com.cmp.model.City;
import com.cmp.testdata.Inventory;

@RestController
public class Controller {

  @Autowired
  Inventory inventory;

  @GetMapping("/greeting")
  public void greeting() {
    System.out.println("Hello");
  }

  @GetMapping("/cabs")
  public List<Cab> getCabs() {
    return inventory.fetchCabs();
  }

  @PostMapping("/cabs")
  public ResponseEntity<String> addCab(@RequestBody Cab c) {
    inventory.addCab(c);
    return ResponseEntity.ok("Cab Addeed to the Inventory");
  }

  @GetMapping("/cities")
  public List<City> getCities() {
    return inventory.fetchCities();
  }

  @PostMapping("/cities")
  public ResponseEntity<String> addCity(@RequestBody City c) {
    inventory.addCity(c);
    return ResponseEntity.ok("City Addeed to the Inventory");
  }

  @PostMapping("/book")
  public ResponseEntity<String> bookCab(@RequestBody String sourceCity) {

    List<Cab> cabs = inventory.fetchIdleCabs(sourceCity);

    Optional<Cab> c = cabs.stream().min((c1, c2) -> c1.getIdleTime() - c2.getIdleTime());

    if (c.isPresent()) {
      updateCabStatus(c.get().getRegNumber(), CabStatus.ON_TRIP);
      return ResponseEntity.ok("Cab with Reg No : " + c.get().getRegNumber() + " is booked");
    }

    else
      return ResponseEntity.ok("No Cabs Found");
  }

  @PutMapping("/change-status")
  public ResponseEntity<String> changeStatus(@RequestBody String regNumber) {

    updateCabStatus(regNumber, CabStatus.IDLE);
    return ResponseEntity.ok(" Status Update for Cab :-" + regNumber);
  }
  
  private void updateCabStatus(String regNo, CabStatus status) {
    for (Cab c : inventory.fetchCabs()) {
      if (c.getRegNumber().equalsIgnoreCase(regNo))
        c.updateStatus(status);
    }
  }



}
