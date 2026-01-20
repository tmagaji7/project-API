package com.booker.api.tests;

import com.booker.api.builders.PostBuilder;
import com.booker.api.api.PostApi;
import com.booker.api.base.BaseTest;
import com.booker.api.models.Post;
import com.booker.api.utils.TestDataFactory;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.booker.api.assertions.ResponseAssert.assertThat;

/**
 * Post CRUD Tests
 * Tests for Create, Read, Update, Delete operations on Posts
 */
@Epic("Posts API")
@Feature("Post CRUD Operations")
public class PostCrudTest extends BaseTest {

    private Post testPost;
    private int createdPostId = 101; // JSONPlaceholder returns 101 for new posts

    @BeforeClass(alwaysRun = true)
    public void setupTestData() {
        testPost = TestDataFactory.createRandomPost();
    }

    @Test(priority = 1, groups = { "smoke" })
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create a new post with valid data")
    @Story("Create Post")
    public void testCreatePost() {
        Response response = PostApi.createPost(testPost);

        assertThat(response)
                .statusCodeIs(201)
                .bodyContains("id", createdPostId)
                .bodyContains("title", testPost.getTitle());

        System.out.println("✓ Created post with ID: " + createdPostId);
    }

    @Test(priority = 2, groups = { "smoke" }, dependsOnMethods = "testCreatePost")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get an existing post by ID")
    @Story("Get Post")
    public void testGetPost() {
        Response response = PostApi.getPost(1);

        assertThat(response)
                .statusCodeIs(200)
                .bodyContains("id", 1)
                .hasField("title")
                .hasField("body");

        System.out.println("✓ Retrieved post: " + response.jsonPath().getString("title"));
    }

    @Test(priority = 3, groups = { "regression" })
    @Severity(SeverityLevel.NORMAL)
    @Description("Get all posts")
    @Story("Get All Posts")
    public void testGetAllPosts() {
        Response response = PostApi.getAllPosts();

        assertThat(response).statusCodeIs(200);
        int postCount = response.jsonPath().getList("$").size();
        Assert.assertEquals(postCount, 100, "Should return 100 posts");

        System.out.println("✓ Retrieved " + postCount + " posts");
    }

    @Test(priority = 4, groups = { "smoke" })
    @Severity(SeverityLevel.CRITICAL)
    @Description("Update an existing post")
    @Story("Update Post")
    public void testUpdatePost() {
        Post updatedPost = new PostBuilder()
                .userId(1)
                .title("Updated Title")
                .body("Updated body content")
                .build();

        Response response = PostApi.updatePost(1, updatedPost);

        assertThat(response)
                .statusCodeIs(200)
                .bodyContains("title", "Updated Title")
                .bodyContains("body", "Updated body content");

        System.out.println("✓ Updated post to: " + response.jsonPath().getString("title"));
    }

    @Test(priority = 5, groups = { "smoke" })
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete an existing post")
    @Story("Delete Post")
    public void testDeletePost() {
        Response response = PostApi.deletePost(1);

        assertThat(response).statusCodeIs(200);

        System.out.println("✓ Deleted post successfully");
    }

    @Test(priority = 6, groups = { "regression" })
    @Severity(SeverityLevel.NORMAL)
    @Description("Get comments for a post")
    @Story("Get Post Comments")
    public void testGetPostComments() {
        Response response = PostApi.getPostComments(1);

        assertThat(response).statusCodeIs(200);
        int commentCount = response.jsonPath().getList("$").size();
        Assert.assertTrue(commentCount > 0, "Should have comments");

        System.out.println("✓ Post has " + commentCount + " comments");
    }
}
