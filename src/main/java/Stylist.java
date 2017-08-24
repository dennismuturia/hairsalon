import java.util.List;
import java.util.ArrayList;
public class Stylist{
    //Initialize all your Class Variables
    private String mName;
    private static List<Stylist> instances = new ArrayList<Stylist>();

    //This method will be efficient when you want to instantiate the class name
    public Stylist(String name){
        mName =  name;
        instances.add(this);
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

}