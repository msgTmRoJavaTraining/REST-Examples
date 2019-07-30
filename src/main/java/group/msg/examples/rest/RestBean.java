package group.msg.examples.rest;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.logging.Logger;

@Path("/rest")
public class RestBean {

  @Inject
  private Logger logger;

  @GET
  @Path("count")
  public String count() {
    return "Count something";
  }

  @GET
  @Path("user/{userId}")
  public String getPathParam(@PathParam("userId") int userId) {
    logger.info("Getting a user using a path param");
    return "user with id "+userId;
  }

  @GET
  @Path("users")
  public String getQueryParam(@QueryParam("salary") int salary) {
    logger.info("Getting using query parameters.");
    return "Found 5 programmers with the salary "+salary;
  }

  @POST
  @Path("create")
  public String insertPostRequest(@FormParam("firstName") String firstName,@FormParam("lastName") String lastName) {
    logger.info("Creating a user using the body");
    return "Created user with firstName: "+firstName +"\n and lastName: "+lastName;
  }

  @PUT
  @Path("update")
  public String updateFirstName(@FormParam("firstName") String firstName,
                       @FormParam("id") int id) {
    return "Updated the firstName for user with the id "+id;
  }
  @DELETE
  @Path("delete")
  public String delete(@QueryParam("userId") int userId) {
    return "Deleted user with id: " + userId;
  }

}
