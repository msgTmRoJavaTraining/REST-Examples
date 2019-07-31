package group.msg.ejb;

import group.msg.entities.Dog;
import group.msg.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public User insertUser(String name, int age, int salary){
        User user=new User();
        user.setName(name);
        user.setAge(age);
        user.setSalary(salary);
        entityManager.persist(user);
        return user;
    }
    public User getUserById(int id){
        User srcd=entityManager.find(User.class,id);
        return srcd;
    }
    public User updateUser(int id,String name){
        User srcd=entityManager.find(User.class,id);
        srcd.setName(name);
        entityManager.persist(srcd);
        return srcd;
    }
    public String deleteUser(int id){
        User srcd=entityManager.find(User.class,id);
        entityManager.remove(srcd);
        return "removed";
    }
    public List<User> salaryAbove(int minSalary){
        List<User>result;
        result=entityManager.createQuery("SELECT usr FROM User usr where usr.salary > :minim").setParameter("minim",minSalary).getResultList();
        return result;
    }
}
