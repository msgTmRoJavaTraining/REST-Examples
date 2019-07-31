package group.msg.day18;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.*;
import java.util.List;

public class UserBeanService {
    @PersistenceContext(unitName = "java.training")
    protected EntityManager em;

    @Inject
    protected UserTransaction utx;

    public String getAllUsersFromDB() {
        TypedQuery<UserEntity> query = em.createQuery("select user from UserEntity user", UserEntity.class);

        List<UserEntity> resultList = query.getResultList();

        StringBuilder stringBuilder = new StringBuilder();
        for (UserEntity u : resultList) {
            stringBuilder.append(u.toString());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public UserModel getUserById(int userId) {
        UserEntity lookedForUser = em.find(UserEntity.class, userId);

        UserModel returnedLookedForUser = new UserModel(lookedForUser.getUserName(), lookedForUser.getUserSalary());

        return returnedLookedForUser;
    }

    public String createNewUser(String userName, double userSalary) {
        try {
            utx.begin();
        } catch (NotSupportedException | SystemException e) {
            e.printStackTrace();
        }
        em.joinTransaction();

        UserEntity newUser = new UserEntity();
        newUser.setUserName(userName);
        newUser.setUserSalary(userSalary);

        em.persist(newUser);
        try {
            utx.commit();
            return "Created user: " + userName + ", with salary: " + userSalary;
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SystemException e) {
            e.printStackTrace();
        }

        return "Could not create new user!";
    }

    public UserEntity updateUserSalary(int userId, double newUserSalary) {
        try {
            utx.begin();

            em.joinTransaction();
            UserEntity toBeUpdatedUser = em.find(UserEntity.class, userId);

            toBeUpdatedUser.setUserSalary(newUserSalary);

            utx.commit();

            return toBeUpdatedUser;
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String deleteUserById(int givenUserId) {
        try {
            utx.begin();
            UserEntity toBeDeletedUser = em.find(UserEntity.class, givenUserId);

            em.remove(toBeDeletedUser);

            utx.commit();
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
            e.printStackTrace();
        }

        return "Deleted!";
    }

    public String getAllUserWithTheSameSalary(double givenSalary) {
        TypedQuery<UserEntity> query = em.createQuery("select user from UserEntity user where user.userSalary = :inputSalary", UserEntity.class);
        query.setParameter("inputSalary", givenSalary);

        List<UserEntity> resultList = query.getResultList();

        StringBuilder stringBuilder = new StringBuilder();
        for (UserEntity u : resultList) {
            stringBuilder.append(u.toString());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
