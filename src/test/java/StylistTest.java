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

  //So that they might be able to select any Stylist to fix the hair
  @Test
  public void all_returnsAllInstancesOfStylist_true() {
    Stylist myStylist1 = new Stylist("Daisy");
    Stylist myStylist2 = new Stylist("Carol");
    assertEquals(true, Stylist.all().contains(myStylist1));
    assertEquals(true, Stylist.all().contains(myStylist2));
  }

  //This test is for emptying the list
  @Test
  public void clear_emptiesAllStylistsFromList_0() {
    Stylist myStylist1 = new Stylist("Daisy");
    Stylist.clear();
    assertEquals(Stylist.all().size(), 0);
  }
    //This Test will be used to assign ids to the individual stylist
    @Test
    public void getId_StylistInstantiateWithAnId_1() {
        Stylist.clear();
        Stylist myStylist = new Stylist("Daisy");
        assertEquals(1, myStylist.getId());
    }
    //Make it locate a specific stylist with an ID
    @Test
    public void findASpecificStlistSecondStylist(){
        Stylist.clear();
        Stylist myStylist1 = new Stylist("Daisy");
        Stylist myStylist2 = new Stylist("Carol");
        assertEquals(Stylist.find(myStylist2.getId()), myStylist2);
    }
    //This finds the specific stylist using the id
    @Test
    public void find_returnsStylistWithSameId_secondStylist() {
      Stylist.clear();
      Stylist myStylist1 = new Stylist("Daisy");
      Stylist myStylist2 = new Stylist("Carol");
      assertEquals(Stylist.find(myStylist2.getId()), myStylist2);
    }
    //Lets connect the Stylists to the customers
    @Test
    public void getCustomers_initiallyReturnsEmptyList_ArrayList() {
      Stylist.clear();
      Stylist myStylist = new Stylist("Dennis");
      assertEquals(0, myStylist.getCustomers().size());
    }

    //Add customer to Stylist
    @Test
    public void addCustomer_addsCustomerToList_true() {
      Stylist myStylist = new Stylist("Daisy");
      Customer myCustomer = new Customer("Grace");
      myStylist.addCustomers(myCustomer);
      assertTrue(myStylist.getCustomers().contains(myCustomer));
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