package main.model.builder;

import main.model.Role;
import main.model.User;

public final class UserBuilder {
    private Integer id;
    private String name;
    private int age;
    private String password;
    private Role role;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(Integer id) {
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

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withRole(Role role) {
        this.role = role;
        return this;
    }

    public User build() {
        User user = new User(name, age, password, role);
        user.setId(id);
        return user;
    }
}
