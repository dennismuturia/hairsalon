import java.util.List;
import java.util.ArrayList;

public class Customer{
    //Create some class variables
    private String mName;
    private static List<Customer> instances = new ArrayList<Customer>();
    private int mId;

//We create the Customer method where the name string will be passed
    public Customer(String name){
        mName = name;
        instances.add(this);
        mId = instances.size();
    }
    //We add a getName method for getting the private class parameter to be accessible
    public String getName(){
        return mName;
    }

    //Clearance of a list
    public static void clear(){
        instances.clear();
    }

    //Lets get the id
    public int getId(){
        return mId;
    }
    //Create the  List method that gets all of the customers  from the customers class
    public static List<Customer> all(){
        return instances;
    }
}