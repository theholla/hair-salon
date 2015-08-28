import org.sql2o.*;
import java.util.List;

public class Client implements Person {
  private int id;
  private String name;
  private String phone;
  private int stylist_id;

  public Client(String name, String phone, int stylist_id) {
    this.name = name;
    this.phone = phone;
    this.stylist_id = stylist_id;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public int getId() {
    return id;
  }

  public int getStylistId() {
    return stylist_id;
  }


  public static List<Client> all() {
    String sql = "SELECT name, phone, stylist_id FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static Client getFirstDBEntry() {
    return all().get(0);
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, phone, stylist_id) VALUES (:name, :phone, stylist_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("phone", this.phone)
      .addParameter("stylist_id", this.stylist_id)
      .executeUpdate()
      .getKey();
    }
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
             this.getPhone().equals(newClient.getPhone()) &&
             this.getStylistId() == newClient.getStylistId();
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

}
