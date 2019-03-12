package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;


import pages.AutoSearchFilter;
import pages.AutoSearchResult;

public class AutoSearchTest {

	private static WebDriver driver = null;

	static int regYear=2015;

	static String pageUrl = "https://www.autohero.com/de/search/";

	static By waitPriceElement = By.xpath("(//div[@data-qa-selector='price'])");


	//Test Preparation (Assign driver and navigate to Auto Hero URL)
	@BeforeTest
	public static void prepTest() {

		String projPath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", projPath+"/drivers/geckodriver/geckodriver.exe");//can be removed by setting the path in env variables

		driver = new FirefoxDriver();
		driver.navigate().to(pageUrl);
	}

	//Execute Test Step and Assert on prices are in descending order
	@Test
	public static void autoSearchTestExec () {


		AutoSearchFilter searchFilter = new AutoSearchFilter(driver);

		WebDriverWait wait = new WebDriverWait(driver, 3);
		WebElement waitElement = wait.until(ExpectedConditions.presenceOfElementLocated(waitPriceElement));

		//Execute Test Steps
		searchFilter.openRegisterationDateFrom();

		searchFilter.selectRegisterationDateYear();

		searchFilter.selectAutoPriceFilter();

		wait.until(ExpectedConditions.stalenessOf(waitElement));

		AutoSearchResult autoSearchResult = new AutoSearchResult();

		List<String> prices = autoSearchResult.getPrices(driver);

		//Adding the actual prices list to another List to be sorted

		List<String> SortedPrices = new ArrayList<>(prices);

		//Sorting the new list in descending orders
		Collections.sort(SortedPrices, Collections.reverseOrder());

		//Assert the actual list with the expected list
		assertThat(prices).isEqualTo(SortedPrices);


	}

	//Assert that the search result contains cars with registeraion date 2015+
	@Test
	public static void getYearAssert() {

		AutoSearchResult autoYearAssert = new AutoSearchResult();

		List<Integer> getYears=	autoYearAssert.getRegisterDate(driver);

		for(int year : getYears) {

			assertThat(year).isGreaterThanOrEqualTo(regYear);
		}

	}


	@AfterTest
	public static void endTest() {

		driver.quit();
	}


}
