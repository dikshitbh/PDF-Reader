package utilities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PDFReader {
	
	public void pdfReader(String url) throws IOException {		
		URL pdfURL = new URL(url);
		InputStream ip = pdfURL.openStream();
		BufferedInputStream bf = new BufferedInputStream(ip);
		PDDocument pdDocument =  PDDocument.load(bf);
		int pageCount = pdDocument.getNumberOfPages();
		
		System.out.println(pageCount);
		Assert.assertEquals(pageCount, 2);
			
		
		//PDF text:content
		PDFTextStripper pdfTextStrip = new PDFTextStripper();
		String pdtext =  pdfTextStrip.getText(pdDocument);
		
		
		//Validate specific text in PDf
		Assert.assertTrue(pdtext.contains("Simple PDF File 2 "));	
		
		
	}
	

	
	
	
}
