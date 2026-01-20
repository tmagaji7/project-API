package com.booker.api.builders;

import com.booker.api.models.Address;
import com.booker.api.models.Company;
import com.booker.api.models.User;

public class UserBuilder {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private Address address;
    private Company company;

    public UserBuilder() {
    }

    public UserBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder website(String website) {
        this.website = website;
        return this;
    }

    public UserBuilder address(Address address) {
        this.address = address;
        return this;
    }

    public UserBuilder company(Company company) {
        this.company = company;
        return this;
    }

    public User build() {
        return new User(id, name, username, email, phone, website, address, company);
    }
}
