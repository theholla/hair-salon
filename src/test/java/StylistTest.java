import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Stylist.all().size());
  }

  @Test
  public void stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Vera", "503-444-2214");
    assertEquals(true, testStylist instanceof Stylist);
  }

  @Test
  public void stylist_returnsStylist_name() {
    Stylist testStylist = new Stylist("Schmoopy", "434-224-6643");
    assertEquals("Schmoopy", testStylist.getName());
  }

  @Test
  public void save_savesIntoListOfStylists_name() {
    Stylist testStylist = new Stylist("Sally", "434-555-3342");
    testStylist.save();
    assertEquals("Sally", Stylist.getFirstDBEntry().getName());
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist testStylist = new Stylist("Sally", "434-555-3342");
    testStylist.save();
    Stylist savedStylist = Stylist.getFirstDBEntry();
    assertEquals(savedStylist.getId(), testStylist.getId());
  }

  @Test
  public void equals_returnsTrueIfSame_true() {
    Stylist testStylist = new Stylist("Hector", "444-222-3333");
    Stylist nextStylist = new Stylist("Hector", "444-222-3333");
    assertTrue(testStylist.equals(nextStylist));
  }

  @Test
  public void find_findsStylistInDatabase_true() {
    Stylist testStylist = new Stylist("Marina", "222-111-3333");
    testStylist.save();
    Stylist savedStylist = Stylist.find(testStylist.getId());
    assertTrue(testStylist.equals(savedStylist));
  }

  @Test
  public void getClients_retrievesAllClientsFromDatabase_clientsList() {
    Stylist testStylist = new Stylist("Darin", "222-333-2232");
    testStylist.save();
    Client firstClient = new Client("Mulloy", "333-222-1321", testStylist.getId());
    firstClient.save();
    Client nextClient = new Client("Mallory", "111-344-1321", testStylist.getId());
    nextClient.save();
    Client[] clients = new Client[] { firstClient, nextClient };
    assertTrue(testStylist.getClients().containsAll(Arrays.asList(clients)));
  }

}
