package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class FolderTest extends BaseTest {

    @Test
    public void testHealthMetricsAvailableOnFolderCreation() {
        String folderName = "Test_Folder_HealthMetrics";
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("health-metrics")));

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@id='health-metrics']")).getText(),
                "Health metrics",
                "Health metrics section text should match expected");
    }
}
