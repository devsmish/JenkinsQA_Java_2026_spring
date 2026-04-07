package old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AleksandraTest {

    @Test
    public void testDuck () {
        WebDriver driver = new ChromeDriver();
        driver.get("https://trikky.ru/tag/utki");

        WebElement images = driver.findElement(By.id("menu-item-550158"));
        images.click();

        WebElement title = driver.findElement(By.id("category-title-section"));
        Assert.assertEquals(title.getText(), "Подбор картинок");

        driver.quit();
    }
}
