import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FedorTest {
    
    @Test
    public void testFedorStreha() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://clck.ru/3SgiiB");
        WebElement strk = driver.findElement(By.xpath("//input [@ id = 'searchStr']"));
        strk.sendKeys("ккккк");
        WebElement button = driver.findElement(By.xpath("// input [@id = 'searchButton']"));
        button.click();
        WebElement text = driver.findElement(By.xpath("//div/p/b"));

        Assert.assertEquals(text.getText(), "По Вашему запросу ничего не найдено.");

        driver.quit();
    }
}
