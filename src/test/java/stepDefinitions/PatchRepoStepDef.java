package stepDefinitions;

import genericLibraries.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.cucumber.java.en.*;

public class PatchRepoStepDef implements AutoConstant {
    Response response;
    DataUtilities data = new DataUtilities();

    @When("user sends PATCH request to update the description")
    public void sendPatchRequest() {
        PojoClass repo = new PojoClass(
            data.getDataFromProperty("reponame"),
            data.getDataFromProperty("updatedrepodescription")
        );

        response = given()
            .log().all()
            .pathParam("owner", data.getDataFromProperty("owner"))
            .pathParam("repo", data.getDataFromProperty("reponame"))
            .header("Authorization", "Bearer " + data.getDataFromProperty("token"))
            .header("Content-Type", "application/json")
            .body(repo)
        .when()
            .patch(patchOperation)
        .then()
            .log().all()
            .extract().response();
    }

    @Then("repository is updated successfully")
    public void validatePatchRepo() {
        response.then().statusCode(200);
    }
}
