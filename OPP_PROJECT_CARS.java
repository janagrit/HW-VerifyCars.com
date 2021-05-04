package April_26;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OPP_PROJECT_CARS {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/1 selenium-java-3.141.59/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.carfax.com/");

        // 2. Click on Find a Used Car
        driver.findElement(By.xpath("//a[@class='button button--green']")).click();

        //3.  Verify the page title contains the word “Used Cars”
        String actualTitle = driver.getTitle();
        String expectedTitle = "Used Cars";
        if (actualTitle.contains(expectedTitle)) {
            System.out.println("Pass: the page title contains the word “Used Cars” ");
        } else {
            System.out.println("Fail");
        }

        // 4. Choose “Tesla” for  Make.

        driver.findElement(By.name("make")).sendKeys("Tesla" + Keys.ENTER + Keys.TAB);
        Thread.sleep(1000);
        driver.findElement(By.name("make")).sendKeys("Tesla" + Keys.ENTER + Keys.TAB);

        // ** ElementNotInteractableException: element not interactable -> solution:

        driver.findElement(By.name("model")).click();

        // 5. Verify that the Select Model dropdown box contains 3 current Tesla models - (Model 3, Model S, Model X).

//        List<WebElement> teslaModelsDropBox = driver.findElements(By.xpath("//optgroup//option[@class='model-option'] "));
//        for (WebElement el: teslaModelsDropBox) {
//            System.out.println(el.getText());
//        }


        List<WebElement> models = new Select(driver.findElement(By.name("model"))).getOptions();

        List<String> expectedList = Arrays.asList(" Model 3 ", " Model S ", " Model X ", " Roadster ");


        List<String> actualArraylist = new ArrayList<>();
        for (int i = 1; i < models.size(); i++) {
            actualArraylist.add(models.get(i).getText());
        }

        System.out.println(actualArraylist);
        System.out.println(expectedList);

        if (actualArraylist.equals(expectedList)) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        // 6. Choose “Model S” for Model.


        new Select(driver.findElement(By.name("model"))).selectByValue("Model S");

        // 7. Enter the zipcode as 22182 and click Next

        driver.findElement(By.name("zip")).sendKeys("22182" + Keys.ENTER);

        // 8.  Verify that the page has “Step 2 – Show me cars with” text

        System.out.println(driver.getPageSource().contains("Step 2 - Show me cars with") ? "Pass" : "Failed");

        // 9. Click on all 4 checkboxes.

        List<WebElement> clickCheckBox = driver.findElements(By.xpath("//span[@role='checkbox']"));

        for (WebElement el : clickCheckBox) {
            if (!el.isSelected())
                el.click();
        }

        // 10. Save the result of “Show me X Results” button to a variable. In this case it is 6.

        int result = Integer.parseInt(driver.findElement(By.xpath("//span[@class='totalRecordsText']")).getText());
        System.out.println("Total result is: " + result);

        // 11.  Click on “Show me x Results” button.
        driver.findElement(By.xpath("//span[@class='totalRecordsText']")).click();


        // 12. On the next page, verify that the results page has the same
        // number of results as indicated in Step 10 by extracting the number and comparing the result

        int resultConfirm = Integer.parseInt(driver.findElement(By.xpath("//span[@id='totalResultCount']")).getText());

        if (resultConfirm == result) {
            System.out.println("Pass, results page has the same number of results as indicated in Step 10");
        } else {
            System.out.println("Fail" + resultConfirm);
        }

        // 13. Verify the results also by getting the actual number of results displayed in the page
        // with the number in the Step 10. For this step get the count the number of WebElements related to each result.


        List<WebElement> numberOfWebElements = driver.findElements(By.xpath("//h4[@class='srp-list-item-basic-info-model']"));
        int count = 0;

        for (WebElement e : numberOfWebElements) {
            count++;
        }
        System.out.println((result == count) ? "Pass. The count the number of WebElements related to each result are: " + count + " equals " + result : "Fail");

        // 14. Verify that each result contains String “Tesla Model S”.


        System.out.println();
        for (WebElement e : numberOfWebElements) {
            if (e.getText().contains("Tesla Model S")) {
                System.out.println("Pass: contains->" + e.getText());
            } else {
                System.out.println("fail");
            }
        }

        // 15. Get the price of each result and save them into a list in the order of their appearance.

        List<String> priceasString = new ArrayList<>();
        List<WebElement> pricesListWebElements = driver.findElements(By.xpath("//span[@class='srp-list-item-price']"));
        for (WebElement e : pricesListWebElements) {
            priceasString.add(e.getText().substring(7));
        }
        System.out.println(priceasString);

        // 16. Choose “Price - High to Low” option from Sort menu

        new Select(driver.findElement(By.xpath("//select[@class='srp-header-sort-select ']"))).
                selectByValue("PRICE_DESC");
        Thread.sleep(2000);

        // 17. Verify that the results are displayed from high to low price.

        System.out.println();
        Thread.sleep(2000);
        for (WebElement element17: pricesListWebElements) {
            System.out.println(priceasString.add(element17.getText().substring(7) ) );
        }


            // 18. Choose “Mileage - Low to High” option from Sort menu

            new Select(driver.findElement
                    (By.xpath("//select[@class='srp-header-sort-select ']"))).selectByValue("PRICE_ASC");

            // 19. Verify that the results are displayed from Low to High mileage.

            System.out.println();
            Thread.sleep(2000);
            for (WebElement element19 : pricesListWebElements) {
                System.out.println(priceasString.add(element19.getText().substring(7)));
            }

            // 20. Choose “Year - New to Old” option from Sort menu

        new Select(driver.findElement
                (By.xpath("//select[@class='srp-header-sort-select ']"))).selectByValue("YEAR_DESC");

            // 21. Verify that the results are displayed from new to old year.

        System.out.println();
        Thread.sleep(2000);
        for (WebElement element21 : pricesListWebElements) {
            System.out.println(priceasString.add(element21.getText().substring(7)));
        }


        }
    }