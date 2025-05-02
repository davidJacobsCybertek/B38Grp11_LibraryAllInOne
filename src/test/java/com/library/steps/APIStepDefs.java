package com.library.steps;


import com.library.utility.LibraryAPI_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;

public class APIStepDefs {
    // 1st future file ==>Retrieve all users from the API endpoint

    RequestSpecification givenPart;
    Response response;
    ValidatableResponse thenPart;

    @Given("I logged Library api as a {string}")
    public void i_logged_library_api_as_a(String role) {

        givenPart = RestAssured.given().log().uri();
        givenPart.header("x-library-token", LibraryAPI_Util.getToken(role));

    }
    @Given("Accept header is {string}")
    public void accept_header_is(String acceptHeader) {
        givenPart.accept(acceptHeader);

    }
    @When("I send GET request to {string} endpoint")
    public void i_send_get_request_to_endpoint(String endpoint) {
        response = givenPart.when().get(endpoint);
        response.prettyPrint();

    }
    @Then("status code should be {int}")
    public void status_code_should_be(Integer expectedStatusCode) {
        thenPart = response.then();
        thenPart.statusCode(expectedStatusCode);

    }
    @Then("Response Content type is {string}")
    public void response_content_type_is(String expectedContentType) {
        thenPart.contentType(expectedContentType);


    }
    @Then("Each {string} field should not be null")
    public void each_field_should_not_be_null(String path) {
        thenPart.body(path, everyItem(notNullValue()));

    }



}
