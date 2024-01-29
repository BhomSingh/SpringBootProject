package com.water.can.WaterCanal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee{

  private @Id
  @GeneratedValue
  Long id;

  private String name;

  private String dept;

  private int salary;

}