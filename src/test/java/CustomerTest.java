import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class CustomerTest{
    @Before
    public void setUp(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hairsalon_test", null, null);
    }
}