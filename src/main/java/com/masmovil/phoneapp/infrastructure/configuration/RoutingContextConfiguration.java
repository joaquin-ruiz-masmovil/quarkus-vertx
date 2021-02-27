package com.masmovil.phoneapp.infrastructure.configuration;

import com.masmovil.phoneapp.application.getphonecatalog.GetPhoneCatalogService;
import com.masmovil.phoneapp.infrastructure.routerhandler.RestPhoneCatalogRouter;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class RoutingContextConfiguration {

  @Produces
  RestPhoneCatalogRouter phoneCatalogController(GetPhoneCatalogService getPhoneCatalogService) {
    return new RestPhoneCatalogRouter(getPhoneCatalogService);
  }

}
