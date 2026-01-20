package com.booker.api.builders;

import com.booker.api.models.Geo;

public class GeoBuilder {
    private String lat;
    private String lng;

    public GeoBuilder() {
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
}
