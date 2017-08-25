import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist{
    //Initialize all your Class Variables
    private String name;
    private int id;
    private String phone;
    private int styles_id;
    private int customer_id;

    //This method will be efficient when you want to instantiate the class name
    public Stylist(String name, String phone, int styles_id, int customer_id){
        this.name =  name;
    }
     //We add a getName method for getting the private class parameter to be accessible
    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public int getStyles_id(){
        return styles_id;
    }
    public int getCustomer_id(){
        return customer_id;
    }


    //This stores all the users in a list
    
    public static List<Stylist> all() {
        String sql = "SELECT id, name, phone, styles_id, customer_id FROM stylist";
        try(Connection con = DB.sql2o.open()) {
          return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
    }

      //This will get the id that is inputted
      public int getId(){
          return id;
      }

      //Find method
      public static Stylist find(int id) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM stylist where id=:id";
          Stylist myStylist = con.createQuery(sql)
            .addParameter("id", id)
            .executeAndFetchFirst(Stylist.class);
          return myStylist;
        }
      }

          //Create an override here in the stylist section
          @Override
          public boolean equals(Object otherStylist) {
            if (!(otherStylist instanceof Stylist) ){
              return false;
            } else {
              Stylist newStylist= (Stylist) otherStylist;
              return this.getName().equals(newStylist.getName()) &&
                     this.getId() == newStylist.getId();
            }
          }

    //This will save our to our table
    public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO stylist (name, phone, styles_id, customer_id) VALUES (:name, :phone, :styles_id, :customer_id)";
          this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .addParameter("phone", this.phone)
            .addParameter("styles_id", this.styles_id)
            .addParameter("customer_id", this.customer_id)
            .executeUpdate()
            .getKey();
        }
      }
      public List<Customer> getCustomers() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM customers WHERE stylistId=:id";
          return con.createQuery(sql)
            .addParameter("id", this.id)
            .executeAndFetch(Customer.class);
        }
      }

      */

}