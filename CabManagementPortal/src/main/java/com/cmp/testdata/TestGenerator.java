package com.cmp.testdata;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cmp.model.Cab;
import com.cmp.model.CabStatus;
import com.cmp.model.City;

@Component
public class TestGenerator {

  @Autowired
  Inventory inventory;

  @PostConstruct
  public void createInitialTestData() {

    City ct1 = new City("Pune", "MH");
    City ct2 = new City("Mumbai", "MH");
    City ct3 = new City("Delhi", "DL");
    inventory.addCity(ct1);
    inventory.addCity(ct2);
    inventory.addCity(ct3);

    Cab c1 = new Cab("MH-12-AA-1111", ct1, CabStatus.IDLE);
    Cab c2 = new Cab("MH-12-AA-1112", ct1, CabStatus.IDLE);
    Cab c3 = new Cab("MH-12-AA-1113", ct2, CabStatus.IDLE);
    Cab c4 = new Cab("MH-12-AA-1114", ct2, CabStatus.IDLE);
    Cab c5 = new Cab("MH-12-AA-1115", ct3, CabStatus.IDLE);
    inventory.addCab(c1);
    inventory.addCab(c2);
    inventory.addCab(c3);
    inventory.addCab(c4);
    inventory.addCab(c5);
  }

}
