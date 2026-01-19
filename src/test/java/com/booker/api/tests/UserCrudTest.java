package com.booker.api.tests;

import com.booker.api.api.UserApi;
import com.booker.api.base.BaseTest;
import com.booker.api.models.User;
import com.booker.api.utils.TestDataFactory;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * User CRUD Tests
 * Tests with complex nested objects (Address, Geo, Company)
 */
@Epic("Users API")
@Feature("User CRUD Operations")
public class UserCrudTest extends BaseTest {

    private User testUser;
    private int createdUserId = 11; // JSONPlaceholder returns 11 for new users

    @BeforeClass(alwaysRun = true)
    public void setupTestData() {
        testUser = TestDataFactory.createRandomUser();
    }

    @Test(priority = 1, groups = { "smoke", "user" })
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create a new user with nested Address and Company")
    @Story("Create User")
    public void testCreateUser() {
        Response response = UserApi.createUser(testUser);

        Assert.assertEquals(response.getStatusCode(), 201, "Status should be 201 Created");
        Assert.assertEquals(response.jsonPath().getInt("id"), createdUserId);
        Assert.assertEquals(response.jsonPath().getString("name"), testUser.getName());
        Assert.assertEquals(response.jsonPath().getString("email"), testUser.getEmail());

        // Verify nested objects
        Assert.assertEquals(response.jsonPath().getString("address.city"), testUser.getAddress().getCity());
        Assert.assertEquals(response.jsonPath().getString("company.name"), testUser.getCompany().getName());

        System.out.println("✓ Created user: " + testUser.getName() + " with ID: " + createdUserId);
    }

    @Test(priority = 2, groups = { "smoke", "user" }, dependsOnMethods = "testCreateUser")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get an existing user by ID with all nested data")
    @Story("Get User")
    public void testGetUser() {
        Response response = UserApi.getUser(1);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
        Assert.assertNotNull(response.jsonPath().getString("name"));
        Assert.assertNotNull(response.jsonPath().getString("email"));

        // Verify nested Address and Geo
        Assert.assertNotNull(response.jsonPath().getString("address.street"));
        Assert.assertNotNull(response.jsonPath().getString("address.city"));
        Assert.assertNotNull(response.jsonPath().getString("address.geo.lat"));
        Assert.assertNotNull(response.jsonPath().getString("address.geo.lng"));

        // Verify nested Company
        Assert.assertNotNull(response.jsonPath().getString("company.name"));

        System.out.println("✓ Retrieved user: " + response.jsonPath().getString("name") +
                " from " + response.jsonPath().getString("address.city"));
    }

    @Test(priority = 3, groups = { "regression", "user" })
    @Severity(SeverityLevel.NORMAL)
    @Description("Get all users")
    @Story("Get All Users")
    public void testGetAllUsers() {
        Response response = UserApi.getAllUsers();

        Assert.assertEquals(response.getStatusCode(), 200);
        int userCount = response.jsonPath().getList("$").size();
        Assert.assertEquals(userCount, 10, "Should return 10 users");

        System.out.println("✓ Retrieved " + userCount + " users");
    }

    @Test(priority = 4, groups = { "smoke", "user" })
    @Severity(SeverityLevel.CRITICAL)
    @Description("Update an existing user")
    @Story("Update User")
    public void testUpdateUser() {
        User updatedUser = TestDataFactory.createRandomUser();
        updatedUser.setName("Updated User Name");

        Response response = UserApi.updateUser(1, updatedUser);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "Updated User Name");

        System.out.println("✓ Updated user to: " + response.jsonPath().getString("name"));
    }

    @Test(priority = 5, groups = { "smoke", "user" })
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete an existing user")
    @Story("Delete User")
    public void testDeleteUser() {
        Response response = UserApi.deleteUser(1);

        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.println("✓ Deleted user successfully");
    }
}
