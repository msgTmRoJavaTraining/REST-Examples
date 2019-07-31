package group.msg.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Dog {
    @GeneratedValue
    @Id
    private int id;

    private String name;
}
