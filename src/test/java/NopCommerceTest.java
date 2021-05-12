
    import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

    import com.aventstack.extentreports.reporter.ExtentSparkReporter;
    import org.openqa.selenium.By;
    import org.openqa.selenium.OutputType;
    import org.openqa.selenium.TakesScreenshot;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.reporter.configuration.Theme;
    import org.testng.reporters.Files;

    public class NopCommerceTest {
        private static Files FileUtils;
        public WebDriver driver;
        public ExtentSparkReporter spark;
        public ExtentReports extent;
        public ExtentTest test;

        @BeforeTest
        public void setExtent() {
            // specify location of the report
            //htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myReport.html");
            ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");

            spark.config().setDocumentTitle("Automation Report"); // Tile of report
            spark.config().setReportName("Functional Testing"); // Name of the report
            spark.config().setTheme(Theme.DARK);


            extent = new ExtentReports();
            extent.attachReporter(spark);


            // Passing General information
            extent.setSystemInfo("Host name", "localhost");
            extent.setSystemInfo("Environemnt", "QA");
            extent.setSystemInfo("user", "huma");
        }

        @AfterTest
        public void endReport() {
            extent.flush();
        }

        @BeforeMethod
        public void setup() {


            System.setProperty("webdriver.chrome.driver", "C:\\Users\\avaho\\IdeaProjects\\I\\TechnicalSupport\\Drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://demo.nopcommerce.com/");
        }

        //Test1
        @Test
        public void noCommerceTitleTest() {
            test = extent.createTest("noCommerceTitleTest");
            String title = driver.getTitle();
            System.out.println(title);
            Assert.assertEquals(title, "eCommerce demo store");
        }

        //Test2
        @Test
        public void noCommerceLogoTest() {
            test = extent.createTest("noCommerceLogoTest");
            boolean b = driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).isDisplayed();
            Assert.assertTrue(b);
        }

        //Test3
        @Test
        public void noCommerceLoginTest() {
            test = extent.createTest("noCommerceLoginTest");

            test.createNode("Login with Valid input");
            Assert.assertTrue(true);

            test.createNode("Login with In-valid input");
            Assert.assertTrue(true);
            test.log(Status.PASS,"Launching chrome");

        }

        @AfterMethod
        public void tearDown(ITestResult result) throws IOException {
            if (result.getStatus() == ITestResult.FAILURE) {
                test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
                test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
                //String screenshotPath = NopCommerceTest.getScreenshot(driver, result.getName());
                String screenshotPath = NopCommerceTest.getScreenshot(driver, result.getName());
                test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
            }
            driver.quit();
        }

        public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            return dateName;


        }
    }

