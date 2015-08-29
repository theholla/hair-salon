import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Client.all().size());
  }

  @Test
  public void client_instantiatesCorrectly_true() {
    Client testClient = new Client("Foxy", "513-444-2214", 1);
    assertEquals(true, testClient instanceof Client);
  }

  @Test
  public void client_returnsClient_Name() {
    Client testClient = new Client("Purp", "414-224-6643", 2);
    assertEquals("Purp", testClient.getName());
  }

  @Test
  public void save_savesIntoDatabase() {
    Client testClient = new Client("Sally", "434-555-3342", 3);
    testClient.save();
    assertTrue(Client.getFirstDBEntry().equals(testClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client testClient = new Client("Sally", "434-555-3342", 3);
    testClient.save();
    Client savedClient = Client.getFirstDBEntry();
    assertEquals(testClient.getId(), savedClient.getId());
  }

  @Test
  public void equals_returnsTrueIfSame() {
    Client testClient = new Client("Hector", "444-222-3333", 1);
    Client nextClient = new Client("Hector", "444-222-3333", 1);
    assertTrue(testClient.equals(nextClient));
  }


  @Test
  public void find_findsClientInDatabase() {
    Client testClient = new Client("Mellow", "143-111-3333", 1);
    testClient.save();
    Client savedClient = Client.find(testClient.getId());
    assertEquals(savedClient.getName(), "Mellow");
  }

  @Test
  public void save_savesIntoListOfClients() {
    Client testClient = new Client("Mellow", "143-111-3333", 1);
    testClient.save();
    assertEquals(Client.getFirstDBEntry().getName(), "Mellow");
  }

  @Test
  public void find_findsClientFromDatabase() {
    Client testClient = new Client("Mellow", "143-111-3333", 1);
    testClient.save();
    Client savedClient = Client.find(testClient.getId());
    assertEquals(savedClient.getName(), "Mellow");
  }

  @Test
  public void save_savesStylistIdIntoDB() {
    Stylist testStylist = new Stylist("Sally", "434-555-3342");
    testStylist.save();
    Client testClient = new Client("Mellow", "143-111-3333", testStylist.getId());
    testClient.save();
    Client savedClient = Client.find(testClient.getId());
    assertEquals(savedClient.getStylistId(), testStylist.getId());
  }

}
