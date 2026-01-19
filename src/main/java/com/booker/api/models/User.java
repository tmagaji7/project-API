package com.booker.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;

/**
 * User Model without Lombok
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private Address address;
    private Company company;

    public User() {
    }

    public User(Integer id, String name, String username, String email, String phone, String website, Address address,
            Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.address = address;
        this.company = company;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Integer id;
        private String name;
        private String username;
        private String email;
        private String phone;
        private String website;
        private Address address;
        private Company company;

        UserBuilder() {
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

        public String toString() {
            return "User.UserBuilder(id=" + this.id + ", name=" + this.name + ", username=" + this.username + ", email="
                    + this.email + ", phone=" + this.phone + ", website=" + this.website + ", address=" + this.address
                    + ", company=" + this.company + ")";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(username, user.username)
                && Objects.equals(email, user.email) && Objects.equals(phone, user.phone)
                && Objects.equals(website, user.website) && Objects.equals(address, user.address)
                && Objects.equals(company, user.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, email, phone, website, address, company);
    }

    @Override
    public String toString() {
        return "User(id=" + this.getId() + ", name=" + this.getName() + ", username=" + this.getUsername() + ", email="
                + this.getEmail() + ", phone=" + this.getPhone() + ", website=" + this.getWebsite() + ", address="
                + this.getAddress() + ", company=" + this.getCompany() + ")";
    }
}
