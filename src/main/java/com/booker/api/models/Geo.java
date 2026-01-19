package com.booker.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;

/**
 * Geo Model (nested in Address)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Geo {
    private String lat;
    private String lng;

    public Geo() {
    }

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public static GeoBuilder builder() {
        return new GeoBuilder();
    }

    public static class GeoBuilder {
        private String lat;
        private String lng;

        GeoBuilder() {
        }

        public GeoBuilder lat(String lat) {
            this.lat = lat;
            return this;
        }

        public GeoBuilder lng(String lng) {
            this.lng = lng;
            return this;
        }

        public Geo build() {
            return new Geo(lat, lng);
        }

        @Override
        public String toString() {
            return "Geo.GeoBuilder(lat=" + this.lat + ", lng=" + this.lng + ")";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Geo geo = (Geo) o;
        return Objects.equals(lat, geo.lat) && Objects.equals(lng, geo.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }

    @Override
    public String toString() {
        return "Geo(lat=" + this.getLat() + ", lng=" + this.getLng() + ")";
    }
}
