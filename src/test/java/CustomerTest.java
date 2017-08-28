import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class CustomerTest{
    @Before
    public void setUstylistp(){
        //Pass the database information ie the password and the username of the DB as well as its localhost
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hairsalon_test", "muturia", "DontAsk12");
    }


    @Test
    //This Method will now check if the customer class will instantiates as expected
    public void CheckWhethertheClassInstantiatesCorrectly(){
      Stylist myStylist = new Stylist("Daisy","87678687",1,1);
        Customer myCustomer = new Customer("Grace", "075675765", 1 , myStylist.getId());
        assertEquals(true, myCustomer instanceof Customer);
    }

    //Trying to save a stylist in the customers
    @Test
    public void save_savesStylistIdIntoDB_true() {
      Stylist myStylist = new Stylist("Daisy","87678687",1,1);
      myStylist.save();
      Customer myCustomer = new Customer("Grace", "075675765",1 , myStylist.getId());
      myCustomer.save();
      Customer savedCustomer = Customer.find(myCustomer.getId());
      assertEquals(savedCustomer.getStylistId(), myStylist.getId());
    }
    @Test
    //Since we have initialized the string in the Customer class, We check if it will instantiates with it
    public void CheckWhethertheClassInstantiatesWithStringCorrectly(){
      Stylist myStylist = new Stylist("Daisy","87678687",1,1);
        Customer myCustomer = new Customer("Grace","075675765", 1 , myStylist.getId());
        assertEquals("Grace", myCustomer.getName());
    }
       //This test checks whether all the instances of the Customer class passes
       @Test
       public void all_returnsAllInstancesOfCustomers_true() {
        Stylist myStylist = new Stylist("Daisy","87678687",1,1);
         Customer myCustomer1 = new Customer("Grace","075675765", 1 , myStylist.getId());
         myCustomer1.save();
         Customer myCustomer2 = new Customer("Graceyyy","075675765", 1 , myStylist.getId());
         myCustomer2.save();
         assertEquals(true, Customer.all().get(0).equals(myCustomer1));
         assertEquals(true, Customer.all().get(1).equals(myCustomer2));
       }
       //This Test will be used to assign ids to the individual Customer
       @Test
       public void getId_CustomerInstantiateWithAnID() {
        Stylist myStylist = new Stylist("Daisy","87678687",1,1);
        Customer myCustomer = new Customer("Grace","075675765", 1 , myStylist.getId());
         myCustomer.save();
         assertTrue(myCustomer.getId() > 0);
       }

       

       //Check whether individual Customers will be given Id. This applies to the second customer
       
       @Test
       public void find_returnsCustomerWithSameId_secondCustomer() {
        Stylist myStylist = new Stylist("Daisy","87678687",1,1);
         Customer myCustomer1 = new Customer("Grace","075675765", 1 , myStylist.getId());
         myCustomer1.save();
         Customer myCustomer2 = new Customer("Njoroge","075675765", 1 , myStylist.getId());
         myCustomer2.save();
         assertEquals(Customer.find(myCustomer2.getId()).getName(), myCustomer2.getName());
         assertEquals(Customer.find(myCustomer1.getId()).getName(), myCustomer1.getName());
       }
    //Here we are going to create a test that will override the equals
    @Test
    public void equals_returnsTrueIfNamesAretheSame() {
      Stylist myStylist = new Stylist("Daisy","87678687",1,1);
      Customer myCustomer1 = new Customer("Grace","075675765", 1 , myStylist.getId());
      Customer myCustomer2 = new Customer("Grace","075675765", 1 , myStylist.getId());
      assertTrue(myCustomer1.equals(myCustomer1));
    }
    //Why dont we save a new object? Here is the code.
    @Test
    public void save_returnsTrueIfNamesAretheSame() {
      Stylist myStylist = new Stylist("Daisy","87678687",1,1);
      Customer myCustomer = new Customer("Grace","075675765", 1 , myStylist.getId());
      myCustomer.save();
      assertTrue(Customer.all().get(0).equals(myCustomer));
    }
    //Lets Assign new id which are  unique
    @Test
    public void save_assignsIdToObject() {
      Stylist myStylist = new Stylist("Daisy","87678687",1,1);
      Customer myCustomer = new Customer("Grace","075675765", 1 , myStylist.getId());
      myCustomer.save();
      Customer savedCustomer = Customer.all().get(0);
      assertEquals(myCustomer.getId(), savedCustomer.getId());
    }
    //Now lets update data to our app
    @Test
    public void update_updatesCustomerName_true() {
      Stylist myStylist = new Stylist("Daisy","87678687",1,1);
      Customer myCustomer = new Customer("Grace","075675765", 1 , myStylist.getId());
      myCustomer.save();
      myCustomer.update("Milcah");
      assertEquals("Milcah", Customer.find(myCustomer.getId()).getName());
    }
    //Lets delete some  stuff
    @Test
    public void delete_deletesUser_true() {
      Stylist myStylist = new Stylist("Daisy","87678687",1,1);
      Customer myCustomer = new Customer("Grace","075675765", 1 , myStylist.getId());
      myCustomer.save();
      int myCustomerId = myCustomer.getId();
      myCustomer.delete();
      assertEquals(null, Customer.find(myCustomerId));
    }

    @After
    //This method Clears down the database tables
    public void tearDown() {
        //Here we run the try exemption to try and connect to the database 
      try(Connection con = DB.sql2o.open()) {
        String sql = "DELETE FROM customers *;";
        con.createQuery(sql).executeUpdate();
      }
    }

}