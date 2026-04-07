package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


public class MyFistTest extends BaseTest {

    @Test
    public void newTest() {

        getDriver().get("http://localhost:8080/view/all/newJob");
        WebElement actualString = getDriver().findElement(By.xpath("//*[@id='add-item-panel']/h1"));
        String expecytedString = "Новый Item";

        Assert.assertEquals(actualString, expecytedString, "Текст не совпадает");

    }

}
