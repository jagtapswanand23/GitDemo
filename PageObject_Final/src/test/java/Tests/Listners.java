package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.Base;
import resources.ExtentReporterNG;

import java.io.IOException;

public class Listners extends Base implements ITestListener   //ITestListner is an interface which invoke all the methods which TestNG provides
{
    ExtentReports extent = ExtentReporterNG.getReportObject();  // create object of extent keyword
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();  // to maintain thread safe for ExtentReports, so it should not overwrite

    public void onTestStart(ITestResult iTestResult) {

        test = extent.createTest(iTestResult.getMethod().getMethodName());  // every test have entry of extent report, and it will give method name of each method
        extentTest.set(test); // sending all new objects are sending all new test cases by tests to thread pool

    }

    public void onTestSuccess(ITestResult iTestResult) {
        extentTest.get().log(Status.PASS, "Test Passed");   // in report we know test is pass message
//extentest.get which will check thread local into pool of extenttest, and it will give tests object by writing get method and all that method you apply fail

    }

    public void onTestFailure(ITestResult iTestResult) {
        extentTest.get().fail(iTestResult.getThrowable());   // it will show log of fail test,
        WebDriver driver = null;  // assigning failed test case to this local webdriver
        String testMethodname = iTestResult.getMethod().getMethodName();  // it will give method name which gets failed
        try {
            driver = (WebDriver) iTestResult.getTestClass().getRealClass().getDeclaredField("driver").get(iTestResult.getInstance());  // to access any fileds of any class we write this code
        } catch (Exception e) {

        }
        // above code we accessing the driver field from failed test cases
        try {
            extentTest.get().addScreenCaptureFromPath(getscreenshot(testMethodname,driver), iTestResult.getMethod().getMethodName());
            //getscreenshot(testMethodname, driver);  this will give the test method name
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {
        extent.flush();

    }
}
