package Firestone;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import fireStonePages.HomePage;
import testBase.Firestone;

public class TestCases extends Firestone{
	
	HomePage homepage;
	@Test(dataProvider= "Firestone")
	
	 public void FireStone(String TestCaseName,String UserName,String Password, String Browser,String Url,String Results) throws Exception{
		fn_LaunchBrowser(Browser,Url);
		homepage =new HomePage(); 
		System.out.println("This is testcase " +TestCaseName);
		homepage.Firestone();
	}

	@AfterMethod
	public void quit() throws InterruptedException{
		driver.close();
		//driver.quit();
	}
	
	@AfterSuite
	public void cleanupSuite() {
		        System.out.println("testClass1.cleanupSuite: after suite");
		 
		    }
	
	@DataProvider(name="Firestone")
    public static Object[][] loginData(){
     
     Object[][] arrayObject=getExcelData("Firestone", "Firestone");
     return arrayObject;
     }	
}
