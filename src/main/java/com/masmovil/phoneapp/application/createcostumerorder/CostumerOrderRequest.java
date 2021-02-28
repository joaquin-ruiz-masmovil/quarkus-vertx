package com.masmovil.phoneapp.application.createcostumerorder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

public class CostumerOrderRequest {

  private String name;
  private String surname;
  private String email;
  private List<String> phonesNames;

  @JsonCreator
  public CostumerOrderRequest(
      @JsonProperty(value = "name", required = true) String name,
      @JsonProperty(value = "surname", required = true) String surname,
      @JsonProperty(value = "email", required = true) String email,
      @JsonProperty(value = "phonesNames", required = true) List<String> phonesNames) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.phonesNames = phonesNames;
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

  @Override
  public String toString() {
    return new StringJoiner(", ", CostumerOrderRequest.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("surname='" + surname + "'")
        .add("email='" + email + "'")
        .add("phonesNames=" + phonesNames)
        .toString();
  }
}
