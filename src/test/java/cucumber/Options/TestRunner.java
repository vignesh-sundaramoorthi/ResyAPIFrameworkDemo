package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",
plugin = {"pretty","json:target/jsonReports/cucumber-report.json","html:target/cucumber-html-reports/cucumber-report.html"},
glue = {"stepDefinitions"})

public class TestRunner {
    //, tags= "@AddPlace"
}
