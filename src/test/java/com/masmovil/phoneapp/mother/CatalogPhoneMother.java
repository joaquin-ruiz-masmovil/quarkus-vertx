package com.masmovil.phoneapp.mother;


import com.masmovil.phoneapp.domain.model.CatalogPhone;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CatalogPhoneMother {

  public static List<CatalogPhone> generatePhoneCatalog() {
    return Arrays.asList(generateRandomPhoneByName("Ericsson"),
        generateRandomPhoneByName("Nokia"),
        generateRandomPhoneByName("iPhone"),
        generateRandomPhoneByName("Blackberry"),
        generateRandomPhoneByName("Samsung"),
        generateRandomPhoneByName("Xiaomi"));
  }

  public static CatalogPhone generateRandomPhoneByName(String name) {

    return CatalogPhone.of(
        UUID.randomUUID(),
        name,
        RandomStringUtils.randomAlphabetic(50),
        "http://www." + RandomStringUtils.randomAlphabetic(8) + ".com/image.png",
        1
    );
  }
}
