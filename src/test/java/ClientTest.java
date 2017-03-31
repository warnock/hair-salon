import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

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
  public void getPhone_returnsInstanceOfPhone_true() {
    Client newClient = new Client("Phill", "5th ave", "222-222-2222", 1);
    assertEquals("222-222-2222", newClient.getPhone());
  }

}
