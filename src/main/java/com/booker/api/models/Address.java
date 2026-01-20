package com.booker.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;

/**
 * Address Model (nested in User)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    public Address() {
    }

    public Address(String street, String suite, String city, String zipcode, Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(suite, address.suite)
                && Objects.equals(city, address.city) && Objects.equals(zipcode, address.zipcode)
                && Objects.equals(geo, address.geo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, suite, city, zipcode, geo);
    }

    @Override
    public String toString() {
        return "Address(street=" + this.getStreet() + ", suite=" + this.getSuite() + ", city=" + this.getCity()
                + ", zipcode=" + this.getZipcode() + ", geo=" + this.getGeo() + ")";
    }
}
