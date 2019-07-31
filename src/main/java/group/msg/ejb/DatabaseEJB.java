package group.msg.ejb;

import group.msg.entities.Dog;

import javax.ejb.Stateless;
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
}
