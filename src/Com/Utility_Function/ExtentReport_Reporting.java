package Com.Utility_Function;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport_Reporting implements IReporter {
	private ExtentReports extent;
	public void buildTestNodes(IResultMap tests, LogStatus status) {

		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());

				/*
				  test.getTest(). = getTime(result.getStartMillis());
				  test.getTest().endedTime = getTime(result.getEndMillis());
				 */

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				String message = "Test " + status.toString().toLowerCase() + "ed";

				if (result.getThrowable() != null)
					message = result.getThrowable().getMessage();
	/*			String screens = System.getProperty("user.dir") + "/ScreenShots/";
				//System.Diagnostics.Stopwatch sw = new System.Diagnostics.Stopwatch();
			    File directory = new File(screens);
			    Image image = ImageIO.read(directory);*/
				//[-]*[0-9]*\\.(png|jpg|jpeg)
				String screens = System.getProperty("user.dir") + "/ScreenShots";
				File directory = new File(screens);
				//FileFilter f = new WildcardFileFilter(result.getName());
				File[] paths = directory.listFiles();
				for(int i=0; i<paths.length; i++){
					//System.out.println("sdncjsncjldsncdscljnds " + paths.length);
					//System.out.println(paths[i]);
					if(paths[i].toString().contains(result.getName())){
						//if (result.getThrowable() != null)
							//message = result.getThrowable().getMessage();
						//String path = System.getProperty("user.dir") + "/ScreenShots/"+result.getName()+".png";				
						String image = test.addScreenCapture(paths[i].toString());
						test.log(status, result.getMethod().getMethodName(), message + image);
						//test.log(status,"Screenshot for the step", image);						
					}
				}
			/*	String path = System.getProperty("user.dir") + "/ScreenShots/"+result.getName()+".png";				
				String image = test.addScreenCapture(path);
				test.log(status, result.getMethod().getMethodName(), image);
				test.log(status, message);*/
				//}
				extent.endTest(test);
			}
		}
	}

	public void generateReport(List<XmlSuite> arg0, List<ISuite> suites, String arg2) {
		extent = new ExtentReports("./Report/extent_report.html", true);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();
				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);

			}
		}

		extent.flush();
		extent.close();
	}

}
