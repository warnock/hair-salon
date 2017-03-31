import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Arrays;

public class StylistTest {

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
  public void Stylist_returnsInstanceOfStylist_true() {
    Stylist newStylist = new Stylist("Bob");
    assertEquals(true, newStylist instanceof Stylist);
  }

  @Test
  public void getName_returnsInstanceOfName_Bob() {
    Stylist newStylist = new Stylist("Bob");
    assertEquals("Bob", newStylist.getName());
  }

  @Test
  public void getId_returnsInstanceOfId_1() {
    Stylist newStylist = new Stylist("Bob");
    newStylist.save();
    assertTrue(newStylist.getId() > 0);
  }

  @Test
  public void all_getsAllStylist_true() {
    Stylist firstStylist = new Stylist("Bob");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Clair");
    secondStylist.save();
    assertEquals(true, Stylist.all().get(0).equals(firstStylist));
    assertEquals(true, Stylist.all().get(1).equals(secondStylist));
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame() {
    Stylist firstStylist = new Stylist("Bob");
    Stylist secondStylist = new Stylist("Bob");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatavase_true() {
    Stylist newStylist = new Stylist("Bob");
    newStylist.save();
    assertTrue(Stylist.all().get(0).equals(newStylist));
  }

  @Test
  public void save_assignsIdToStylist() {
    Stylist newStylist = new Stylist("Bob");
    newStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(newStylist.getId(), savedStylist.getId());
  }

  @Test
  public void find_returnsStylistWithSameId_secondStylist() {
    Stylist firstStylist = new Stylist("Bob");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Clair");
    secondStylist.save();
    assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
  }

  @Test
  public void delete_deletesStylist_true() {
    Stylist myStylist = new Stylist("Bob");
    myStylist.save();
    myStylist.delete();
    assertEquals(null, Stylist.find(myStylist.getId()));
  }

  @Test
  public void update_updatesNameOfStylist_true() {
    Stylist myStylist = new Stylist("Bob");
    myStylist.save();
    myStylist.update("Clair");
    assertEquals("Clair", myStylist.find(myStylist.getId()).getName());
  }


  @Test
  public void getClients_returnsAllClientsFromDataBase_clientList() {
    Stylist newStylist = new Stylist("Bob");
    newStylist.save();
    Client firstClient = new Client("Phill", "5th ave", "222-222-2222", newStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Abe", "6th ave", "509-222-2222", newStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] {firstClient, secondClient};
    assertTrue(newStylist.getClients().containsAll(Arrays.asList(clients)));
  }

}
