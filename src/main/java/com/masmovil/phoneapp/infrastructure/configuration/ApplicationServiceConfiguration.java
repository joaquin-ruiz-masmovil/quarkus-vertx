package com.masmovil.phoneapp.infrastructure.configuration;

import com.masmovil.phoneapp.application.getphonecatalog.GetPhoneCatalogService;
import com.masmovil.phoneapp.domain.repository.CatalogPhoneRepository;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class ApplicationServiceConfiguration {

  @Produces
  public GetPhoneCatalogService getPhoneCatalogService(final CatalogPhoneRepository catalogPhoneRepository) {
    return new GetPhoneCatalogService(catalogPhoneRepository);
  }

}
