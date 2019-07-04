package group.msg.examples.rest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
  @Path("insert/{numberOfEntries}")
  public String insertPathParam(@PathParam("numberOfEntries") int numberOfEntries) {
    logger.info("Adding entries using path parameters.");
    return "Added " + numberOfEntries + " entries";
  }

  @GET
  @Path("insertQuery")
  public String insertQueryParam(@QueryParam("numberOfEntries") int numberOfEntries) {
    logger.info("Adding entries using query parameters.");
    return "Added " + numberOfEntries + " entries";
  }

  @POST
  @Path("insertForm")
  public String insertPostRequest(@FormParam("numberOfEntries") int numberOfEntries) {
    logger.info("Adding entries using post request.");
    return "Added " + numberOfEntries + " entries";
  }

  @POST
  @Path("insertCookie")
  public String insertCookieRequest(@CookieParam("numberOfEntries") int numberOfEntries) {
    logger.info("Adding entries using cookies request.");
    return "Added " + numberOfEntries + " entries";
  }

  @DELETE
  @Path("delete")
  public String delete(@QueryParam("entryId") int entryId) {
    return "Deleted entry with id: " + entryId;
  }

}
