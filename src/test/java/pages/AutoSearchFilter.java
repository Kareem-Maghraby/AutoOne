package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AutoSearchFilter {

	WebDriver driver= null;

	String regYearFrom = "2015";

	String autoPriceFilter = "Höchster Preis";

	By firstRegisterDate = By.xpath("(//span[@class='labelText___1_7Q2'])[3]");

	By firstRegisterDateDropDown = By.xpath("(//select[@name='yearRange.min'])");

	By sortPrice = By.xpath("(//select[@name='sort'])");



	public AutoSearchFilter(WebDriver driver) {

		this.driver = driver;
	}

	//Click one the registeration date From menu to open it

	public void openRegisterationDateFrom() {

		driver.findElement(firstRegisterDate).click();

	}

	//Click and Select year from drop down list

	public void selectRegisterationDateYear() {

		Select registerYear = new Select (driver.findElement(firstRegisterDateDropDown));

		registerYear.selectByVisibleText(regYearFrom);

	}

	//Click on Sort By drop down list and select Highest Price

	public void selectAutoPriceFilter() {

		Select priceFilter= new Select(driver.findElement(sortPrice));

		priceFilter.selectByVisibleText(autoPriceFilter);
	}

}
