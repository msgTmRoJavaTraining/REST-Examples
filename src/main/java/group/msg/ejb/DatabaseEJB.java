package group.msg.ejb;

import group.msg.entities.Dog;
import group.msg.entities.User;

import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    public User insertUser(String name,int age,String cnp)
    {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setCNP(cnp);
        entityManager.persist(user);
        return user;
    }

    public String getUserName(int id)
    {
        User u=entityManager.find(User.class,id);
        return u.toString();
    }

    public void changeName(int id,String name)
    {
        User u=entityManager.find(User.class,id);
        u.setName(name);
    }


    public void deteleRecord(int userId)
    {
        User u=entityManager.find(User.class,userId);
        entityManager.remove(u);
    }
}
