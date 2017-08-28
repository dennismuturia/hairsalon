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
            String phone = request.queryParams("phone");
            int styles = Integer.parseInt(request.queryParams("style_id"));
            Customer myCustomer = new Customer(name,phone,styles, stylist.getId());
            myCustomer.save();
            model.put("stylist", stylist);
            model.put("template", "templates/stylist-customer-success.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());

          post("/stylists/:stylists_id/customers/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Customer customer = Customer.find(Integer.parseInt(request.params("id")));
            String name = request.queryParams("name");
            Stylist stylist = Stylist.find(customer.getStylistId());
            customer.update(name);
            String url = String.format("/stylists/%d/customers/%d", stylist.getId(), customer.getId());
            response.redirect(url);
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());
        //This is suppossed to show us the individual customer that is existing in a database

        get("/stylists/:stylist_id/customers/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
          Customer customer = Customer.find(Integer.parseInt(request.params(":id")));
          model.put("stylist", stylist);
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


          post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            int styles = Integer.parseInt(request.queryParams("styles_id"));
            int customers = Integer.parseInt(request.queryParams("customer_id"));
            Stylist newStylist = new Stylist(name, phone,styles,customers);
            newStylist.save();
            model.put("template", "templates/stylist-success.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());


          get("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());

          post("/stylists/:stylist_id/customers/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Customer customer = Customer.find(Integer.parseInt(request.params("id")));
            Stylist stylist = Stylist.find(customer.getStylistId());
            customer.delete();
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());


        get("/stylists/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
          model.put("stylist", stylist);
          model.put("template", "templates/stylist.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        
    }
}