package com.booker.api.tests;

import com.booker.api.api.PostApi;
import com.booker.api.base.BaseTest;
import com.booker.api.builders.PostBuilder;
import com.booker.api.models.Post;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.booker.api.assertions.ResponseAssert.assertThat;

/**
 * Post Negative Tests
 * Tests for error scenarios and edge cases
 */
@Epic("Posts API")
@Feature("Post Negative Scenarios")
public class PostNegativeTest extends BaseTest {

    @Test(groups = { "regression", "negative" })
    @Severity(SeverityLevel.MINOR)
    @Description("Get post with invalid ID")
    @Story("Invalid Post ID")
    public void testGetPostInvalidId() {
        Response response = PostApi.getPost(99999);

        assertThat(response).statusCodeIs(404);

        System.out.println("✓ Correctly returned 404 for invalid post ID");
    }

    @Test(groups = { "regression", "negative" })
    @Severity(SeverityLevel.MINOR)
    @Description("Create post with empty body")
    @Story("Empty Post Body")
    public void testCreatePostEmptyBody() {
        Post emptyPost = new PostBuilder()
                .userId(1)
                .title("")
                .body("")
                .build();

        Response response = PostApi.createPost(emptyPost);

        // JSONPlaceholder accepts empty posts (returns 201)
        assertThat(response).statusCodeIs(201);

        System.out.println("✓ Post created with empty fields (API accepts it)");
    }

    @Test(groups = { "regression", "negative" })
    @Severity(SeverityLevel.MINOR)
    @Description("Update non-existent post")
    @Story("Update Invalid Post")
    public void testUpdateNonExistentPost() {
        Post post = new PostBuilder()
                .userId(1)
                .title("Test")
                .body("Test body")
                .build();

        Response response = PostApi.updatePost(99999, post);

        // JSONPlaceholder returns 500 for non-existent resources on PUT
        Assert.assertTrue(
                response.getStatusCode() == 200 || response.getStatusCode() == 500,
                "Should return 200 or 500");

        System.out.println("✓ Update non-existent post returned: " + response.getStatusCode());
    }
}
