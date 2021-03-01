package com.masmovil.phoneapp.infrastructure.routerhandler;

import com.google.common.collect.Lists;
import com.masmovil.phoneapp.application.createcostumerorder.CostumerOrderRequest;
import com.masmovil.phoneapp.application.createcostumerorder.CostumerOrderResponse;
import com.masmovil.phoneapp.application.createcostumerorder.CreateCostumerOrderService;
import com.masmovil.phoneapp.application.getphonecatalog.PhoneCatalogInfo;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.reactivex.Single;
import io.vertx.core.json.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
public class RestCostumerOrderRouterShould {

  @InjectMock
  private CreateCostumerOrderService createCostumerOrderServiceMock;

  private RestCostumerOrderRouter restCostumerOrderRouter;

  @BeforeEach
  public void setUp() {
    restCostumerOrderRouter = new RestCostumerOrderRouter(createCostumerOrderServiceMock);
  }

  @Test
  public void return200WhenCostumerOrderCreated() {
    // given
    List<PhoneCatalogInfo> catalog = Lists.newArrayList(
        PhoneCatalogInfo.of("Nokia", "3030", "http:://image.jpg", 100),
        PhoneCatalogInfo.of("iPhone", "Version 12", "http:://image.png", 200)
    );
    CostumerOrderRequest costumerOrderRequest = new CostumerOrderRequest(
        "userName", "userSurname", "email@me.com",
        catalog.stream().map(phone -> phone.getName()).collect(Collectors.toList()));
    CostumerOrderResponse orderCreatedResponse = new CostumerOrderResponse(
        "userName", "userSurname", "email@me.com",
        catalog.stream().map(phone -> phone.getName()).collect(Collectors.toList()),
        300);
    when(createCostumerOrderServiceMock.execute(any(CostumerOrderRequest.class)))
        .thenReturn(Single.just(orderCreatedResponse));

    // then
    given()
        .contentType("application/json")
        .body(Json.encodePrettily(costumerOrderRequest))
        .when()
        .post("/v1/order")
        .then()
        .statusCode(201)
        .body(
            containsString("userName"),
            containsString("userSurname"),
            containsString("email@me.com"),
            containsString("Nokia"),
            containsString("iPhone"),
            containsString("totalPrice"));
  }

  @Test
  public void return500WhenFails() {
    // given
    CostumerOrderRequest costumerOrderRequest = new CostumerOrderRequest(
        "userName", "userSurname", "email@me.com",
        Lists.newArrayList("Nokia", "iPhone"));
    when(createCostumerOrderServiceMock.execute(any(CostumerOrderRequest.class)))
        .thenReturn(Single.error(new RuntimeException("Something Fails")));

    // then
    given()
        .contentType("application/json")
        .body(Json.encodePrettily(costumerOrderRequest))
        .when()
        .post("/v1/order")
        .then()
        .statusCode(500)
        .body(equalTo("Something Fails"));
  }

  @Test
  public void return500WhenException() {
    // given
    CostumerOrderRequest costumerOrderRequest = new CostumerOrderRequest(
        "userName", "userSurname", "email@me.com",
        Lists.newArrayList("Nokia", "iPhone"));
    when(createCostumerOrderServiceMock.execute(any(CostumerOrderRequest.class)))
        .thenThrow(new RuntimeException("Something Fails"));

    // then
    given()
        .contentType("application/json")
        .body(Json.encodePrettily(costumerOrderRequest))
        .when()
        .post("/v1/order")
        .then()
        .statusCode(500);
  }

}
