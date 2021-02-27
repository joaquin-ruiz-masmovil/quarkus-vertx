package com.masmovil.phoneapp.application.getphonecatalog;

import com.masmovil.phoneapp.domain.model.CatalogPhone;
import com.masmovil.phoneapp.domain.repository.CatalogPhoneRepository;
import io.reactivex.Single;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GetPhoneCatalogService {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetPhoneCatalogService.class);

  private final CatalogPhoneRepository catalogPhoneRepository;

  public GetPhoneCatalogService(CatalogPhoneRepository catalogPhoneRepository) {
    this.catalogPhoneRepository = catalogPhoneRepository;
  }

  public Single<List<PhoneCatalogInfo>> execute() {

    LOGGER.info("Executing -> 'Get Phone Catalog Service'");

    return catalogPhoneRepository.getPhoneCatalog()
        .map(this::mapToCatalogInfo);

  }

  private List<PhoneCatalogInfo> mapToCatalogInfo(List<CatalogPhone> catalogPhoneList) {

    return catalogPhoneList.stream()
        .map(phone -> PhoneCatalogInfo.of(phone.getName(), phone.getDescription(), phone.getImage(), phone.getPrice()))
        .collect(Collectors.toList());
  }

}
