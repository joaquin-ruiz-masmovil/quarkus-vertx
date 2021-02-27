package com.masmovil.phoneapp.infrastructure.routerhandler;

import com.google.common.collect.Lists;
import com.masmovil.phoneapp.application.getphonecatalog.GetPhoneCatalogService;
import com.masmovil.phoneapp.application.getphonecatalog.PhoneCatalogInfo;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.reactivex.Single;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@QuarkusTest
public class RestPhoneCatalogRouterShould {

  @InjectMock
  private GetPhoneCatalogService getPhoneCatalogServiceMock;

  private RestPhoneCatalogRouter restPhoneCatalogRouter;

  @BeforeEach
  public void setUp() {
    restPhoneCatalogRouter = new RestPhoneCatalogRouter(getPhoneCatalogServiceMock);
  }


  @Test
  public void return200WithPhonesCatalog() {
    // given
    List<PhoneCatalogInfo> catalog = Lists.newArrayList(
        PhoneCatalogInfo.of("Nokia", "3030", "http:://image.jpg", 100),
        PhoneCatalogInfo.of("iPhone", "Version 12", "http:://image.png", 200)
    );
    when(getPhoneCatalogServiceMock.execute()).thenReturn(Single.just(catalog));

    // then
    given()
        .when()
        .get("/v1/phones")
        .then()
        .statusCode(200)
        .body(
            containsString("Nokia"),
            containsString("iPhone"));
  }

  @Test
  public void return500WhenFails() {
    // given
    when(getPhoneCatalogServiceMock.execute())
        .thenReturn(Single.error(new RuntimeException("Something Fails")));

    // then
    given()
        .when()
        .get("/v1/phones")
        .then()
        .statusCode(500)
        .body(equalTo("Something Fails"));
  }

  @Test
  public void return500WhenException() {
    // given
    when(getPhoneCatalogServiceMock.execute())
        .thenThrow(new RuntimeException("Something Fails"));

    // then
    given()
        .when()
        .get("/v1/phones")
        .then()
        .statusCode(500)
        .body(equalTo("Something Fails"));
  }

}
