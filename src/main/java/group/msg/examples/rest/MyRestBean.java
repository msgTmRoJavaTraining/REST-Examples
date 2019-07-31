package group.msg.examples.rest;

import group.msg.ejb.DatabaseEJB;
import group.msg.entities.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.logging.Logger;

@Path("/razvan")
public class MyRestBean {

    @Inject
    private Logger logger;

    @Inject
    private DatabaseEJB databaseEJB;

    @GET
    @Path("hello")
    public String count() {
        return "Hello!";
    }

    @POST
    @Path("createUser")
    public String createNewUser(@FormParam("name") String name,
                                @FormParam("age") int age,
                                @FormParam("occupation") String occupation) {
        databaseEJB.insertUser(name, age, occupation);
        return "Success!";
    }

    @GET
    @Path("getUser/{userId}")
    public User getUserById(@PathParam("userId") int userId) {
        return databaseEJB.getUserById(userId); //returning a JSON
    }

    @PUT
    @Path("updateName")
    public User updateName(@FormParam("userId") int userId,
                           @FormParam("newName") String newName) {
        return databaseEJB.updateUserName(userId, newName);
    }

    @DELETE
    @Path("deleteUser")
    public String deleteUser(@QueryParam("userId") int userId) {
        if (databaseEJB.deleteUser(userId))
            return "Done";
        else return "Fail";
    }

    @GET
    @Path("age/{age}")
    public Object[] getUsersByAge(@PathParam("age") int age) {
        return databaseEJB.getUsersByAge(age);
    }
}
