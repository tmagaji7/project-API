package com.booker.api.tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.booker.api.models.User;
import com.booker.api.utils.TestDataFactory;
import com.booker.api.api.UserApi;
import com.booker.api.base.BaseTest;

import static com.booker.api.assertions.ResponseAssert.assertThat;

/**
 * User CRUD Tests
 * Tests with complex nested objects (Address, Geo, Company)
 */
@Epic("Users API")
@Feature("User CRUD Operations")
public class UserCrudTest extends BaseTest {

    private User testUser;
    private int createdUserId = 11; // JSONPlaceholder always returns 11 for new users

    @BeforeClass(alwaysRun = true)
    public void setupTestData() {
        testUser = TestDataFactory.createRandomUser();
    }

    @Test(priority = 1, groups = { "smoke" })
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create a new user with nested Address and Company")
    @Story("Create User")
    public void testCreateUser() {
        Response response = UserApi.createUser(testUser);

        assertThat(response)
                .statusCodeIs(201)
                .bodyContains("id", createdUserId)
                .bodyContains("name", testUser.getName())
                .bodyContains("email", testUser.getEmail())
                .bodyContains("address.city", testUser.getAddress().getCity())
                .bodyContains("company.name", testUser.getCompany().getName());

        System.out.println("✓ Created user: " + testUser.getName() + " with ID: " + createdUserId);
    }

    // Depends on creation to ensure API is healthy, but fetches ID 1 because
    // Creation doesn't persist in JSONPlaceholder
    @Test(priority = 2, groups = { "smoke" }, dependsOnMethods = "testCreateUser")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get an existing user by ID with all nested data")
    @Story("Get User")
    public void testGetUser() {
        Response response = UserApi.getUser(1); // Fetching ID 1 as ID 11 doesn't exist on server

        assertThat(response)
                .statusCodeIs(200)
                .bodyContains("id", 1)
                .hasField("name")
                .hasField("email")
                .hasField("address.street")
                .hasField("address.city")
                .hasField("address.geo.lat")
                .hasField("address.geo.lng")
                .hasField("company.name");

        System.out.println("✓ Retrieved user: " + response.jsonPath().getString("name") +
                " from " + response.jsonPath().getString("address.city"));
    }

    @Test(priority = 3, groups = { "regression" })
    @Severity(SeverityLevel.NORMAL)
    @Description("Get all users")
    @Story("Get All Users")
    public void testGetAllUsers() {
        Response response = UserApi.getAllUsers();

        assertThat(response).statusCodeIs(200);
        int userCount = response.jsonPath().getList("$").size();
        Assert.assertEquals(userCount, 10, "Should return 10 users");

        System.out.println("✓ Retrieved " + userCount + " users");
    }

    // Depends on Get to ensure we can read before we write
    @Test(priority = 4, groups = { "smoke" }, dependsOnMethods = "testGetUser")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Update an existing user")
    @Story("Update User")
    public void testUpdateUser() {
        User updatedUser = TestDataFactory.createRandomUser();
        updatedUser.setName("Updated User Name");

        Response response = UserApi.updateUser(1, updatedUser);

        assertThat(response)
                .statusCodeIs(200)
                .bodyContains("name", "Updated User Name");

        System.out.println("✓ Updated user to: " + response.jsonPath().getString("name"));
    }

    // Depends on Update to complete the lifecycle
    @Test(priority = 5, groups = { "smoke" }, dependsOnMethods = "testUpdateUser")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete an existing user")
    @Story("Delete User")
    public void testDeleteUser() {
        Response response = UserApi.deleteUser(1);

        assertThat(response).statusCodeIs(200);

        System.out.println("✓ Deleted user successfully");
    }
}
