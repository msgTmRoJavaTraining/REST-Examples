package group.msg.exercise;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @GeneratedValue
    @Id
    private int userId;

    private int salary;
    private String name;


}
