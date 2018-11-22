package fireStonePages;

	import java.util.ArrayList;
	import java.util.List;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.PageFactory;
	import testBase.Firestone;

public class HomePage extends Firestone{
	String heroStatus;
	String headerStatus;
	String contentStatus;
	String status1;
	String status2;
	String status3;
	String header;
	String data=null;
	String headerData = null;
	String para =null;
	String pagesource_h1=null;
	 public HomePage() {
		PageFactory.initElements(driver,this);
	}

	public void Firestone() throws Exception{
		
		String h1 = driver.findElement(By.tagName("h1")).getText().toLowerCase();
		System.out.println(h1);
		String h2 = driver.findElement(By.cssSelector("#main > div > div:nth-child(3) > section > div > div:nth-child(1) > h2")).getText().toLowerCase();
		System.out.println(h2);
		String paragraph = driver.findElement(By.cssSelector("#main > div > div:nth-child(3) > section > div > div:nth-child(1) > p:nth-child(2)")).getText().toLowerCase();
		System.out.println(paragraph);
		String pagesource_home = driver.getPageSource().toLowerCase();
		System.out.println(pagesource_home);
	
		if (pagesource_home.replaceAll("&amp;", "&").replaceAll("&#39;", "'").contains(h1)){
			status1 = "Matched";
			
		}else {
			
			status1= "Not Matched";
		}
		
		if (pagesource_home.replaceAll("&amp;", "&").replaceAll("&#39;", "'").contains(h2)){
			status2 = "Matched";
			
		}else {
			
			status2= "Not Matched";
		}
		
		if ( pagesource_home.replaceAll("&amp;", "&").replaceAll("&#39;", "'").contains(paragraph)){
			status3 = "Matched";
			
		}else {
			
			status3= "Not Matched";
		}
		// Write in excel
		List<WebElement> allElements = driver.findElements(By.xpath("//ul[@class='blocklinks']//a"));
		int counter=0;
		ArrayList<String> vehicle = new ArrayList<String>();
		ArrayList<String> mainCategory = new ArrayList<String>();
		ArrayList<String> subCategory = new ArrayList<String>();
		ArrayList<String> types = new ArrayList<String>();
		for (WebElement element : allElements) {
			header = element.getText();
			System.out.println(element.getText());
			vehicle.add(element.getAttribute("href"));
			System.out.println(element.getAttribute("href"));
		}
		
		for (String lists : vehicle) {
			counter++;
			if (counter>=2)
				break;
			driver.navigate().to(lists);
			
			List<WebElement> allElementss = driver.findElements(By.xpath("//ul[@class='blocklinks']//a"));
			for (WebElement string : allElementss) {
				mainCategory.add(string.getAttribute("href"));
			}
			String UrlData1 = driver.getCurrentUrl();
			 writetest(UrlData1,status1,status2,status3); 
			for (String lists2 : mainCategory) {
				driver.navigate().to(lists2);  //cl
				 data = driver.findElement(By.cssSelector("#main > div > div > section > div > div > div > h1"))
						.getText();
				 headerData = driver.findElement(By.cssSelector("#main > div > div > section > div > div > div > h2"))
						.getText();
				 para = driver.findElement(By.cssSelector("#main > div > div > section > div > div > div > p"))
						.getText();
				 String UrlData2 = driver.getCurrentUrl();
				 pagesource_h1 = driver.getPageSource().replaceAll("&amp;", "&").replaceAll("&#39;", "'");
				 dataVerify(pagesource_h1,data,headerData,para,UrlData2);
				
				List<WebElement> allElementss1 = driver.findElements(By.xpath("//ul[@class='blocklinks']//a"));
				for (WebElement string1 : allElementss1) {
					subCategory.add(string1.getAttribute("href"));
				}
				
				for (String lists3 : subCategory) {
					driver.navigate().to(lists3); //2003
					
					 data = driver.findElement(By.cssSelector("#main > div > div > section > div > div > div > h1"))
							.getText();
					 headerData = driver.findElement(By.cssSelector("#main > div > div > section > div > div > div > h2"))
							.getText();
					 para = driver.findElement(By.cssSelector("#main > div > div > section > div > div > div > p"))
							.getText();
					 String UrlData3 = driver.getCurrentUrl();
					 pagesource_h1 = driver.getPageSource().replaceAll("&amp;", "&").replaceAll("&#39;", "'");
					 dataVerify(pagesource_h1,data,headerData,para,UrlData3);
					 
					List<WebElement> allElementss2 = driver.findElements(By.xpath("//ul[@class='blocklinks']//a"));
					for (WebElement string2 : allElementss2) {
						types.add(string2.getAttribute("href"));
					}
					
					for (String lists4 : types) {
						driver.navigate().to(lists4); //type-s
						 headerData = driver.findElement(By.cssSelector("#product_results > div > div.seo-footer-content-container > div > div > h3"))
								.getText();
						 para = driver.findElement(By.cssSelector("#product_results > div > div.seo-footer-content-container > div > div > p"))
								.getText();
						 String UrlData4 = driver.getCurrentUrl();
						 pagesource_h1 = driver.getPageSource().replaceAll("&amp;", "&").replaceAll("&#39;", "'");
						 dataVerify(pagesource_h1,data,headerData,para,UrlData4);
					}
					types.clear();
				}	
				subCategory.clear();
			}
			mainCategory.clear();
		}
	}
}