import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DmitriyTest {

    @Test
    public void wikiSimpleTest() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.wikipedia.org/");

        driver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys("Java programming language");
        driver.findElement(By.xpath("//*[@id=\"search-form\"]/fieldset/button/i")).click();

        WebElement findingText = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[2]/table[1]/tbody/tr[4]/td/a"));

        Assert.assertEquals(findingText.getText(), "James Gosling");

        driver.quit();
    }

    @Test
    public void wikiSimpleTestRu() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://ru.wikipedia.org");

        driver.findElement(By.id("searchInput")).sendKeys("Петербург", Keys.ENTER);
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[3]/div[4]/ul/li[1]/div[2]/div[2]/div[1]/a")).click();
        WebElement result = driver.findElement(By.id("Предыстория,_основание_города_и_XVIII_век"));

        Assert.assertEquals(result.getText(), "Предыстория, основание города и XVIII век");

        driver.quit();
    }
}
