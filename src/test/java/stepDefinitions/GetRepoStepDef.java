package stepDefinitions;

import genericLibraries.AutoConstant;
import genericLibraries.DataUtilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import io.cucumber.java.en.*;

public class GetRepoStepDef implements AutoConstant {
    Response response;
    DataUtilities data = new DataUtilities();

    @Given("user sets base URI")
    public void setBaseUri() {
        RestAssured.baseURI = "https://api.github.com";
    }

    @When("user sends GET request to retrieve repository")
    public void sendGetRequest() {
        response = given()
            .log().all()
            .pathParam("owner", data.getDataFromProperty("owner"))
            .pathParam("repo", data.getDataFromProperty("reporistory"))
            .header("Authorization", "Bearer " + data.getDataFromProperty("token"))
        .when()
            .get(getOperation)
        .then()
            .log().all()
            .extract().response();
    }

    @Then("repository details are returned successfully")
    public void validateGetRepo() {
        response.then().statusCode(200);
    }
}
