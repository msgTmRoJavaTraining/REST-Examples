package group.msg.examples.rest;

import group.msg.ejb.DatabaseEJB;
import group.msg.ejb.DatabaseUserEJB;
import group.msg.entities.User;
import org.glassfish.config.support.Delete;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.logging.Logger;

@Path("/rest")
public class RestBean {

    @Inject
    private Logger logger;

    @Inject
    private DatabaseUserEJB databaseUserEJB;



    @POST
    @Path("create")
    public String createNewDog(@QueryParam("name")String name,@QueryParam("Salary")int salary) {
        databaseUserEJB.insertUser(name,salary);
        return "Success!";
    }

    @GET
    @Path("getId")
    public String getUserbyId(@QueryParam("id")int id) {
        User user = databaseUserEJB.findUser(id);
        return "User " + user;
    }

    @PUT
    @Path("update")
    public String updateUserName(@FormParam("name")String name,String replacebleName,@FormParam("id")int id){

        User user = databaseUserEJB.findUser(id);
        user.setName(replacebleName);
        return "updated";
    }

    @DELETE
    @Path("deleteRecord")
    public String deleteById(@QueryParam("id")int id){


        databaseUserEJB.deleteUser(id);

        return "succes";
    }

    @GET
    @Path("display")
    public String returnUsersAbove(@QueryParam("salary")int salary){

        return  databaseUserEJB.returnUsers(2000);

    }

}
