import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Customer{
    //Create some class variables
    private String name;
    private int id;

//We create the Customer method where the name string will be passed
    public Customer(String name){
        this.name = name;
    }
    //We add a getName method for getting the private class parameter to be accessible
    public String getName(){
        return name;
    }


    //Lets get the id
    public int getId(){
        return id;
    }
    //Here the Specific Customer is looked for with a specific id
    public static Customer find(int id) {
        
      }
    //Here we will be using the all so that it may store all our data and push them to our database
    public static List<Customer> all(){
        String sql = "SELECT id, name FROM customers";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Customer.class);
        }
    }

}