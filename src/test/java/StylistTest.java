import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

    //Now here we check if the Stylist instantiates correctly without any mishap
  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist myStylist = new Stylist("Daisy");
    assertEquals(true, myStylist instanceof Stylist);
  }
}