package com.masmovil.phoneapp.infrastructure.repository;

import com.masmovil.phoneapp.domain.model.CatalogPhone;
import com.masmovil.phoneapp.domain.repository.CatalogPhoneRepository;
import io.reactivex.Single;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RandomCatalogPhoneRepositoryImpl implements CatalogPhoneRepository {

  @Override
  public Single<List<CatalogPhone>> getPhoneCatalog() {
    return Single.just(Arrays.asList(generateRandomPhone("Ericsson"),
        generateRandomPhone("Nokia"),
        generateRandomPhone("iPhone"),
        generateRandomPhone("Blackberry"),
        generateRandomPhone("Samsung"),
        generateRandomPhone("Xiaomi"))
    );
  }

  private CatalogPhone generateRandomPhone(String name) {

    return CatalogPhone.of(
        UUID.randomUUID(),
        name,
        RandomStringUtils.randomAlphabetic(50),
        "http://www." + RandomStringUtils.randomAlphabetic(8) + ".com/image.png",
        new Random().ints(1, 999).findFirst().getAsInt()
    );

  }

}
