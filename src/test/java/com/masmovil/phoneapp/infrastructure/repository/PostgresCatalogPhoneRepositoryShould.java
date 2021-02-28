package com.masmovil.phoneapp.infrastructure.repository;

import com.masmovil.phoneapp.domain.model.CatalogPhone;
import com.masmovil.phoneapp.domain.repository.CatalogPhoneRepository;
import com.masmovil.phoneapp.infrastructure.repository.mapper.PostgresCatalogPhoneMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.reactivex.Single;
import io.vertx.mutiny.pgclient.PgPool;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class PostgresCatalogPhoneRepositoryShould {

  @Inject
  Flyway flyway;

  @Inject
  PgPool jdbcPool;

  private CatalogPhoneRepository phoneRepository;

  @BeforeEach
  public void setUp() {
    phoneRepository = new PostgresCatalogPhoneRepository(jdbcPool, new PostgresCatalogPhoneMapper());
    flyway.clean();
    flyway.migrate();
  }

  @Test
  public void returnAllPhonesCatalog() {
    // given
    jdbcPool.preparedQuery(
        "INSERT INTO PHONECATALOG.PHONES_CATALOGUE(PC_ID, PC_NAME, PC_PRICE, PC_IMAGE, PC_DESCRIPTION) VALUES\n" +
            "('" + UUID.randomUUID() + "', 'EricssonTest', 100, 'http://www.ericsson.com/image.png', 'Ericsson Mobile'),\n" +
            "('" + UUID.randomUUID() + "', 'NokiaTest', 100, 'http://www.nokia.com/image.png', 'Nokia Mobile'),\n" +
            "('" + UUID.randomUUID() + "', 'iPhoneTest', 100, 'http://www.apple.com/image.png', 'Apple Mobile'),\n" +
            "('" + UUID.randomUUID() + "', 'BlackberryTest', 100, 'http://www.blackberry.com/image.png', 'Blackberry Mobile'),\n" +
            "('" + UUID.randomUUID() + "', 'SamsungTest', 100, 'http://www.samsung.com/image.png', 'Samsung Mobile'),\n" +
            "('" + UUID.randomUUID() + "', 'XiaomiTest', 100, 'http://www.xiaomi.com/image.png', 'Xiaomi Mobile');\n"
    ).execute().subscribe().with(result -> assertNotNull(result));

    // when
    Single<List<CatalogPhone>> result = phoneRepository.getPhoneCatalog();

    // then
    List<CatalogPhone> resultList = result.blockingGet();
    assertEquals(resultList.size(), 6);
    List<String> phonesList = resultList.stream().map(CatalogPhone::getName).collect(Collectors.toList());
    assertTrue(phonesList.contains("EricssonTest"));
    assertTrue(phonesList.contains("NokiaTest"));
    assertTrue(phonesList.contains("iPhoneTest"));
    assertTrue(phonesList.contains("BlackberryTest"));
    assertTrue(phonesList.contains("SamsungTest"));
    assertTrue(phonesList.contains("XiaomiTest"));
  }

  @Test
  public void returnAnyPhonesCatalog() {
    // given

    // when
    Single<List<CatalogPhone>> result = phoneRepository.getPhoneCatalog();

    // then
    List<CatalogPhone> resultList = result.blockingGet();
    assertEquals(resultList.size(), 0);
  }
}
