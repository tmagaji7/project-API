package com.booker.api.api;

import com.booker.api.utils.Routes;
import com.booker.api.models.User;
import com.booker.api.utils.RestUtils;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * User API Layer
 * Handles all User endpoint operations
 */
public class UserApi {

    private UserApi() {
    }

    @Step("Create new user")
    public static Response createUser(User user) {
        return RestUtils.post(Routes.CREATE_USER, user);
    }

    @Step("Get all users")
    public static Response getAllUsers() {
        return RestUtils.get(Routes.GET_ALL_USERS);
    }

    @Step("Get user by ID: {userId}")
    public static Response getUser(int userId) {
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("id", userId);
        return RestUtils.get(Routes.GET_USER, pathParams);
    }

    @Step("Update user ID: {userId}")
    public static Response updateUser(int userId, User user) {
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("id", userId);
        return RestUtils.put(Routes.UPDATE_USER, pathParams, user);
    }

    @Step("Delete user ID: {userId}")
    public static Response deleteUser(int userId) {
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("id", userId);
        return RestUtils.delete(Routes.DELETE_USER, pathParams);
    }
}
