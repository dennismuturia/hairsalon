//Making sure that i have all the  imports that i require in this file
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App{
    //this is the method or the main method where the templates will be rendered at
     public static void main(String[] args) {
         //Initialize the variables that i will use
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";
    //This is the index homepage or the home directory
        get("/", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          model.put("template", "templates/index.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        
    }
}