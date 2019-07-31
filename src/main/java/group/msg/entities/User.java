package group.msg.entities;


import lombok.Getter;
import lombok.Setter;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
public class User implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;


    public User(){}

}
