package com.masmovil.phoneapp.domain.model;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class CostumerOrder {

  private final UUID id;
  private final String name;
  private final String surname;
  private final String email;
  private final List<OrderPhone> orderPhones;
  private final Integer totalPrice;

  private CostumerOrder(final Builder builder) {

    this.id = UUID.randomUUID();
    this.name = builder.name;
    this.surname = builder.surname;
    this.email = builder.email;
    this.orderPhones = builder.phoneCatalog.stream()
        .filter(p -> builder.phonesNames.contains(p.getName()))
        .map(p -> OrderPhone.of(p.getName(), p.getPrice()))
        .collect(Collectors.toList());
    this.totalPrice = orderPhones.stream()
        .mapToInt(OrderPhone::getPrice)
        .sum();

  }

  public static Builder create() {
    return new Builder();
  }

  public UUID getId() {
    return id;
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

  public List<String> getPhoneNames() {
    return orderPhones.stream().map(p -> p.getName()).collect(Collectors.toList());
  }

  public Integer getTotalPrice() {
    return totalPrice;
  }

  public static class Builder {
    private String name;
    private String surname;
    private String email;
    private List<String> phonesNames;
    private List<CatalogPhone> phoneCatalog;

    public Builder withName(String name) {
      this.name = name;
      return this;
    }
    public Builder withSurname(String surname) {
      this.surname = surname;
      return this;
    }
    public Builder withEmail(String email) {
      this.email = email;
      return this;
    }
    public Builder withPhonesNames(List<String> phonesNames) {
      this.phonesNames = phonesNames;
      return this;
    }
    public Builder withPhoneCatalog(List<CatalogPhone> phoneCatalog) {
      this.phoneCatalog = phoneCatalog;
      return this;
    }

    public CostumerOrder build() {
      return new CostumerOrder(this);
    }
  }



}
