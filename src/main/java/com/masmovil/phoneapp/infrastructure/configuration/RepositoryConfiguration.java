package com.masmovil.phoneapp.infrastructure.configuration;

import com.masmovil.phoneapp.domain.repository.CatalogPhoneRepository;
import com.masmovil.phoneapp.infrastructure.repository.PostgresCatalogPhoneRepository;
import com.masmovil.phoneapp.infrastructure.repository.mapper.PostgresCatalogPhoneMapper;
import io.vertx.mutiny.pgclient.PgPool;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class RepositoryConfiguration {

  @Produces
  public CatalogPhoneRepository phoneRepository(PgPool jdbcPool) {
    return new PostgresCatalogPhoneRepository(jdbcPool, new PostgresCatalogPhoneMapper());
  }

}
