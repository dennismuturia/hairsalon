import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

    @Before
    public void setUp(){
        //Pass the database information ie the password and the username of the DB as well as its localhost
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hairsalon_test", "muturia", "DontAsk12");
    }


    //Now here we check if the Stylist instantiates correctly without any mishap
  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist myStylist = new Stylist("Daisy");
    assertEquals(true, myStylist instanceof Stylist);
  }

  @Test
  public void getName_StylistInstantiatesWithName() {
    Stylist myStylist = new Stylist("Daisy");
    assertEquals("Daisy", myStylist.getName());
  }

  @After
  //This method Clears down the database tables
  public void tearDown() {
      //Here we run the try exemption to try and connect to the database 
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylist *;";
      con.createQuery(sql).executeUpdate();
    }
  }
}