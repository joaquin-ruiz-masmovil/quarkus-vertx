package com.masmovil.phoneapp.infrastructure.controller;

import com.masmovil.phoneapp.application.getphonecatalog.GetPhoneCatalogService;
import com.masmovil.phoneapp.application.getphonecatalog.PhoneCatalogInfo;
import io.smallrye.mutiny.Uni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/phones")
@ApplicationScoped
public class PhoneCatalogController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PhoneCatalogController.class);

  private GetPhoneCatalogService getPhoneCatalogService;

  public PhoneCatalogController(GetPhoneCatalogService getPhoneCatalogService) {
    this.getPhoneCatalogService = getPhoneCatalogService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<List<PhoneCatalogInfo>> getPhoneCatalog() {

    LOGGER.info("GET Phone Catalog");

    try {

      return getPhoneCatalogService.execute();

    } catch (RuntimeException e) {
      LOGGER.error("GET Phone Catalog fails -> Exception: {}", e.getMessage(), e);
      return Uni.createFrom().item(List.of());
    }

  }


}
