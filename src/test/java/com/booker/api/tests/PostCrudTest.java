package com.booker.api.tests;

import com.booker.api.api.PostApi;
import com.booker.api.base.BaseTest;
import com.booker.api.models.Post;
import com.booker.api.utils.TestDataFactory;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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

    @Test(priority = 1, groups = { "smoke", "post" })
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create a new post with valid data")
    @Story("Create Post")
    public void testCreatePost() {
        Response response = PostApi.createPost(testPost);

        Assert.assertEquals(response.getStatusCode(), 201, "Status should be 201 Created");
        Assert.assertEquals(response.jsonPath().getInt("id"), createdPostId, "ID should be 101");
        Assert.assertEquals(response.jsonPath().getString("title"), testPost.getTitle());

        System.out.println("✓ Created post with ID: " + createdPostId);
    }

    @Test(priority = 2, groups = { "smoke", "post" }, dependsOnMethods = "testCreatePost")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get an existing post by ID")
    @Story("Get Post")
    public void testGetPost() {
        Response response = PostApi.getPost(1);

        Assert.assertEquals(response.getStatusCode(), 200, "Status should be 200 OK");
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
        Assert.assertNotNull(response.jsonPath().getString("title"));
        Assert.assertNotNull(response.jsonPath().getString("body"));

        System.out.println("✓ Retrieved post: " + response.jsonPath().getString("title"));
    }

    @Test(priority = 3, groups = { "regression", "post" })
    @Severity(SeverityLevel.NORMAL)
    @Description("Get all posts")
    @Story("Get All Posts")
    public void testGetAllPosts() {
        Response response = PostApi.getAllPosts();

        Assert.assertEquals(response.getStatusCode(), 200);
        int postCount = response.jsonPath().getList("$").size();
        Assert.assertEquals(postCount, 100, "Should return 100 posts");

        System.out.println("✓ Retrieved " + postCount + " posts");
    }

    @Test(priority = 4, groups = { "smoke", "post" })
    @Severity(SeverityLevel.CRITICAL)
    @Description("Update an existing post")
    @Story("Update Post")
    public void testUpdatePost() {
        Post updatedPost = Post.builder()
                .userId(1)
                .title("Updated Title")
                .body("Updated body content")
                .build();

        Response response = PostApi.updatePost(1, updatedPost);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "Updated Title");
        Assert.assertEquals(response.jsonPath().getString("body"), "Updated body content");

        System.out.println("✓ Updated post to: " + response.jsonPath().getString("title"));
    }

    @Test(priority = 5, groups = { "smoke", "post" })
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete an existing post")
    @Story("Delete Post")
    public void testDeletePost() {
        Response response = PostApi.deletePost(1);

        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.println("✓ Deleted post successfully");
    }

    @Test(priority = 6, groups = { "regression", "post" })
    @Severity(SeverityLevel.NORMAL)
    @Description("Get comments for a post")
    @Story("Get Post Comments")
    public void testGetPostComments() {
        Response response = PostApi.getPostComments(1);

        Assert.assertEquals(response.getStatusCode(), 200);
        int commentCount = response.jsonPath().getList("$").size();
        Assert.assertTrue(commentCount > 0, "Should have comments");

        System.out.println("✓ Post has " + commentCount + " comments");
    }
}
