package com.example.serenity.apitesting.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/apiTesting",
        glue = "com.example.serenity.apitesting.tests"  // Path to your step definitions
)
public class RunCucumberTest {
}