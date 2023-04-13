package DreamSoft;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class DreamSoft {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void testCaseOne() {
        driver.get("https://mind-wend-913065.framer.app/pricing");
        List <WebElement> currencySymbolElement = driver.findElements(By.xpath("//span[contains(text(),'$')]"));
        Assert.assertEquals(currencySymbolElement.size(), 3);
        Assert.assertEquals(currencySymbolElement.get(0).getText(), "$15/mo");
        Assert.assertEquals(currencySymbolElement.get(1).getText(), "$30/mo");
        Assert.assertEquals(currencySymbolElement.get(2).getText(), "$45/mo");
    }


    @Test
    public void testCaseTwo() {
        driver.get("https://mind-wend-913065.framer.app/pricing");
        List<WebElement> faqQuestions = driver.findElements(By.xpath("//div[@class='framer-eabl6y']//h3"));
        Assert.assertEquals(faqQuestions.size(), 4, "FAQ questions should be 4");
    }

    @Test
    public void testCaseThree() {
        driver.get("https://mind-wend-913065.framer.app/components");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement manyComponentsTextElement = driver.findElement(By.xpath("//span[contains(text(),'Many types of components to customize')]"));

        js.executeScript("arguments[0].scrollIntoView();", manyComponentsTextElement);
        List <WebElement> cards = driver.findElements(By.xpath("//div[@name='Card']"));
        List <WebElement> smallCards = driver.findElements(By.xpath("//div[@name='Card Small']"));

        List <WebElement> visitTextOnCards = driver.findElements(By.xpath("//div[@name='Card']//span[contains(text(),'Visit')]"));
        List <WebElement> visitTextOnSmallCards = driver.findElements(By.xpath("//div[@name='Card Small']//span[contains(text(),'Visit')]"));

        Assert.assertEquals(cards.size(), visitTextOnCards.size());
        Assert.assertEquals(smallCards.size(), visitTextOnSmallCards.size());
    }

    @Test
    public void testCaseFour() {
        driver.get("https://mind-wend-913065.framer.app/components");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement copyrightText = driver.findElement(By.xpath("//span[contains(text(),'Copyright')]"));

        js.executeScript("arguments[0].scrollIntoView();", copyrightText);
        WebElement signUpButton = driver.findElement(By.xpath("//input[@value='Sign Up']"));
        String buttonColor = signUpButton.getCssValue("background");
        Assert.assertTrue(buttonColor.contains("rgb(255, 82, 79)"));
    }

}
