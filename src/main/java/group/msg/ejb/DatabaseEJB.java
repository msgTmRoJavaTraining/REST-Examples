package group.msg.ejb;

import group.msg.entities.Dog;
import group.msg.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class DatabaseEJB {

    @PersistenceContext(unitName = "java.training")
    private EntityManager entityManager;

    public Dog insertDog(String name) {
        Dog dog = new Dog();
        dog.setName(name);
        entityManager.persist(dog);
        return dog;
    }

    public User insertUser(String name, int age, String occupation) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setOccupation(occupation);
        entityManager.persist(user);
        return user;
    }

    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    public User updateUserName(int userId, String newName) {
        User user = getUserById(userId);
        user.setName(newName);
        entityManager.persist(user);
        return user;
    }

    public boolean deleteUser(int userId) {
        User user = getUserById(userId);
        if (user == null)
            return false;
        else {
            entityManager.remove(user);
        }

        User userAfterDelete = getUserById(userId);
        if (userAfterDelete == null)
            return true;
        else return false;
    }

    public Object[] getUsersByAge(int age) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.age= ?1", User.class);
        query.setParameter(1, age);
        List<User> result = query.getResultList();
        return result.toArray();
    }
}
