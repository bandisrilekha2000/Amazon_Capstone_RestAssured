package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    public static ExtentReports getExtentReports() {
        if (extentReports == null) {
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent-report.html");
            extentReports = new ExtentReports();
            extentReports.attachReporter(htmlReporter);
        }
        return extentReports;
    }

    public static ExtentTest createTest(String testName) {
        extentTest = getExtentReports().createTest(testName);
        return extentTest;
    }

    public static void flush() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    public static ExtentTest getExtentTest() {
        return extentTest;
    }

}
