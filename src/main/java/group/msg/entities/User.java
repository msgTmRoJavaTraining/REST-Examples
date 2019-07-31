package group.msg.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @GeneratedValue
    @Id
    private int id;

    private String name;

    private int age;

    private String occupation;
}
