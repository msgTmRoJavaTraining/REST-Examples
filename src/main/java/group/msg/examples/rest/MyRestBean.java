package group.msg.examples.rest;


import group.msg.ejb.DatabaseEJB;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.logging.Logger;

@Path("/myrest")
public class MyRestBean
{

    @Inject
    private Logger logger;

    @Inject
    private DatabaseEJB databaseEJB;


    @GET
    @Path("myCount")
    public String count() {
        return "Count something for me now!";
    }

    @POST
    @Path("createuser")
    public String insertPostRequest(@FormParam("name") String name, @FormParam("age") int age, @FormParam("cnp") String cnp)
    {
        logger.info("Creating a user using the body");
        databaseEJB.insertUser(name,age,cnp);
        return "Created user with the name: " + name+" age: "+age+" CNP:"+cnp;
    }

    @GET
    @Path("thisUser/{userId}")
    public String getPathParam(@PathParam("userId") int userId) {
        logger.info("Getting a user using a path param");
        return "user with id " + databaseEJB.getUserName(userId);
    }

    @PUT
    @Path("update")
    public String updateName(@FormParam("name") String name,@FormParam("userId") int userId)
    {
        databaseEJB.changeName(userId,name);
        return "update done successfully";
    }


    @DELETE
    @Path("delete")
    public String delete(@QueryParam("userId") int userId)
    {
        databaseEJB.deleteRecord(userId);
        return "Deleted user with id: " + userId;
    }


    @GET
    @Path("users")
    public String getQueryParam(@QueryParam("age") int age) {

        return databaseEJB.numberOfUsers(age);
    }
}
