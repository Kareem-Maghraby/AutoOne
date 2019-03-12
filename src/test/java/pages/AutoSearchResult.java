package pages;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AutoSearchResult {

	By priceList = By.xpath("(//div[@data-qa-selector='price'])");

	By regDate = By.xpath("(//div[@class='item___T1IPF'])");

	String datePattern="([0-9]{2})/([0-9]{4})";

	//Retrieve all prices and store them in a list and return this list to be used for assertion
	public List<String> getPrices(WebDriver driver){

		List<String> listOfPrices = new ArrayList<>();

		List<WebElement> pricesWebElements = driver.findElements(priceList);


		for (WebElement priceElement : pricesWebElements) {

			listOfPrices.add(priceElement.getText());

		}

		return listOfPrices;
	}

	//Retrieve all registeration date and store them in a list to be used in assertion
	public List<Integer> getRegisterDate(WebDriver driver){

		List<WebElement> regDateElements = driver.findElements(regDate);
		Pattern pattern = Pattern.compile(datePattern);

		List<Integer> regDateList= new ArrayList<>();

		for(WebElement years : regDateElements) {
			String[] yearText = years.getText().split("\\n");
			if(yearText != null) {
				for(String item: yearText) {
					if(pattern.matcher(item).matches()) {
						regDateList.add(Integer.valueOf(item.split("/")[1]));
					}
				}
			}
		}

		return regDateList;
	}

}
