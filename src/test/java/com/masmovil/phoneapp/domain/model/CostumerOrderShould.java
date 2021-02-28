package com.masmovil.phoneapp.domain.model;

import com.google.common.collect.Lists;
import com.masmovil.phoneapp.mother.CatalogPhoneMother;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class CostumerOrderShould {

  @Test
  public void returnZeroTotalPriceWhenNoBookCatalog() {
    // given
    CostumerOrder costumerOrder = CostumerOrder.create()
        .withName("User")
        .withSurname("Surname")
        .withEmail("email@email.es")
        .withPhonesNames(Lists.newArrayList("Nokia", "iPhone"))
        .withPhoneCatalog(Lists.newArrayList())
        .build();

    // when
    Integer totalPrice = costumerOrder.getTotalPrice();

    // then
    assertEquals(totalPrice, 0);

  }

  @Test
  public void createWithAllBookOrdered() {
    // given
    List<CatalogPhone> phoneCatalog = CatalogPhoneMother.generatePhoneCatalog();
    CostumerOrder costumerOrder = CostumerOrder.create()
        .withName("User")
        .withSurname("Surname")
        .withEmail("email@email.es")
        .withPhonesNames(Lists.newArrayList("Nokia", "iPhone"))
        .withPhoneCatalog(phoneCatalog)
        .build();

    // when
    List<String> phonesOrdered = costumerOrder.getPhoneNames();
    Integer totalPrice = costumerOrder.getTotalPrice();

    // then
    assertEquals(phonesOrdered, Lists.newArrayList("Nokia", "iPhone"));
    assertEquals(totalPrice, 2);

  }

  @Test
  public void createWithOnlyExistingBooksInCatalog() {
    // given
    List<CatalogPhone> phoneCatalog = CatalogPhoneMother.generatePhoneCatalog();
    CostumerOrder costumerOrder = CostumerOrder.create()
        .withName("User")
        .withSurname("Surname")
        .withEmail("email@email.es")
        .withPhonesNames(Lists.newArrayList("Nokia", "Alcatel"))
        .withPhoneCatalog(phoneCatalog)
        .build();

    // when
    List<String> phonesOrdered = costumerOrder.getPhoneNames();
    Integer totalPrice = costumerOrder.getTotalPrice();

    // then
    assertEquals(phonesOrdered, Lists.newArrayList("Nokia"));
    assertEquals(totalPrice, 1);

  }

}
