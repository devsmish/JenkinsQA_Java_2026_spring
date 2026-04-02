package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class PipelineTest extends BaseTest {

    @Test
    public void testCreatePipeline() {
        final String projectName = "new Pipeline";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.className("app-jenkins-logo")).click();

        WebElement actualProjectName = getDriver().findElement(By
                .xpath("//span[text()='%s']".formatted(projectName)));
        Assert.assertEquals(actualProjectName.getText(), projectName);
    }
}
