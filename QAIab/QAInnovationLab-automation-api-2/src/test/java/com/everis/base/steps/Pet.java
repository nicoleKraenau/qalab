package com.everis.base.steps;

import com.everis.base.models.Pet;
import com.google.gson.JsonObject;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.*;
import net.thucydides.core.annotations.Step;
import org.hamcrest.CoreMatchers;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreService {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PetStoreService.class);
    static private final String BASE_URL = "https://petstore.swagger.io/v2/";

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    private Response response;
    private RequestSpecBuilder builder;
    private RequestSpecification requestSpecification;
    private String bodyPost;

    @Before
    public void init() {

        LOGGER.info("Inicializa el constructor request");
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();

        LOGGER.info("Inicializa el constructor response");
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Step("obtiene lista de mascotas por status")
    public void listPetsByStatus(String status) {

        given().
                spec(requestSpec).
                queryParam("status", status).
                when().
                get("pet/findByStatus").
                then().
                spec(responseSpec).
                and();
    }

    @Step("obtiene una mascota")
    public void getPetById(long petId) {

        given()
                .spec(requestSpec)
                .pathParams("petId", petId).
                when()
                .get("pet/{petId}").
                then().
                and();
    }

    @Step("obtiene lista de mascotas con objeto Java")
    public void listPetObject(String status) {

        Pet pet =
                given().
                        spec(requestSpec).
                        queryParam("status", status).
                        when().
                        get("pet/findByStatus").
                        as(Pet.class);

        LOGGER.info("Realizo la consulta de Mascotas: ");
        //  List<Pet> pets = pet.getData();
        //  for(Pet p : pets) {
        System.out.println("Nombre: " + p.getName());
        //  }//
    }

    public void validateStatusCode(int i) {
        assertThat(lastResponse().statusCode(), is(i));
    }

    public void validateBodyContent(String var) {
        assertThat(lastResponse().getBody().path("name"), equalTo(var));
    }

    public void validarNombre(String nombre) {
        assertThat(lastResponse().getBody().path("name"), equalTo(nombre));
    }

    @Step("insertar una mascota")
    public void insertPet(String nombre, String status) {

        JsonObject parametros = new JsonObject();
        parametros.addProperty("name", nombre);
        parametros.addProperty("status", status);

        bodyPost = parametros.toString();

        builder.setBody(bodyPost);
    }

    @Step("inicializo los parámetros del request para POST")
    public void inicializoParametrosRequestPost() {
        RestAssured.baseURI = BASE_URL;
        builder = new RequestSpecBuilder();
        requestSpecification = builder.build();
    }

    @Step("set Header")
    public void setHeader(String k, String v) {
        builder.addHeader(k, v);
    }

    public void sendPostRequest(String api) {
        response = given().spec(requestSpecification).when().post(api);
    }

    @Step("verifica el contenido de la RESPUESTA")
    public void checkDataResponse(String k, String v) {
        assertThat(response.body().path(k), CoreMatchers.equalTo(v));
    }
}
