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

        //This is a new route for customers
        get("/customers", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("customers", Customer.all());
            model.put("template", "templates/customers.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());
          //This will render the form that assigns a customer to a Stylist
          get("stylist/:id/customer/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist-customer-form.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());
          //This is a method for posting data from the customers section
          post("/customers", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
            String name = request.queryParams("name");
            Customer myCustomer = new Customer(name,"09867687",78678, stylist.getId());
            myCustomer.save();
            model.put("stylist", stylist);
            model.put("template", "templates/stylist-customer-success.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());
        //This is suppossed to show us the individual customer that is existing in a database
          get("/customers/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Customer customer = Customer.find(Integer.parseInt(request.params(":id")));
            model.put("customer", customer);
            model.put("template", "templates/customer.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());

          //This renders all of our stylists
          get("/stylists/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/stylist-form.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());
        
    }
}