package group.msg.ejb;

import group.msg.entities.Dog;
import group.msg.entities.User;

import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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

    public User insertUser(String name, int age, String cnp) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setCNP(cnp);
        entityManager.persist(user);
        return user;
    }

    public String getUserName(int id) {
        User u = entityManager.find(User.class, id);
        return u.toString();
    }

    public void changeName(int id, String name) {
        User u = entityManager.find(User.class, id);
        u.setName(name);
    }


    public void deleteRecord(int userId) {
        User u = entityManager.find(User.class, userId);
        entityManager.remove(u);
    }

    public String numberOfUsers(int age) {


        TypedQuery<User> query = entityManager.createQuery("select user from User user where user.age = :inputAge", User.class);
        query.setParameter("inputAge", age);

        List<User> userList=query.getResultList();

        StringBuilder stringBuilder = new StringBuilder();
        for (User u : userList) {
            stringBuilder.append(u.toString()).append("\n");
        }
        return stringBuilder.toString()+" "+userList.size();
    }
}