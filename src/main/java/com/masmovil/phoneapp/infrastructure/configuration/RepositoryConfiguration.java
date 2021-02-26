package com.masmovil.phoneapp.infrastructure.configuration;

import com.masmovil.phoneapp.domain.repository.CatalogPhoneRepository;
import com.masmovil.phoneapp.infrastructure.repository.RandomCatalogPhoneRepositoryImpl;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class RepositoryConfiguration {

  @Produces
  public CatalogPhoneRepository phoneRepository() {
    return new RandomCatalogPhoneRepositoryImpl();
  }

}
