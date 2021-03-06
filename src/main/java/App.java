import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    //homepage
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //add stylist form
    get("/stylists/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/stylist-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //add stylist
    post("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = new Stylist(request.queryParams("name"));
      stylist.save();
      model.put("stylists", stylist);
      model.put("template", "templates/stylist-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //view all stylists
    get("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //view stylist
    get("/stylists/:stylist_id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
      model.put("stylists", stylist);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //add new client form
    get("/stylists/:stylist_id/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
      model.put("stylist", stylist);
      model.put("template", "templates/client-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //add client
    post("/clients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylist_id")));
      String name = request.queryParams("name");
      String address = request.queryParams("address");
      String phone = request.queryParams("phone");
      Client newClient = new Client(name, address, phone, stylist.getId());
      newClient.save();
      model.put("stylists", stylist);
      model.put("template", "templates/client-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //list of all clients
    get("/clients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      model.put("template", "templates/clients.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //update sylist name
    post("/stylists/:stylist_id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
      stylist.update(request.queryParams("name"));
      model.put("stylists", stylist);
      String url = String.format("/stylists/%d", stylist.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //delete stylist
    post("/stylists/:stylist_id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params("stylist_id")));
      stylist.delete();
      String url = String.format("/stylists");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //client detail
    get("/stylists/:stylist_id/clients/:client_id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
      model.put("stylist", stylist);
      Client client = Client.find(Integer.parseInt(request.params(":client_id")));
      model.put("client", client);
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //update client
    post("/stylists/:stylists_id/clients/:client_id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":client_id")));
      Stylist stylist = Stylist.find(client.getStylistId());
      client.updateName(request.queryParams("name"));
      client.updateAddress(request.queryParams("address"));
      client.updatePhone(request.queryParams("phone"));
      model.put("client", client);
      String url = String.format("/stylists/%d/clients/%d", stylist.getId(), client.getId() );
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //delete client
    post("/clients/:client_id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params("client_id")));
      client.delete();
      String url = String.format("/stylists");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
