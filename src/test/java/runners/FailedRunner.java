package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //path to all the feature file
        //features ="src/test/resources/feature/Login.feature
        //features= "srs/test/resources/features/EmployeeSearch.feature
        features = "@target/failed.txt",
        //name of the package where all the step definitions available
        glue = "steps",
        //if we set the value to true, it stops execution and scan all the step definitions and gives missing steps
        //dryRun =false,
//        tags = "@chikhi or @godwill",// one of the conditions must be true then it will execute
//      tags ="@chikhi and @godwill"// both the conditions must be true then it will execute
        //tags = "@invalid",
        plugin = {"pretty"}

)


public class FailedRunner {
}
