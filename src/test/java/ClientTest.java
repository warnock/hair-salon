import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class ClientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String deleteStylistsQuery = "DELETE FROM stylists *;";
      String deleteClientsQuery = "DELETE FROM clients *;";
      con.createQuery(deleteStylistsQuery).executeUpdate();
      con.createQuery(deleteClientsQuery).executeUpdate();
    }
  }

  @Test
  public void Client_returnsInstanceOfClient_true() {
    Client newClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    assertEquals(true, newClient instanceof Client);
  }

  @Test
  public void getName_returnsInstanceOfName_true() {
    Client newClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    assertEquals("Phill", newClient.getName());
  }

  @Test
  public void getAddress_returnsInstanceOfAddress_true() {
    Client newClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    assertEquals("5th ave", newClient.getAddress());
  }

  @Test
  public void getStylistId_returnsInstanceOfStylistId_true() {
    Client newClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    assertEquals(1, newClient.getStylistId());
  }

  @Test
  public void getId_returnsInstanceOfStylistId_true() {
    Client newClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    assertEquals(1, newClient.getStylistId());
  }

  @Test
  public void getId_returnsInstanceOfId_true() {
    Client newClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    newClient.save();
    assertTrue(newClient.getId() > 0);
  }

  @Test
  public void all_getsAllClients_true() {
    Client firstClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    firstClient.save();
    Client secondClient = new Client("Abe", "6th ave", "509-222-2222", 1);
    secondClient.save();
    assertEquals(true, Client.all().get(0).equals(firstClient));
    assertEquals(true, Client.all().get(1).equals(secondClient));
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame() {
    Client firstClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    Client secondClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesIntoDatavase_true() {
    Client newClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    newClient.save();
    assertTrue(Client.all().get(0).equals(newClient));
  }

  @Test
  public void save_assignsIdToClient() {
    Client newClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    newClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(newClient.getId(), savedClient.getId());
  }

  @Test
  public void find_returnsClientWithSameId_secondClient() {
    Client firstClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    firstClient.save();
    Client secondClient = new Client("Abe", "6th ave", "509-222-2222", 1);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

  @Test
  public void delete_deletesClient_true() {
    Client myClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    myClient.save();
    myClient.delete();
    assertEquals(null, Client.find(myClient.getId()));
  }

  @Test
  public void updateName_updatesName_true() {
    Client myClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    myClient.save();
    myClient.updateName("Abe");
    assertEquals("Abe", myClient.find(myClient.getId()).getName());
  }

  @Test
  public void updateAddress_updatesAddress_true() {
    Client myClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    myClient.save();
    myClient.updateAddress("16th ave");
    assertEquals("16th ave", myClient.find(myClient.getId()).getAddress());
  }



}
