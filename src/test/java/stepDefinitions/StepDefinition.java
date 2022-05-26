package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.addPlace;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class StepDefinition extends Utils {

    RequestSpecification req;
    RequestSpecification req1;
    ResponseSpecification res;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;

    @Given("Add Place payload with {string} {string}  {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        req1 = given().spec(requestSpecification())
                .body(data.addPlacePayload(name,language,address));

    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        // Write code here that turns the phrase above into concrete actions
        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());
        res = new ResponseSpecBuilder()
                .expectStatusCode(200).expectContentType(ContentType.JSON)
                .build();

        if(method.equalsIgnoreCase("POST"))
            response = req1.when().post(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response = req1.when().get(resourceAPI.getResource());
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
     assertEquals(response.getStatusCode(),200);

    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String ExpectedValue) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(getJsonPath(response,keyValue),ExpectedValue);
    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
      //request spec
        place_id = getJsonPath(response,"place_id");
        req1 = given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_http_request(resource,"GET");
        String actualName = getJsonPath(response,"name");
        assertEquals(actualName,expectedName);
    }

    @Given("DeletePlace payload")
    public void delete_place_payload() throws IOException {
    req1 = given().spec(requestSpecification())
            .body(data.deletePlacePayload(place_id));
    }
}
