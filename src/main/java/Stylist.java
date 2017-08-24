import java.util.List;
import java.util.ArrayList;
public class Stylist{
    //Initialize all your Class Variables
    private String mName;
    private static List<Stylist> instances = new ArrayList<Stylist>();
    private int mId;
    private List<Customer>myCustomers;

    //This method will be efficient when you want to instantiate the class name
    public Stylist(String name){
        mName =  name;
        instances.add(this);
        mId = instances.size();
       myCustomers = new ArrayList<Customer>();
    }
     //We add a getName method for getting the private class parameter to be accessible
    public String getName(){
        return mName;
    }
    //This stores all the users in a list
    public static List<Stylist> all() {
        return instances;
      }
      //This method clears all the stylists in a list
      public static void clear() {
        instances.clear();
      }
      //This will get the id that is inputted
      public int getId(){
          return mId;
      }
      //Finds the specific ID
      public static Stylist find(int id) {
        return instances.get(id - 1);
      }
      //This connects the Customers to the Stylist
      public List<Customer> getCustomers() {
        return myCustomers;
      }
      public void addCustomers(Customer customer) {
        myCustomers.add(customer);
      }

}