package utilities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Time;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.github.dockerjava.api.model.Driver;

public class PDFReader {
	
	public void pdfReader(String url,String textToValidate) throws IOException {		
		URL pdfURL = new URL(url);
		InputStream ip = pdfURL.openStream();
		BufferedInputStream bf = new BufferedInputStream(ip);
		PDDocument pdDocument =  PDDocument.load(bf);
		
		//PDF text:content
		PDFTextStripper pdfTextStrip = new PDFTextStripper();
		String pdtext =  pdfTextStrip.getText(pdDocument);
		
		//Validate specific text in PDf
		Assert.assertTrue(pdtext.contains(textToValidate));	
				
	}
	
	public void readPDFInSameBrowser(WebDriver driver, By xpath) throws IOException {
		
		driver.findElement(xpath).click();
		String url = driver.getCurrentUrl();
		System.out.println(url);
		URL pdUrl = new URL(url);
		
		URLConnection urlConnection = pdUrl.openConnection();
		urlConnection.addRequestProperty("User-Agent", "Mozilla");
		InputStream ip = urlConnection.getInputStream();
		BufferedInputStream bf = new BufferedInputStream(ip);
		PDDocument pddoc = PDDocument.load(bf);		
		int count=pddoc.getNumberOfPages();
		Assert.assertEquals(count, 43);
		
	}
	
	
public void readPDFInNewBrowser(WebDriver driver, By xpath) throws IOException, InterruptedException {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
		driver.findElement(xpath).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@title='/personal/resources/interest-rates-for-5-cr-and-above'])[1]")));
		driver.findElement(By.xpath("(//a[@title='/personal/resources/interest-rates-for-5-cr-and-above'])[1]")).click();
//		Thread.sleep(3000);
		
		Set<String> windowHandles = driver.getWindowHandles();
		
		Iterator<String> handles = windowHandles.iterator();
		String PWID = handles.next();
		String CWID = handles.next();
		
		driver.switchTo().window(CWID);
		
		String url = driver.getCurrentUrl();
		
		System.out.println(url);
		
//		int count=pddoc.getNumberOfPages();
//		Assert.assertEquals(count, 43);
		
	}
	

	
	
	
}
