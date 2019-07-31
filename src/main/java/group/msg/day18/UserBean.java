package group.msg.day18;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.logging.Logger;

@Path("/day18")
public class UserBean {
    @Inject
    private Logger logger;

    @Inject
    private UserBeanService userBeanService;

    @GET
    @Path("getUsers")
    public String getUsers() {
        return userBeanService.getAllUsersFromDB();
    }

    @POST
    @Path("createUser")
    public String createNewUserRequest(@FormParam("userName") String userName, @FormParam("userSalary") double userSalary) {
        logger.info("Attempting to create a new user...");
        return userBeanService.createNewUser(userName, userSalary);
    }

    @GET
    @Path("getUserById")
    public UserModel getUserById(@QueryParam("userId") int userId) {
        return userBeanService.getUserById(userId);
    }

    @PUT
    @Path("updateUser")
    public UserEntity updateUserSalaryById(@FormParam("newUserSalary") double newSalary,
                                           @FormParam("userId") int userId) {
        return userBeanService.updateUserSalary(userId, newSalary);
    }

    @DELETE
    @Path("deleteUserById")
    public String deleteUser(@QueryParam("userId") int userId) {
        return userBeanService.deleteUserById(userId);
    }

    @GET
    @Path("getAllUsersHavingSameSalary")
    public String getQueryParam(@QueryParam("userSalary") double userSalary) {
        return userBeanService.getAllUserWithTheSameSalary(userSalary);
    }
}
