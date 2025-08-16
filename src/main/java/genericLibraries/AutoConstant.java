package genericLibraries;

public interface AutoConstant {
    // API Endpoints
    String getOperation = "/repos/{owner}/{repo}";
    String postOperation = "/user/repos";
    String patchOperation = "/repos/{owner}/{repo}";
    String deleteOperation = "/repos/{owner}/{repo}";

    // Property file path
    String propertyFilePath = "src/test/resources/Data.properties";
}
