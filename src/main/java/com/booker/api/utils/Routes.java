package com.booker.api.utils;

/**
 * API Route Constants
 * Centralizes all endpoint definitions
 */
public final class Routes {

    private Routes() {
    }

    // ============ Posts API ============
    private static final String POSTS = "/posts";

    public static final String CREATE_POST = POSTS;
    public static final String GET_ALL_POSTS = POSTS;
    public static final String GET_POST = POSTS + "/{id}";
    public static final String UPDATE_POST = POSTS + "/{id}";
    public static final String DELETE_POST = POSTS + "/{id}";
    public static final String GET_POST_COMMENTS = POSTS + "/{id}/comments";

    // ============ Users API ============
    private static final String USERS = "/users";

    public static final String CREATE_USER = USERS;
    public static final String GET_ALL_USERS = USERS;
    public static final String GET_USER = USERS + "/{id}";
    public static final String UPDATE_USER = USERS + "/{id}";
    public static final String DELETE_USER = USERS + "/{id}";

    // ============ Comments API ============
    private static final String COMMENTS = "/comments";

    public static final String GET_ALL_COMMENTS = COMMENTS;
    public static final String GET_COMMENT = COMMENTS + "/{id}";

    // ============ Todos API ============
    private static final String TODOS = "/todos";

    public static final String GET_ALL_TODOS = TODOS;
    public static final String GET_TODO = TODOS + "/{id}";
}
