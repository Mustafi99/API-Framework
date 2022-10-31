package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

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
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address,String website) throws IOException{
		res = given().spec(requestSpecification())
				.body(data.AddPlacePayLoad(name, language, address,website));	
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String HttpMethod) {

		APIResources resourceAPI =APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if(HttpMethod.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());

		else if(HttpMethod.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());

	}

	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		assertEquals(getJsonPath(response, key),value);

	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedname, String resource) throws IOException {
		//Prepare request Specification
		place_id =getJsonPath(response,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id",place_id);
		//getAPI call
		user_calls_with_http_request(resource, "GET");
		String actualname =getJsonPath(response,"name");
		assertEquals(actualname,expectedname);

	}
	@Given("DeletePlace payload")
	public void deleteplace_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	  res = given().spec(requestSpecification())
	   .body(data.deletePlacePayload(place_id));
	}



}
