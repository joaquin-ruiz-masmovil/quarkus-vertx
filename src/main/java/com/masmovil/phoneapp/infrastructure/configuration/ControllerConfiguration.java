package com.masmovil.phoneapp.infrastructure.configuration;

import com.masmovil.phoneapp.application.getphonecatalog.GetPhoneCatalogService;
import com.masmovil.phoneapp.infrastructure.controller.PhoneCatalogController;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class ControllerConfiguration {

  @Produces
  PhoneCatalogController phoneCatalogController(GetPhoneCatalogService getPhoneCatalogService) {
    return new PhoneCatalogController(getPhoneCatalogService);
  }

}
