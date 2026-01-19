package com.booker.api.utils;

import com.booker.api.config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Factory for creating Request Specifications
 * Singleton pattern ensures consistent configuration
 */
public class RequestSpecFactory {

    private static RequestSpecification requestSpec;

    private RequestSpecFactory() {
    }

    public static RequestSpecification getRequestSpec() {
        if (requestSpec == null) {
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(ConfigManager.get("base.url"))
                    .setContentType(ContentType.JSON)
                    .setAccept(ContentType.JSON)
                    .build();
        }
        return requestSpec;
    }

    /**
     * Reset request spec (useful for tests that need different config)
     */
    public static void reset() {
        requestSpec = null;
    }
}
