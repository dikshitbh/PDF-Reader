package utilities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
	
	
public void readPDFInNewBrowser(WebDriver driver, By xpath) throws IOException {
		
//		driver.findElement(xpath).click();
//		String url = driver.getCurrentUrl();
//		System.out.println(url);
//		URL pdUrl = new URL(url);
//		
//		URLConnection urlConnection = pdUrl.openConnection();
//		urlConnection.addRequestProperty("User-Agent", "Mozilla");
//		InputStream ip = urlConnection.getInputStream();
//		BufferedInputStream bf = new BufferedInputStream(ip);
//		PDDocument pddoc = PDDocument.load(bf);		
//		int count=pddoc.getNumberOfPages();
//		Assert.assertEquals(count, 43);
		
	}
	

	
	
	
}
