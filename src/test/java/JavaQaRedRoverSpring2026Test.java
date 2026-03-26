import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class JavaQaRedRoverSpring2026Test {

    @Test
    public void testVitaliyKonstantinov() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));

        Assert.assertEquals(message.getText(), "Received!");

        driver.quit();
    }

    @Test
    public void testAnnaKuryleva() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.mveu.ru");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.xpath("//input[@placeholder='ФИО*']"));
        String expectedText = "Иванов Иван Иванович";
        textBox.sendKeys(expectedText);

        WebElement button = driver.findElement(By.xpath("//button[contains(@class, 'form_button')]"));

        new Actions(driver).scrollToElement(button).perform();
        button.click();

        WebElement errorButton = driver.findElement(By.xpath("//div[@data-notivue='error']"));

        String actualErrorMessage = errorButton.getText();
        String expectedErrorMessage = "Проверьте все поля";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

        driver.quit();
    }

}
