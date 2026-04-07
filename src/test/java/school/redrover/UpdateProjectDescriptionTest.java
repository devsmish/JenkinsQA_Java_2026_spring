package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.time.Duration;

public class UpdateProjectDescriptionTest extends BaseTest {

    @Test
    public void testUpdateProjectDescription() {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));

        String expectedDescription = "The goal of this project is to return Manchester United to its former glory";

        getDriver().findElement(By.xpath("//span[text()='Create a job']")).click();
        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Make Manchester Utd great again");
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("description"))).
                sendKeys("Fake description");

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='description-link']"))).click();

        WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("description")));
        descriptionField.clear();
        descriptionField.sendKeys(expectedDescription);

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        //String newDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='description-content']"))).getText();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("description-content"), expectedDescription));
        String newDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content"))).getText();

        Assert.assertEquals(newDescription, expectedDescription);

    }
}
