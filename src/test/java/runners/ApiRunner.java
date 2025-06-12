package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

// This class is a placeholder for API test runner.
// It can be used to configure API test execution settings in the future.
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/", // Path to API feature files
        glue = "ApiSteps", // Package containing API step definitions
        dryRun = false, // Set to true to check for missing step definitions without executing tests
        tags = "@jsonpayload", // Tag to filter which tests to run
        plugin = {"pretty", "html:target/cucumber-api.html", "json:target/cucumber-api.json"} // Reporting options
)

public class ApiRunner {

}