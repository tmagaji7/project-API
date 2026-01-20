package com.booker.api.assertions;

import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseAssert {
    private final Response response;

    private ResponseAssert(Response response) {
        this.response = response;
    }

    public static ResponseAssert assertThat(Response response) {
        return new ResponseAssert(response);
    }

    public ResponseAssert statusCodeIs(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Status code should be " + expectedStatusCode);
        return this;
    }

    public ResponseAssert bodyContains(String path, Object expectedValue) {
        Object actualValue = response.jsonPath().get(path);
        Assert.assertEquals(actualValue, expectedValue,
                "Body field '" + path + "' mismatch");
        return this;
    }

    public ResponseAssert hasField(String path) {
        Assert.assertNotNull(response.jsonPath().get(path),
                "Field '" + path + "' should not be null");
        return this;
    }
}
