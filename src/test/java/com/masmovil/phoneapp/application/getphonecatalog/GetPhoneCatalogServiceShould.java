package com.masmovil.phoneapp.application.getphonecatalog;

import com.google.common.collect.Lists;
import com.masmovil.phoneapp.domain.model.CatalogPhone;
import com.masmovil.phoneapp.domain.repository.CatalogPhoneRepository;
import com.masmovil.phoneapp.mother.CatalogPhoneMother;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@QuarkusTest
public class GetPhoneCatalogServiceShould {


  private CatalogPhoneRepository catalogPhoneRepositoryMock = mock(CatalogPhoneRepository.class);

  private GetPhoneCatalogService getPhoneCatalogService;

  @BeforeEach
  public void setUp() {
    getPhoneCatalogService = new GetPhoneCatalogService(catalogPhoneRepositoryMock);
  }

  @Test
  public void returnAllPhonesCatalog() {
    // given
    Uni<List<CatalogPhone>> phoneCatalog = Uni.createFrom().item(CatalogPhoneMother.generatePhoneCatalog());
    when(catalogPhoneRepositoryMock.getPhoneCatalog())
        .thenReturn(phoneCatalog);

    // when
    Uni<List<PhoneCatalogInfo>> result = getPhoneCatalogService.execute();

    // then
    result.subscribe().with(resultlist -> {
      List<String> catalogPhoneNames = resultlist.stream()
          .map(p -> p.getName())
          .collect(Collectors.toList());
      assertEquals(resultlist.size(), catalogPhoneNames.size());
      assertTrue(catalogPhoneNames.contains("Ericsson"));
      assertTrue(catalogPhoneNames.contains("Nokia"));
      assertTrue(catalogPhoneNames.contains("iPhone"));
      assertTrue(catalogPhoneNames.contains("Blackberry"));
      assertTrue(catalogPhoneNames.contains("Samsung"));
      assertTrue(catalogPhoneNames.contains("Xiaomi"));
    });

  }

  @Test
  public void returnAnyPhonesCatalog() {

    // given
    List<CatalogPhone> phoneCatalog = Lists.newArrayList();
    when(catalogPhoneRepositoryMock.getPhoneCatalog())
        .thenReturn(Uni.createFrom().item(phoneCatalog));

    // when
    Uni<List<PhoneCatalogInfo>> result = getPhoneCatalogService.execute();

    // then
    result.subscribe().with(resultlist -> {
      assertEquals(resultlist.size(), 0);
    });

  }


}
