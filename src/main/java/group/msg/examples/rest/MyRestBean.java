package group.msg.examples.rest;

import group.msg.ejb.UserBean;
import group.msg.entities.User;
import org.glassfish.config.support.Delete;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.logging.Logger;

@Path("/myRest")
public class MyRestBean {

    @Inject
    private Logger logger;

    @Inject
    private UserBean userBean;

    @POST
    @Path("createUser")
    public String insertPostRequest(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
        logger.info("Creating a user using the body");
        userBean.insertUser(firstName, lastName);
        return "Created user with firstName: " + firstName + "\n and lastName: " + lastName ;
    }

    @GET
    @Path("user/{id}")
    public User getPathParam(@PathParam("id") int id) {
        logger.info("Getting a user using a path param");
        return userBean.getUserById(id);
    }

    @PUT
    @Path("update/{id}")
    public User updateFirstName(@FormParam("firstName") String firstName, @PathParam("id") int id) {
        return userBean.updateUser(firstName,id);
    }

    @DELETE
    @Path("delete")
    public String delete(@QueryParam("id") int id) {
        userBean.deleteUser(id);
        return "Deleted user with id: " + id;
    }

    @GET
    @Path("getUsers")
    public User getQueryParam(@QueryParam("firstName") String firstName) {
        return userBean.getUser(firstName);
    }
}
