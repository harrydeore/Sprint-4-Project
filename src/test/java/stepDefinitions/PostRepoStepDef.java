package stepDefinitions;

import genericLibraries.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.cucumber.java.en.*;

public class PostRepoStepDef implements AutoConstant {
    Response response;
    DataUtilities data = new DataUtilities();

    @When("user sends POST request to create a new repository")
    public void sendPostRequest() {
        PojoClass repo = new PojoClass(
            data.getDataFromProperty("reponame"),
            data.getDataFromProperty("repodescription")
        );

        response = given()
            .log().all()
            .header("Authorization", "Bearer " + data.getDataFromProperty("token"))
            .header("Content-Type", "application/json")
            .body(repo)
        .when()
            .post(postOperation)
        .then()
            .log().all()
            .extract().response();
    }

    @Then("repository is created successfully")
    public void validatePostRepo() {
        response.then().statusCode(201);
    }
}
