package com.masmovil.phoneapp.domain.model;

import java.util.UUID;

public final class CatalogPhone {

  private final UUID id;
  private final String name;
  private final String description;
  private final String image;
  private final Integer price;

  private CatalogPhone(UUID id, String name, String description, String image, Integer price) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.image = image;
    this.price = price;
  }

  public static CatalogPhone of(UUID id, String name, String description, String image, Integer price) {
    return new CatalogPhone(id, name, description, image, price);
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getImage() {
    return image;
  }

  public Integer getPrice() {
    return price;
  }
}
