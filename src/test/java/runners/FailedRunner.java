package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // Path to the rerun file containing failed scenarios
        features = "@target/failed.txt",

        // Package where step definitions (glue code) are located
        glue = "steps",

        // If set to true, checks for undefined step definitions without running tests
        //dryRun = false,

        // Tag to filter which scenarios to run
        tags = "@addLanguageProficiency",

        // Reporting plugins
        plugin = {
                "pretty",
                "html:target/cucumber.html",
                "rerun:target/failed.txt",
                "json:target/cucumber.json"
        }
)
public class FailedRunner {
}