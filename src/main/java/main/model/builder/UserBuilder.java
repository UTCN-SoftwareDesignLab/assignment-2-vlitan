package main.model.builder;

import main.model.Role;
import main.model.User;

public final class UserBuilder {
    private int id;
    private String name;
    private int age;
    private Role role;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    public UserBuilder withRole(Role role) {
        this.role = role;
        return this;
    }

    public UserBuilder but() {
        return anUser().withId(id).withName(name).withAge(age).withRole(role);
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        user.setRole(role);
        return user;
    }
}
