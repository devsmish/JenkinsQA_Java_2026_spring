package school.redrover.common;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DescriptionTest extends BaseTest{

    @Test
    public void testDescription () {
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//*[@id='description-edit-form']/form/div[1]/div[1]/textarea")).sendKeys("hi");
        getDriver().findElement(By.xpath("//*[@id='description-edit-form']/form/div[2]/button[1]")).click();

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='description-content']")).getText(),"hi");
    }
}
