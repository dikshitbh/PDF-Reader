package utilities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PDFReader {
	WebDriver driver;
	String url = "https://www.africau.edu/images/default/sample.pdf";
	
	
	@BeforeTest
	public void setup(){	
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get(url);		
	}
	
	@Test
	public void pdfReader() throws IOException {		
		URL pdfURL = new URL(url);
		InputStream ip = pdfURL.openStream();
		BufferedInputStream bf = new BufferedInputStream(ip);
		PDDocument pdDocument =  PDDocument.load(bf);
		int pageCount = pdDocument.getNumberOfPages();
		
		System.out.println(pageCount);
		Assert.assertEquals(pageCount, 2);
		
		System.out.println(pdDocument.getDocumentId());
		
		
		//PDF text:content
		PDFTextStripper pdfTextStrip = new PDFTextStripper();
		System.out.println(pdfTextStrip.getText(pdDocument));
		
		
		
		
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
	
}
