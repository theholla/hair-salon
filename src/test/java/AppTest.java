import org.fluentlenium.adapter.FluentTest;
import java.util.ArrayList;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Manage Your Clients");
  }

  @Test
  public void stylistIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a New Stylist"));
    fill("#name").with("Suzane");
    fill("#phone").with("503-222-1111");
    submit(".btn");
    assertThat(pageSource()).contains("Suzane");
  }

  @Test
  public void stylistIsDisplayedTest() {
    Stylist testStylist = new Stylist("Suzane", "503-222-1111");
    testStylist.save();
    String stylistPath = String.format("http://localhost:4567/%d", testStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Suzane");
  }

  @Test
  public void allClientsDisplayNameOnStylistPage() {
    Stylist testStylist = new Stylist("Melissa", "408-664-3322");
    testStylist.save();
    Client firstClient = new Client("Jane", "555-333-1111", testStylist.getId());
    firstClient.save();
    Client nextClient = new Client("Jake", "225-223-1122", testStylist.getId());
    nextClient.save();
    String stylistPath = String.format("http://localhost:4567/%d", testStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Jane");
    assertThat(pageSource()).contains("Jake");
  }

}
