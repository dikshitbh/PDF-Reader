package test.selenium;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.PDFReader;

public class pdfReader extends PDFReader {
	
	WebDriver driver;
	String url = "https://www.africau.edu/images/default/sample.pdf";
	String urlSameBrowser = "https://www.inkit.com/blog/pdf-the-best-digital-document-management";
	By pdfLink = By.xpath("//a[normalize-space()='trillions of PDFs']");
	PDFReader pd = new PDFReader();
	
	
	@SuppressWarnings("deprecation")
	@BeforeTest
	public void setup(){
		EdgeOptions eo = new EdgeOptions();
		eo.setHeadless(false);
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver(eo);
		driver.manage().window().maximize();
	}
	
	@Test(enabled = true)
	public void Reader() throws IOException {
		driver.get(url);
		pd.pdfReader(url,"Simple PDF File 2");		
	}
	
	@Test
	public void ReaderSameBrowserTab() throws IOException {
		driver.get(urlSameBrowser);
		pd.readPDFInSameBrowser(driver,pdfLink);		
	}
	
	@Test(enabled = false)
	public void ReaderNewBrowserTab() throws IOException {
//		pd.readPDFInSameBrowser(driver,"trillions of PDFs");		
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
