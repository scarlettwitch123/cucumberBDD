package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.AfterClass;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        plugin = {"pretty", "json:target/cucumber-report.json"}
)
public class TestRunner {
    private static final Logger logger = LoggerFactory.getLogger(TestRunner.class);

    static {
        logger.info("Starting Cucumber Tests...");
    }

    @AfterClass
    public static void generateReport() {
        // Directory for the report
        File reportOutputDirectory = new File("target/cucumber-reports");

        // List of JSON files to include in the report
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-report.json");

        // Project name
        String projectName = "Github - Test Automation";

        // Create configuration
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setBuildNumber("1.0.0"); // Optional: Add a build number
        configuration.addClassifications("Environment", "QA"); // Optional: Add classifications

        try {
            // Generate the report
            ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
            reportBuilder.generateReports();
            logger.info("Report generated successfully.");
        } catch (Exception e) {
            logger.error("Error generating report: ", e);
        }
    }
}
