package group.msg.ejb;

import group.msg.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserBean {

    @PersistenceContext(unitName = "java.training")
    private EntityManager entityManager;

    public User insertUser(String firstName, String lastName) {
        User user=new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);

        entityManager.persist(user);
        return user;
    }

    public User getUserById(int id){
        return entityManager.find(User.class,id);
    }

    public User updateUser(String firstName,int id){
        User user=getUserById(id);
        user.setFirstName(firstName);
        entityManager.persist(user);
        return user;
    }

    public void deleteUser(int id){
        User user=getUserById(id);
        entityManager.remove(user);
    }

    public User getUser(String firstName){
        return entityManager.find(User.class,firstName);
    }


}
