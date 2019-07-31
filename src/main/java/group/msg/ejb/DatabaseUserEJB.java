package group.msg.ejb;


import group.msg.entities.Dog;
import group.msg.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DatabaseUserEJB {

    @PersistenceContext(unitName = "java.training")
    private EntityManager entityManager;

    public User insertUser(String name,int salary) {

        User user = new User();
        user.setName(name);
        user.setSalary(salary);
        entityManager.persist(user);

        return user;
    }

    public void deleteUser(int id) {

        User user = entityManager.find(User.class,id);
        entityManager.remove(user);

    }

    public User findUser(int id){

        return entityManager.find(User.class,id);

    }

   public void persist(User user,String name){

        entityManager.remove(user);
        user.setName(name);
        entityManager.persist(user);
   }

   public String returnUsers(int salary){

        List<User> user = entityManager.createQuery("select user from User user where user.salary>salary ").getResultList();
        String str= " ";
        for(User u:user)
            str = str+u.getName()+u.getSalary();

        return str;
   }



}
