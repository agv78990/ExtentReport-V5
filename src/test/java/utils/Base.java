package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class Base {


    public static ExtentReports extent;
    public static ExtentSparkReporter spark;


    @BeforeSuite
    public static void setExtent() {
        // specify location of the report
        //htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myReport.html");
        ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");


        extent = new ExtentReports();
        extent.attachReporter(spark);

        spark.config().setDocumentTitle("Automation Report"); // Tile of report
        spark.config().setReportName("Functional Testing"); // Name of the report
        spark.config().setTheme(Theme.DARK);


        // Passing General information
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("operating system", System.getProperty("os.name"));
        extent.setSystemInfo("OS version", System.getProperty("os.version"));
        extent.setSystemInfo("OS Arch Model", System.getProperty("os.arch"));
        extent.setSystemInfo("Environemnt", "QA");
        extent.setSystemInfo("user", "huma");

        extent.setSystemInfo("JAVA VERSION", System.getProperty("java.runtime.version"));
    }


    @AfterSuite
    public void tearDown() {


        extent.flush();
    }
}

