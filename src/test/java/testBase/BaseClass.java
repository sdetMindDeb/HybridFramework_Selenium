package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties pr;

    @BeforeClass(groups = {"Sanity", "Master", "Regression", "DataDriven"})
    @Parameters({"OS", "browser"})
    public void setup(String os, String br) throws InterruptedException, IOException {

        logger = LogManager.getLogger(this.getClass());

        //Load Config property

        FileReader file = new FileReader(System.getProperty("user.dir") + "./src\\test\\resources\\config.properties");
        pr = new Properties();
        pr.load(file);

        //If the execution is done on remote,below code is required
        if (pr.getProperty("execution_env").equals("remote")) {
            DesiredCapabilities cap = new DesiredCapabilities();
            if (os.equalsIgnoreCase("Windows")) {
                cap.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("Mac")) {
                cap.setPlatform(Platform.MAC);
            } else {
                System.out.println("Invalid OS");
                return;
            }

            //Browser
            switch (br.toLowerCase()) {
                case "chrome":
                    cap.setBrowserName("chrome");
                    break;
                case "edge":
                    cap.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    System.out.println("Invalid browser");
                    return;
            }

            driver=new RemoteWebDriver(new URL("pass the selenium grid url here"),cap);
        }



        switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Invalid browser");
                return;
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(pr.getProperty("url"));
        driver.manage().window().maximize();
        Thread.sleep(10000);
    }

    @AfterClass(groups = {"Sanity", "Master", "Regression", "DataDriven"})
    public void tearDown() {
        driver.quit();
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphanumeric(8) + "@";
    }

    public String randomNumeric() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String captureScreen(String tname) throws IOException {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourcefile = ts.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshot" + tname + "_" + timestamp;
        File targetFile = new File(targetFilePath);

        sourcefile.renameTo(targetFile);
        return targetFilePath;
    }
}
