package com.masmovil.phoneapp.infrastructure.routerhandler;

import com.masmovil.phoneapp.application.getphonecatalog.GetPhoneCatalogService;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

import static io.netty.handler.codec.http.HttpHeaderValues.TEXT_PLAIN;

@RouteBase(path = "/v1", produces = "application/json")
@ApplicationScoped
public class RestPhoneCatalogRouter {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestPhoneCatalogRouter.class);

  private GetPhoneCatalogService getPhoneCatalogService;

  public RestPhoneCatalogRouter(GetPhoneCatalogService getPhoneCatalogService) {
    this.getPhoneCatalogService = getPhoneCatalogService;
  }

  @Route(methods = HttpMethod.GET, path = "/phones")
  public void getPhoneCatalog(RoutingContext rc) {

    LOGGER.info("Handler {} {}", rc.currentRoute().methods().toString(), rc.currentRoute().getPath());

    try {

      getPhoneCatalogService.execute().subscribe(
          result -> {
            LOGGER.info("GET Phone Catalog: " + result.size());
            rc.response()
                .setChunked(true)
                .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                .setStatusCode(HttpResponseStatus.OK.code())
                .end(Json.encodePrettily(result));
          },
          error -> {
            LOGGER.error("GET Phone Catalog fails: " + error.getMessage());
            handleError(error, rc.response());
          }
      );
    } catch (Exception e) {
      handleError(e, rc.response());
    }

  }

  private void handleError(Throwable error, HttpServerResponse response) {
    response.putHeader(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN)
        .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
        .end(error.getMessage());
  }

}
