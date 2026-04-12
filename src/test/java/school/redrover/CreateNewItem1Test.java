package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateNewItem1Test extends BaseTest {
    @Test
    public void testNavigateToItemCreatePage() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();
        WebElement text = getDriver().findElement(By.xpath("//h1"));
        Assert.assertEquals(text.getText(), "New Item");
    }

}
