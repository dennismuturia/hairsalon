import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Customer{
    //Create some class variables
    private String name;
    private int id;
    private String phone;
    private int styles;
    private int stylistId;

//We create the Customer method where the name string will be passed
    public Customer(String name, String phone, int styles, int stylistId){
        this.name = name;
        this.stylistId = stylistId;
    }
    //We add a getName method for getting the private class parameter to be accessible
    public String getName(){
        return name;
    }

    //New id that will return the stylist id
    public int getStylistId(){
        return stylistId;
    }
    public String getPhone(){
        return phone;
    }

    public int getStyles(){
        return styles;
    }


    //Lets get the id
    public int getId(){
        return id;
    }
    //Here the Specific Customer is looked for with a specific id
    public static Customer find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM customers where id=:id";
            Customer myCustomer = con.createQuery(sql)
              .addParameter("id", id)
              .executeAndFetchFirst(Customer.class);
            return myCustomer;
          } 
      }

      //Create an overrride that will override our equals
      @Override
      public boolean equals(Object otherCustomer) {
        if (!(otherCustomer instanceof Customer)) {
          return false;
        } else {
          Customer myCustomer = (Customer) otherCustomer;
          return this.getName().equals(myCustomer.getName()) &&
          this.getId() == myCustomer.getId()&&
          this.getStylistId() == myCustomer.getStylistId();
        }
      }

      public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO customers (name, phone ,styles,stylistId) VALUES (:name, :phone, :styles, :stylistId)";
          this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .addParameter("phone", this.phone)
            .addParameter("styles", this.styles)
            .addParameter("stylistId", this.stylistId)
            .executeUpdate()
            .getKey();
        }
      }
    //Here we will be using the all so that it may store all our data and push them to our database
    public static List<Customer> all(){
        String sql = "SELECT id, name, phone, styles, stylistId FROM customers";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Customer.class);
        }
    }


}