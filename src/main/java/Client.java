import org.sql2o.*;

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

}
