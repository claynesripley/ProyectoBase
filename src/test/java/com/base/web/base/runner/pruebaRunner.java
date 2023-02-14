package com.base.web.base.runner;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.web.base.runner.base.WebBaseRunnerTest;

@CucumberOptions(
    features = "src/test/java/com/base/web/base/features/cotizador",
    glue = "com.base.web.base.stepdefinition",
    tags="@Somoketest",
    publish = true,
    plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
    monochrome = true
)

    public class pruebaRunner extends WebBaseRunnerTest {
        private TestNGCucumberRunner testNGCucumberRunner;
    
        @BeforeClass(alwaysRun = true)
        public void setUpClass() {
            testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        }
    
        @Test(groups = "cucumber", description = "Run Cucumber Features.", dataProvider = "scenarios")
        public void scenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
            testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
        }
    
        @DataProvider
        public Object[][] scenarios() {
            return testNGCucumberRunner.provideScenarios();
        }
    
        @AfterClass(alwaysRun = true)
        public void tearDownClass() {
            testNGCucumberRunner.finish();
        }
}
