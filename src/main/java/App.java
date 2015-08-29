import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    /* Index */
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /* Index --> List of Stylists */
    get("/stylists", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /* Index --> Add a new Stylist */
    get("/stylists/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Stylist> stylists = Stylist.all();
      model.put("stylists", stylists);
      model.put("template", "templates/stylist-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /* Add a new Stylist --> POST new stylist */
    post("/stylists", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String theirName = request.queryParams("name");
      String theirNumber = request.queryParams("number");
      Stylist stylist = new Stylist(theirName, theirNumber);
      stylist.save();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /* List of Stylists --> View a Stylist and their clients */
    get("/stylists/:id", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Stylist stylist = Stylist.find(id);
      model.put("stylist", stylist);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
