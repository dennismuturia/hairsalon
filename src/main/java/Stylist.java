public class Stylist{
    //Initialize all your Class Variables
    private String mName;
    //This method will be efficient when you want to instantiate the class name
    public Stylist(String name){
        mName =  name;
    }
     //We add a getName method for getting the private class parameter to be accessible
    public String getName(){
        return mName;
    }
}