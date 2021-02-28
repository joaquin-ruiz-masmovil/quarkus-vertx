package com.masmovil.phoneapp.application.createcostumerorder;

import java.util.List;
import java.util.StringJoiner;

public class CostumerOrderResponse {

  private String name;
  private String surname;
  private String email;
  private List<String> phonesNames;
  private Integer totalPrice;

  public CostumerOrderResponse(String name, String surname, String email, List<String> phonesNames, Integer totalPrice) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.phonesNames = phonesNames;
    this.totalPrice = totalPrice;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getEmail() {
    return email;
  }

  public List<String> getPhonesNames() {
    return phonesNames;
  }

  public Integer getTotalPrice() {
    return totalPrice;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CostumerOrderResponse.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("surname='" + surname + "'")
        .add("email='" + email + "'")
        .add("phonesNames=" + phonesNames)
        .add("totalPrice=" + totalPrice)
        .toString();
  }
}
