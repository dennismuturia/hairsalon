import org.sql2o.*;

public class DB{
    //Pass the database information ie the password and the username of the DB as well as its localhost
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hairsalon_test", "muturia", "DontAsk12");
}