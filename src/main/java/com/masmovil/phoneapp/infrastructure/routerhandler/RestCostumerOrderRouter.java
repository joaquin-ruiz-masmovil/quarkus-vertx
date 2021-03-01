package com.masmovil.phoneapp.infrastructure.routerhandler;

import com.masmovil.phoneapp.application.createcostumerorder.CostumerOrderRequest;
import com.masmovil.phoneapp.application.createcostumerorder.CreateCostumerOrderService;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.vertx.web.Body;
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
public class RestCostumerOrderRouter {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestCostumerOrderRouter.class);

  private CreateCostumerOrderService createCostumerOrderService;

  public RestCostumerOrderRouter(CreateCostumerOrderService createCostumerOrderService) {
    this.createCostumerOrderService = createCostumerOrderService;
  }

  @Route(methods = HttpMethod.POST, path = "/order")
  public void createOrder(RoutingContext rc, @Body CostumerOrderRequest costumerOrderRequest) {

    LOGGER.info("Handler {} {}", rc.currentRoute().methods().toString(), rc.currentRoute().getPath());

    try {
      createCostumerOrderService.execute(costumerOrderRequest)
          .subscribe(
              result -> {
                rc.response()
                    .setChunked(true)
                    .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                    .setStatusCode(201)
                    .end(Json.encodePrettily(result));
              },
              error -> {
                LOGGER.error("POST create order fails: " + error.getMessage());
                handleError(error.getMessage(), rc.response());
              }
          );
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      handleError(e.getMessage(), rc.response());
    }
  }

  private void handleError(String errorMessage, HttpServerResponse response) {
    response.putHeader(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN)
        .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
        .end(errorMessage);
  }

}
