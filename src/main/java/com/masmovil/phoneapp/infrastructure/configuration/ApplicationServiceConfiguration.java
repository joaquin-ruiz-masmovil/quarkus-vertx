package com.masmovil.phoneapp.infrastructure.configuration;

import com.masmovil.phoneapp.application.createcostumerorder.CreateCostumerOrderService;
import com.masmovil.phoneapp.application.getphonecatalog.GetPhoneCatalogService;
import com.masmovil.phoneapp.domain.event.service.DomainEventPublisher;
import com.masmovil.phoneapp.domain.model.CostumerOrder;
import com.masmovil.phoneapp.domain.repository.CatalogPhoneRepository;
import io.quarkus.arc.DefaultBean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class ApplicationServiceConfiguration {

  @Produces
  @ApplicationScoped
  @DefaultBean
  public GetPhoneCatalogService getPhoneCatalogService(final CatalogPhoneRepository catalogPhoneRepository) {
    return new GetPhoneCatalogService(catalogPhoneRepository);
  }

  @Produces
  @ApplicationScoped
  @DefaultBean
  public CreateCostumerOrderService createCostumerOrderService(final CatalogPhoneRepository catalogPhoneRepository,
                                                               DomainEventPublisher<CostumerOrder> costumerOrderPublisher) {
    return new CreateCostumerOrderService(catalogPhoneRepository, costumerOrderPublisher);
  }
}
