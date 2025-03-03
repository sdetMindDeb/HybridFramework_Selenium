package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public void onStart(ITestContext testContext) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date dt = new Date();
        String currentDateTimeStamp = df.format(dt);

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        sparkReporter.config().setDocumentTitle("OpenCart Automation report");
        sparkReporter.config().setReportName("OpenCart FunctionalTesting");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.dir"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getTestName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + "got successfully executed");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getTestName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getName() + "got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String impPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(impPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getTestName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + "got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {
        extent.flush();

        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //To email the report

    /*try{
        URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);

        //create email
        ImageHtmlEmail email=new ImageHtmlEmail();
        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        email.setHostName("smtp.googleemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("Test@gmail","password"));
        email.setSSLOnConnect(true);
        email.setFrom("DebdipTest@gmail.com");//sender
        email.setSubject("Test exceution report");
        email.setMsg("Please find attached message");
        email.addTo("manager@test.com");//rceiver
        email.attach(url,"extentreport","please check report");
        email.send();
    }
    catch(Exception e){
        e.printStackTrace();
    }*/


}
