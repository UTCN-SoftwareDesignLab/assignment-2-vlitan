package main.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String name, int age, String password, Role role) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.role = role;
    }


    public User(){}

    @Override

    public String toString() {
        return "id:" + this.getId() + " " +
                "name:" + this.getName() + " " +
                "age:" + this.getAge() + " " +
                "role:" + this.getRole();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
