import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v125.fetch.Fetch;
import org.openqa.selenium.devtools.v125.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v125.network.model.ErrorReason;


public class NetworkFailedRequest {

	public static void main(String[] args) {
		

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Chromedriver\\chromedriver.exe");
		
		ChromeDriver driver = new ChromeDriver();
		//log file ->
		
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		//java.util.Optional<java.lang.String> urlPattern
	    Optional<List<RequestPattern>>	patterns = Optional.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"),Optional.empty(),Optional.empty())));
		
		devTools.send(Fetch.enable(patterns, Optional.empty()));
		
		devTools.addListener(Fetch.requestPaused(), request ->
		{
			
			devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
			
		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();	
		
	}

}
