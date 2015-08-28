import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
   }

   protected void after() {
       try(Connection con = DB.sql2o.open()) {
         String deleteClientQuery = "DELETE FROM clients *;";
         String deleteStylistQuery = "DELETE FROM stylists *;";
         con.createQuery(deleteClientQuery).executeUpdate();
         con.createQuery(deleteStylistQuery).executeUpdate();
       }
   }
}
