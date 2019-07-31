package group.msg.examples.rest;

import group.msg.ejb.DatabaseEJB;
import group.msg.entities.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.logging.Logger;

@Path("/pers")
public class PersonBean {
    @Inject
    private Logger logger;

    @Inject
    private DatabaseEJB databaseEJB;

    @POST
    @Path("create")
    public String insertPostRequest(@FormParam("name") String name,
                                    @FormParam("age") int age,
                                    @FormParam("salary") int salary) {
        logger.info("Creating a user");
        databaseEJB.insertUser(name, age, salary);
        return "Created user: " + name + "\n\t" +
                +age + "\n\t" +
                +salary;
    }

    @GET
    @Path("user/{userId}")
    public User getPathParam(@PathParam("userId") int userId) {
        logger.info("Getting a user using a path param");
        User sr = databaseEJB.getUserById(userId);
        return sr;
    }

    @PUT
    @Path("update")
    public User updateFirstName(@FormParam("userId") int userId,
                                @FormParam("name") String name) {
        User modif = databaseEJB.updateUser(userId, name);
        return modif;
    }

    @DELETE
    @Path("delete")
    public String delete(@QueryParam("userId") int userId) {
        databaseEJB.deleteUser(userId);
        return "Deleted user with id: " + userId;
    }
    @GET
    @Path("salaryAbove")
    public List<User> getSalaryAbove(@QueryParam("salary") int salary) {
        //logger.info("Getting using query parameters.");
       List<User> grater = databaseEJB.salaryAbove(salary);
        return grater;
    }


}