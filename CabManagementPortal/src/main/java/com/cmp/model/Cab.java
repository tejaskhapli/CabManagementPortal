package com.cmp.model;

import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cab {


  public Cab(String rNo, City city, CabStatus status) {
    this.regNumber = rNo;
    this.city = city;
    this.status = status;
    this.idleTime = 0 + new Random().nextInt(100 - 0 + 1);
  }
  
  public void updateStatus(CabStatus status) {
    this.status = status;
  }
  
  private String regNumber;
  private City city;
  private CabStatus status;
  private int idleTime;
}
