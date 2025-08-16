package stepDefinitions;

import genericLibraries.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.cucumber.java.en.*;

public class DeleteRepoStepDef implements AutoConstant {
    Response response;
    DataUtilities data = new DataUtilities();

    @When("user sends DELETE request to remove repository")
    public void sendDeleteRequest() {
        response = given()
            .log().all()
            .pathParam("owner", data.getDataFromProperty("owner"))
            .pathParam("repo", data.getDataFromProperty("reponame"))
            .header("Authorization", "Bearer " + data.getDataFromProperty("token"))
        .when()
            .delete(deleteOperation)
        .then()
            .log().all()
            .extract().response();
    }

    @Then("repository is deleted successfully")
    public void validateDeleteRepo() {
        response.then().statusCode(204);
    }
}
