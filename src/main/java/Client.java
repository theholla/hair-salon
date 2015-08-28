import org.sql2o.*;
import java.util.List;

public class Client implements Person {
  private int id;
  private String name;
  private String phone;

  public Client(String name, String phone) {
    this.name = name;
    this.phone = phone;
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

}
