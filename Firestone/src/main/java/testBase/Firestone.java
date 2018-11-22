package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.csvreader.CsvWriter;
import com.google.common.io.Files;
import junit.framework.Assert;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Firestone {
public static int totalNoofCols;
public static int totalNoofRows;
String heroStatus;
String headerStatus;
String contentStatus;

public static WebDriver driver;
@Test(dataProvider= "Firestone")


public static WebDriver fn_LaunchBrowser(String Browser,String URL) throws Exception{
	try { 
	if(Browser.equalsIgnoreCase("CH")){
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\swapnilband\\driver\\chromedriver.exe");
	driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
else if(Browser.equalsIgnoreCase("FF")){
	System.setProperty("webdriver.gecko.driver","C:\\Users\\swapnilband\\driver\\geckodriver.exe");
	driver= new FirefoxDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
 	   driver.navigate().to(URL);  
     } catch (Exception e) {  
    	e.printStackTrace();
       System.out.println("FAILURE: URL did not load: " + URL); 
       takeScreenshot();
       Assert.fail();
       throw new Exception("URL did not load");  
       
     } 
driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
return driver;
}

public void writetest(String d,String data,String headerData,String para) throws IOException{
	// String csv = "D:\\output.csv";
	  String csvOutputFile = "D://output.csv";
        //check if file exist
        boolean isFileExist = new File(csvOutputFile).exists();
            try {
                //create a FileWriter constructor to open a file in appending mode
                CsvWriter testcases = new CsvWriter(new FileWriter(csvOutputFile, true), ',');
                //write header column if the file did not already exist
                if(!isFileExist)
                {
                    testcases.write("Url");
                    testcases.write("Hero");
                    testcases.write("Header");
                    testcases.write("Content");
                    //end the record
                    testcases.endRecord();
                }
             //add record to the file
                testcases.write(d);
                testcases.write(data);
                testcases.write(headerData);
                testcases.write(para);
               
                //end the record
                testcases.endRecord();
                //close the file
                testcases.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }



public void dataVerify(String pagesource_h1,String data, String headerData, String para, String url) throws IOException{
	
	
	if (pagesource_h1.toLowerCase().replaceAll("&amp;", "&").replaceAll("&#39;", "'").contains("hero : " + data.toLowerCase())){
		heroStatus="Matched";
	}
	else{
		heroStatus="Not Matched";
	}
	
	if( pagesource_h1.toLowerCase().replaceAll("&amp;", "&").replaceAll("&#39;", "'").contains("header1 : " + headerData.toLowerCase())){
		headerStatus= "Matched";
		
	}else {
		headerStatus= "Not Matched";
	}
	
	if( pagesource_h1.toLowerCase().replaceAll("&amp;", "&").replaceAll("&#39;", "'").contains("content1 : " + para.toLowerCase())) {
		contentStatus= "Matched";
	} else {
		contentStatus= "Not Matched";
	}

	writetest(url,heroStatus,headerStatus,contentStatus); 
}

	// Read excel code.
	public static String[][] getExcelData(String fileName,String sheetName){
		String[][] arrayExcelData = null;
		
		try{
			FileInputStream fs=new FileInputStream("D://Firestone//Firestone.xls");
			
			Workbook wb=Workbook.getWorkbook(fs);
			Sheet sh=wb.getSheet(sheetName);
			
			
			System.out.println("Current col and row from the sheet: " +sheetName);
			totalNoofCols=sh.getColumns();
			totalNoofRows=sh.getRows();
			
			System.out.println("the current col abd row number: col "+totalNoofCols+ "Row:"+totalNoofRows);
			arrayExcelData= new String[totalNoofRows-1][totalNoofCols];
			
			for (int i = 1; i < totalNoofRows; i++) {
				for (int j = 0; j < totalNoofCols; j++) {
					arrayExcelData[i-1][j]=sh.getCell(j, i).getContents();
					System.out.println(sh.getCell(j, i).getContents());
				}
				
			}
			
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} 
		catch(IOException e){
			e.printStackTrace();
			e.printStackTrace();
		}
		catch(BiffException e){
			e.printStackTrace();
		}
		return arrayExcelData;
	}
	
public static void takeScreenshot() throws Exception {
    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    Files.copy(scrFile, new File("D://Screenshot//Failed_TC_.png"));
}


@DataProvider(name="Firestone")
public static Object[][] loginData(){
 
 Object[][] arrayObject=getExcelData("Firestone", "Firestone");
 return arrayObject;
 }	
}
	
	
	
	


