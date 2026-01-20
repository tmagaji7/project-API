package com.booker.api.tests;

import com.booker.api.api.UserApi;
import com.booker.api.base.BaseTest;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.booker.api.assertions.ResponseAssert.assertThat;

/**
 * User Negative Tests
 * Tests for error scenarios
 */
@Epic("Users API")
@Feature("User Negative Scenarios")
public class UserNegativeTest extends BaseTest {

    @Test(groups = { "regression", "negative" })
    @Severity(SeverityLevel.MINOR)
    @Description("Get user with invalid ID")
    @Story("Invalid User ID")
    public void testGetUserInvalidId() {
        Response response = UserApi.getUser(99999);

        assertThat(response).statusCodeIs(404);

        System.out.println("✓ Correctly returned 404 for invalid user ID");
    }

    @Test(groups = { "regression", "negative" })
    @Severity(SeverityLevel.MINOR)
    @Description("Delete non-existent user")
    @Story("Delete Invalid User")
    public void testDeleteNonExistentUser() {
        Response response = UserApi.deleteUser(99999);

        // JSONPlaceholder may return 200 even for non-existent
        Assert.assertTrue(
                response.getStatusCode() == 200 || response.getStatusCode() == 404,
                "Should return 200 or 404");

        System.out.println("✓ Delete non-existent user returned: " + response.getStatusCode());
    }
}
