import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class CustomerTest{
    @Before
    public void setUp(){
        //Pass the database information ie the password and the username of the DB as well as its localhost
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hairsalon_test", "muturia", "DontAsk12");
    }


    @Test
    //This Method will now check if the customer class will instantiates as expected
    public void CheckWhethertheClassInstantiatesCorrectly(){
        Customer myCustomer = new Customer("Grace");
        assertEquals(true, myCustomer instanceof Customer);
    }

    @Test
    //Since we have initialized the string in the Customer class, We check if it will instantiates with it
    public void CheckWhethertheClassInstantiatesWithStringCorrectly(){
        Customer myCustomer = new Customer("Grace");
        assertEquals("Grace", myCustomer.getName());
    }

       //This test checks whether all the instances of the Customer class passes
       @Test
       public void all_returnsAllInstancesOfCustomers_true() {
         Customer myCustomer1 = new Customer("Grace");
         myCustomer1.save();
         Customer myCustomer2 = new Customer("Njoroge");
         myCustomer2.save();
         assertEquals(true, Customer.all().get(0).equals(myCustomer1));
         assertEquals(true, Customer.all().get(1).equals(myCustomer2));
       }

       //This Test will be used to assign ids to the individual Customer
       /*
       @Test
       public void getId_customersInstantiateWithAnID_1() {
         Customer.clear();  // Remember, the test will fail without this line! We need to empty leftover Tasks from previous tests!
         Customer myCustomer = new Customer("Grace");
         assertEquals(1, myCustomer.getId());
       }
       */

       //Check whether individual Customers will be given Id. This applies to the second customer
       /*
       @Test
       public void find_returnsCustomerWithSameId_secondCustomer() {
         Customer myCustomer1 = new Customer("Grace");
         Customer myCustomer2 = new Customer("Njoroge");
         assertEquals(Customer.find(myCustomer2.getId()), myCustomer2);
       }
       */
    //Here we are going to create a test that will override the equals
    @Test
    public void equals_returnsTrueIfNamesAretheSame() {
      Customer myCustomer1 = new Customer("Grace");
      Customer myCustomer2 = new Customer("Kim");
      assertTrue(myCustomer1.equals(myCustomer2));
    }

    //Lets save new objects to our database
    @Test
    public void save_returnsTrueIfNamesAretheSame() {
    Customer myCustomer = new Customer("Grace");
      myCustomer.save();
      assertTrue(Customer.all().get(0).equals(myCustomer));
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