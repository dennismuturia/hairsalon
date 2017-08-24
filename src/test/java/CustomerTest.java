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
         Customer myCustomer2 = new Customer("Njoroge");
         assertEquals(true, Customer.all().contains(myCustomer1));
         assertEquals(true, Customer.all().contains(myCustomer2));
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