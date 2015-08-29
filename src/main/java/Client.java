import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String client_name;
  private String client_phone;
  private int stylist_id;

  public Client(String client_name, String client_phone, int stylist_id) {
    this.client_name = client_name;
    this.client_phone = client_phone;
    this.stylist_id = stylist_id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return client_name;
  }

  public String getPhone() {
    return client_phone;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public static Client getFirstDBEntry() {
    return all().get(0);
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(client_name, client_phone, stylist_id) VALUES (:client_name, :client_phone, :stylist_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("client_name", this.client_name)
      .addParameter("client_phone", this.client_phone)
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
      return this.getId() == newClient.getId() &&
             this.getName().equals(newClient.getName()) &&
             this.getPhone().equals(newClient.getPhone()) &&
             this.getStylistId() == newClient.getStylistId();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT id, client_name, client_phone, stylist_id FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
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
