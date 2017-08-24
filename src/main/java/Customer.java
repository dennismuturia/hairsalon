public class Customer(){
    //Create some class variables
    private String mName;
//We create the Customer method where the name string will be passed
    public Customer(String name){
        mName = name
    }
    //We add a getName method for getting the private class parameter to be accessible
    public getName(){
        return mName;
    }
}