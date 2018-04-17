package main.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    @Enumerated(EnumType.STRING)
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
