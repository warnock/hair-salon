import org.sql2o.*;
import java.util.List;

public class Client {
  private String name;
  private String address;
  private String phone;
  private int stylist_id;
  private int id;

  public Client(String name, String address, String phone, int stylist_id) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.stylist_id = stylist_id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public int getId() {
    return id;
  }

  public static List<Client> all() {
    String sql = "SELECT id, name, address, phone, stylist_id FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return
      this.getName().equals(newClient.getName()) &&
      this.getAddress().equals(newClient.getAddress()) &&
      this.getPhone().equals(newClient.getPhone()) &&
      this.getStylistId() == newClient.getStylistId() &&
      this.getId() == newClient.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, address, phone, stylist_id) VALUES (:name, :address, :phone, :stylist_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("address", this.address)
      .addParameter("phone", this.phone)
      .addParameter("stylist_id", this.stylist_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id = :id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateName(String name) {
    try(Connection con= DB.sql2o.open()) {
      String sql = "UPDATE clients SET name=:name WHERE id=:id";
      con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateAddress(String address) {
    try(Connection con= DB.sql2o.open()) {
      String sql = "UPDATE clients SET address=:address WHERE id=:id";
      con.createQuery(sql)
      .addParameter("address", address)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updatePhone(String phone) {
    try(Connection con= DB.sql2o.open()) {
      String sql = "UPDATE clients SET phone=:phone WHERE id=:id";
      con.createQuery(sql)
      .addParameter("phone", phone)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
