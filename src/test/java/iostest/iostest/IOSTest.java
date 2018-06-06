package iostest.iostest;

import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.experitest.appium.SeeTestClient;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class IOSTest  {

	private String testName = "Jenkins Demo iOS Phone";
    protected IOSDriver<IOSElement> driver = null;
    protected DesiredCapabilities dc = new DesiredCapabilities();
    private SeeTestClient client;

	@Before
	public void setUp() throws Exception{
        dc.setCapability("testName", testName);
		dc.setCapability("deviceQuery", System.getenv("deviceQuery"));
        //dc.setCapability("deviceQuery", "@os='ios'");
		dc.setCapability("reportDirectory", "reports");
		dc.setCapability("reportFormat", "xml");
		dc.setCapability("stream", "jenkins_ios_phone");
		dc.setCapability("build.number", System.getenv("BUILD_NUMBER"));
		dc.setCapability("accessKey", System.getenv("accessKey")); 
		//dc.setCapability("accessKey", "eyJ4cC51IjoxLCJ4cC5wIjoyLCJ4cC5tIjoiTVRVeE5UVTNNekEwT1RJNU1nIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4MzMyNTc2NTIsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.dlomjSQ3wkmV3uovu5fJtS5-Z7-vnyk7Sw9AOmmE4aA");
        driver = new IOSDriver<IOSElement>(new URL("http://jaharon-lt.experitest.local:9192/wd/hub"), dc);
        client = new SeeTestClient(driver);
	}

	@Test
	public void test(){
		client.install("cloud:uniqueName=testEribankIOS_" + System.getenv("BUILD_NUMBER"), true, false);
		client.launch("cloud:com.experitest.ExperiBank", true, true);

		driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='loginButton']")).click();
	}
	
	@After
	public void tearDown(){
		driver.quit();
	}
}

