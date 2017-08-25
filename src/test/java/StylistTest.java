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
    Stylist myStylist = new Stylist("Daisy","87678687",1,1);
    assertEquals(true, myStylist instanceof Stylist);
  }

  @Test
  public void getName_StylistInstantiatesWithName() {
    Stylist myStylist = new Stylist("Daisy","87678687",1,1);
    assertEquals("Daisy", myStylist.getName());
  }

  //So that they might be able to select any Stylist to fix the hair
  @Test
  public void all_returnsAllInstancesOfStylist_true() {
    Stylist myStylist1 = new Stylist("Daisy","87678687",1,1);
    myStylist1.save();
    Stylist myStylist2 = new Stylist("Carol","87678687",1,1);
    myStylist2.save();
    assertEquals(true, Stylist.all().get(0).equals(myStylist1));
    assertEquals(true, Stylist.all().get(1).equals(myStylist2));
  }

  //This test is for emptying the list
  @Test
  public void clear_emptiesAllStylistsFromList_0() {
    Stylist myStylist1 = new Stylist("Daisy","87678687",1,1);
    assertEquals(Stylist.all().size(), 0);
  }
    //This Test will be used to assign ids to the individual stylist
    @Test
    public void getId_StylistInstantiateWithAnId_1() {
        Stylist myStylist = new Stylist("Daisy","87678687",1,1);
        myStylist.save();
        assertTrue(myStylist.getId() > 0);
    }

    //This returns objects with the same id as with the db
    @Test
    public void save_assignsIdToObject() {
    Stylist myStylist = new Stylist("Daisy","87678687",1,1);
      myStylist.save();
      Stylist savedStylist = Stylist.all().get(0);
      assertEquals(myStylist.getId(), savedStylist.getId());
    }
    //Make it locate a specific stylist with an ID
    @Test
    public void findASpecificStlistSecondStylist(){
        Stylist myStylist1 = new Stylist("Daisy","87678687",1,1);
        Stylist myStylist2 = new Stylist("Carol","87678687",1,1);
        assertEquals(Stylist.find(myStylist2.getId()), myStylist2);
    }
    //This finds the specific stylist using the id
    @Test
    public void find_returnsStylistWithSameId_secondStylist() {
      Stylist myStylist1 = new Stylist("Daisy","87678687",1,1);
      myStylist1.save();
      Stylist myStylist2 = new Stylist("Daisy","87678687",1,1);
      myStylist2.save();
      assertEquals(Stylist.find(myStylist2.getId()), myStylist2);
    }
    //Lets connect the Stylists to the customers
    @Test
    public void getCustomers_initiallyReturnsEmptyList_ArrayList() {
      Stylist myStylist = new Stylist("Daisy","87678687",1,1);
      assertEquals(0, myStylist.getCustomer().size());
    }

    //Add customer to Stylist
    @Test
    public void addCustomer_addsCustomerToList_true() {
      Stylist myStylist = new Stylist("Daisy","87678687",1,1);
      Customer myCustomer = new Customer("Grace","075675765", 1);
      myStylist.addCustomers(myCustomer);
      assertTrue(myStylist.getCustomers().contains(myCustomer));
    }

    //This is an equals statement
    @Test
    public void equals_returnsTrueIfNamesAretheSame() {
        Stylist myStylist1 = new Stylist("Daisy","87678687",1,1);
        Stylist myStylist2 = new Stylist("Monroe","87678687",1,1);
      assertTrue(myStylist1.equals(myStylist2));
    }

    //This is for adding new stylists into our databases
    @Test
    public void save_savesIntoDatabase_true() {
      Stylist myStylist = new Stylist("Daisy","87678687",1,1);
      myStylist.save();
      assertTrue(Stylist.all().get(0).equals(myStylist));
    }
  @After
  //This method Clears down the database tables
  public void tearDown() {

      //Here we run the try exemption to try and connect to the database 
      try(Connection con = DB.sql2o.open()) {
        String deleteCustomerQuery = "DELETE FROM customers *;";
        String deleteStylistQuery = "DELETE FROM  stylist*;";
        con.createQuery(deleteCustomerQuery).executeUpdate();
        con.createQuery(deleteStylistQuery).executeUpdate();
      }
  }
}