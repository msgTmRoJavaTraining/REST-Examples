package group.msg.exercise;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserBean {

    @PersistenceContext(unitName = "java.training")
    private EntityManager entityManager;

    public User insertUser(int salary, String name) {
        User user = new User();
        user.setSalary(salary);
        user.setName(name);
        entityManager.persist(user);
        return user;
    }
    public User getUserById(int id){
        User result= entityManager.find(User.class,id);
        return result;
    }
    public User updateUser(int id, String updateName){
        User result = entityManager.find(User.class,id);
        result.setName(updateName);
        entityManager.persist(result);
        return result;
    }
    public void deleteUserByID(int id){
        User result =  entityManager.find(User.class,id);
        entityManager.remove(result);

    }
    public List<User> getUsersBySalary(int salary){
        TypedQuery<User> check = entityManager.createQuery("select user from User user where user.salary = " + salary, User.class);
        List<User> resultUsers = check.getResultList();
        return resultUsers;

    }

}
