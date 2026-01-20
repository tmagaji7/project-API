package com.booker.api.builders;

import com.booker.api.models.Company;

public class CompanyBuilder {
    private String name;
    private String catchPhrase;
    private String bs;

    public CompanyBuilder() {
    }

    public CompanyBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CompanyBuilder catchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
        return this;
    }

    public CompanyBuilder bs(String bs) {
        this.bs = bs;
        return this;
    }

    public Company build() {
        return new Company(name, catchPhrase, bs);
    }
}
