package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class ManageJenkinsPageSystemButtonTest extends BaseTest {

    @Test
    public void testSystemButtonOpensConfigurationPage() {

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='configure']")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.titleIs("System - Manage Jenkins - Jenkins"));

        Assert.assertTrue(getDriver().getCurrentUrl().contains("/manage/configure"));
        Assert.assertEquals(getDriver().getTitle(), "System - Manage Jenkins - Jenkins");

    }
}
