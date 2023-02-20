package test.selenium;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.PDFReader;

public class pdfReader extends PDFReader {
	
	WebDriver driver;
	String url = "https://www.africau.edu/images/default/sample.pdf";
	PDFReader pd = new PDFReader();
	
	
	@BeforeTest
	public void setup(){	
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get(url);		
	}
	
	@Test
	public void PDFReader() throws IOException {
				pd.pdfReader(url);		
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
