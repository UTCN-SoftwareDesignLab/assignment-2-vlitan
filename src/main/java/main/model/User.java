package main.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NonNull
    private Integer id;
    @Column
    @NonNull
    private String name;
    @Column
    @Min(value = 14, message = "Age is below minium permited")
    private int age;
    @Column
    @Enumerated(EnumType.STRING)
    @NonNull
    private Role role;

    @Override
    public String toString() {
        return "id:" + this.getId() + " " +
                "name:" + this.getName() + " " +
                "age:" + this.getAge() + " " +
                "role:" + this.getRole();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
