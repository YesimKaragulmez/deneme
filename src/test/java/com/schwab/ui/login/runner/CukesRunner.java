package com.schwab.ui.login.runner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "json:target/cucumber.json",
        features = "src/test/resources/features",
        glue = "com/schwab/ui/login/step_def",
        dryRun = false,
        tags = "@login"
)
public class CukesRunner {

}
