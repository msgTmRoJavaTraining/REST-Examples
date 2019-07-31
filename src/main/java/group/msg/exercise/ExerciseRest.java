package group.msg.exercise;


import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
public class ExerciseRest {

    @Inject
    private UserBean ub;


    @GET
    @Path("find/user/{userId}")
    public String getPathParam(@PathParam("userId") int userId) {
        User found;
        found= ub.getUserById(userId);
        return found.getName();
    }


    @POST
    @Path("create")
    public User insertPostRequest(@FormParam("salary") int salary ,@FormParam("name") String name) {
        User user = ub.insertUser(salary,name);
        return user;
    }

    @PUT
    @Path("update/user/{userId}/{userName}")
    public String updateFirstName(@PathParam("userId") int userId,@PathParam("userName") String updateName) {
        User userChanged = ub.updateUser(userId,updateName);
        return "Rename successful \n" + " current user data: " + userChanged.getUserId() + " " + userChanged.getName() + " " + userChanged.getSalary();
    }

    @DELETE
    @Path("delete/user/{userId}")
    public String delete(@PathParam("userId") int userId) {
        ub.deleteUserByID(userId);
        return "Deleted user with id: " + userId;
    }

    @GET
    @Path("getUsers")
    public String getUsers(@QueryParam("salary") int salary) {
        List<User>resultList = new ArrayList<>();
        resultList= ub.getUsersBySalary(salary);
        String sb = " ";
        for(User u:resultList){
            sb = sb + u.getUserId()+ " "+ u.getName() + " " + u.getSalary()+"\n";
        }
        return sb;
    }

}
