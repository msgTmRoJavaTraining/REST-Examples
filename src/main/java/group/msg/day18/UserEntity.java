package group.msg.day18;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity

public class UserEntity implements Serializable {
    @Id
    @GeneratedValue
    private int userId;

    private String userName;

    private double userSalary;

    @Override
    public String toString() {
        return "ID: " + userId + ", NAME: " + userName + ", SALARY: " + userSalary + " ron";
    }
}
