package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class FolderTest extends BaseTest {

    public void createFolder() {
        String folderName = "Test_Folder_HealthMetrics";
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("health-metrics")));

    }
    @Test
    public void testHealthMetricsAvailableOnFolderCreation() {
        createFolder();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@id='health-metrics']")).getText(),
                "Health metrics",
                "Health metrics section text should match expected");
    }

    @Test
    public void testHealthMetricsAvailableOnFolderChanges() {
        createFolder();
        getDriver().findElement(By.xpath("//span[text()='Jenkins']")).click();
        getDriver().findElement(By.xpath("//button[@class='jenkins-menu-dropdown-chevron']")).click();
        getDriver().findElement(By.cssSelector("#tippy-4 > div > div > div > a:nth-child(1)")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@id='health-metrics']")).getText(),
                "Health metrics",
                "Health metrics section text should match expected");
    }
}
