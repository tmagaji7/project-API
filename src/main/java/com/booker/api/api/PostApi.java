package com.booker.api.api;

import com.booker.api.utils.Routes;
import com.booker.api.models.Post;
import com.booker.api.utils.RestUtils;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Post API Layer
 * Handles all Post endpoint operations
 */
public class PostApi {

    private PostApi() {
    }

    @Step("Create new post")
    public static Response createPost(Post post) {
        return RestUtils.post(Routes.CREATE_POST, post);
    }

    @Step("Get all posts")
    public static Response getAllPosts() {
        return RestUtils.get(Routes.GET_ALL_POSTS);
    }

    @Step("Get post by ID: {postId}")
    public static Response getPost(int postId) {
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("id", postId);
        return RestUtils.get(Routes.GET_POST, pathParams);
    }

    @Step("Update post ID: {postId}")
    public static Response updatePost(int postId, Post post) {
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("id", postId);
        return RestUtils.put(Routes.UPDATE_POST, pathParams, post);
    }

    @Step("Delete post ID: {postId}")
    public static Response deletePost(int postId) {
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("id", postId);
        return RestUtils.delete(Routes.DELETE_POST, pathParams);
    }

    @Step("Get comments for post ID: {postId}")
    public static Response getPostComments(int postId) {
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("id", postId);
        return RestUtils.get(Routes.GET_POST_COMMENTS, pathParams);
    }
}
