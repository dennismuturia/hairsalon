import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class CustomerTest{
    @Before
    public void setUp(){
        //Pass the database information ie the password and the username of the DB as well as its localhost
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hairsalon_test", null, null);
    }

    @After
    //This method Clears down the database tables
    public void tearDown() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "DELETE FROM customers *;";
        con.createQuery(sql).executeUpdate();
      }
    }
}