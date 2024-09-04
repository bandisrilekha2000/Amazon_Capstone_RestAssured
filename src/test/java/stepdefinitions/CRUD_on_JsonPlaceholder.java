package stepdefinitions;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.ExtentReportManager;

public class CRUD_on_JsonPlaceholder {
    Response res;
    
    @BeforeAll
    public static void beforeAll() {
    	ExtentReportManager.getExtentReports();
    }

    @Before
    public void setup(Scenario scenario) {
        ExtentReportManager.createTest(scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentReportManager.getExtentTest().fail("Test failed: " + scenario.getName());
        } else {
            ExtentReportManager.getExtentTest().pass("Test passed: " + scenario.getName());
        }
        ExtentReportManager.flush();
    }

    @Given("I have a base URI of {string}")
    public void i_have_a_base_uri_of(String URL) {
        baseURI = URL;
        ExtentReportManager.getExtentTest().info("Base URI set to: " + URL);
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endPoint) {
        ExtentReportManager.getExtentTest().info("Sending GET request to endpoint: " + endPoint);
        res = given()
                .log().all()
                .when()
                .get(endPoint);
        ExtentReportManager.getExtentTest().info("Response received: " + res.asString());
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer stsCode) {
        ExtentReportManager.getExtentTest().info("Verifying response status code");
        res.then()
            .log().all()
            .statusCode(stsCode);
        ExtentReportManager.getExtentTest().info("Expected status code: " + stsCode + ", Actual: " + res.getStatusCode());
    }

    @When("I send a POST request to {string} with body as {string}")
    public void i_send_a_post_request_to_with_body_as(String endPoint, String RequestBody) {
        ExtentReportManager.getExtentTest().info("Sending POST request to endpoint: " + endPoint + " with body: " + RequestBody);
        res = given()
                .body(RequestBody)
                .when()
                .post(endPoint);
        ExtentReportManager.getExtentTest().info("Response received: " + res.asString());
    }

    @Then("the response time should be {long}")
    public void the_response_time_should_be(Long millSec) {
        ExtentReportManager.getExtentTest().info("Verifying response time");
        res.then()
           .time(lessThan(millSec));
        ExtentReportManager.getExtentTest().info("Expected response time less than: " + millSec + "ms");
    }

    @When("I send a PUT request to {string} with body as {string}")
    public void i_send_a_put_request_to_with_body_as(String endPoint, String RequestBody) {
        ExtentReportManager.getExtentTest().info("Sending PUT request to endpoint: " + endPoint + " with body: " + RequestBody);
        res = given()
                .body(RequestBody)
                .when()
                .put(endPoint);
        ExtentReportManager.getExtentTest().info("Response received: " + res.asString());
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endPoint) {
        ExtentReportManager.getExtentTest().info("Sending DELETE request to endpoint: " + endPoint);
        res = given()
                .when()
                .delete(endPoint);
        ExtentReportManager.getExtentTest().info("Response received: " + res.asString());
    }
}
