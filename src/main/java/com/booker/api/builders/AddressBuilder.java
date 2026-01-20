package com.booker.api.builders;

import com.booker.api.models.Address;
import com.booker.api.models.Geo;

public class AddressBuilder {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    public AddressBuilder() {
    }

    public AddressBuilder street(String street) {
        this.street = street;
        return this;
    }

    public AddressBuilder suite(String suite) {
        this.suite = suite;
        return this;
    }

    public AddressBuilder city(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder zipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public AddressBuilder geo(Geo geo) {
        this.geo = geo;
        return this;
    }

    public Address build() {
        return new Address(street, suite, city, zipcode, geo);
    }
}
