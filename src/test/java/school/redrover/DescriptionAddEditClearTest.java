package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class DescriptionAddEditClearTest extends BaseTest {

    private final String text = "My first project";
    private final String text2 = "My first successful project";

    @Test
    public void testAddDescription() {
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        getDriver().findElement(By.id("name")).sendKeys("Freestyle");
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.id("ok-button")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("description")));

        descriptionField.sendKeys(text);
        getDriver().findElement(By.className("textarea-show-preview")).click();

        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview")).getText(), text);

        getDriver().findElement(By.name("Submit")).click();
        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), text);

        getDriver().findElement(By.id("description-link")).click();
        WebElement textArea = getDriver().findElement(By.name("description"));
        textArea.clear();
        textArea.sendKeys(text2);

        getDriver().findElement(By.className("textarea-show-preview")).click();
        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview")).getText(), text2);

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), text2);

        getDriver().findElement(By.id("description-link")).click();
        WebElement textArea2 = getDriver().findElement(By.name("description"));
        textArea2.clear();

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertTrue(getDriver().findElement(By.id("description-content")).getText().isEmpty(),
                "Описание не очистилось!");
    }
}
