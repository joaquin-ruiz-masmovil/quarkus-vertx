package com.masmovil.phoneapp.domain.model;

public final class OrderPhone {

  private final String name;
  private final Integer price;

  private OrderPhone(String name, Integer price) {
    this.name = name;
    this.price = price;
  }

  public static OrderPhone of(String name, Integer price) {
    return new OrderPhone(name, price);
  }

  public String getName() {
    return name;
  }

  public Integer getPrice() {
    return price;
  }
}
