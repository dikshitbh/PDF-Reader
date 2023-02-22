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

public class pdfReaderTest extends PDFReader  {
	
	WebDriver driver;
	String url = "https://www.africau.edu/images/default/sample.pdf";
	String urlSameBrowser = "https://www.inkit.com/blog/pdf-the-best-digital-document-management";
	String urlNewTab = "https://www.hdfcbank.com/personal/resources/rates";
	
	
	PDFReader pd = new PDFReader();
	pageObjects.pdfReader p = new pageObjects.pdfReader();
	
	@SuppressWarnings("deprecation")
	@BeforeTest
	public void setup(){
		EdgeOptions eo = new EdgeOptions();
		eo.setHeadless(false);
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver(eo);
		driver.manage().window().maximize();
	}
	
	@Test(enabled = false)
	public void Reader() throws IOException {
		driver.get(url);
		pd.pdfReader(url,"Simple PDF File 2");		
	}
	
	@Test(enabled = false)
	public void ReaderSameBrowserTab() throws IOException {
		driver.get(urlSameBrowser);
		pd.readPDFInSameBrowser(driver,p.pdfLink);		
	}
	
	@Test(enabled = true)
	public void ReaderNewBrowserTab() throws IOException, InterruptedException {
		driver.get(urlNewTab);
		pd.readPDFInNewBrowser(driver,p.newTabLink);		
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
