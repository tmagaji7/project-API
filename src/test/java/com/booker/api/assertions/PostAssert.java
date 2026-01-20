package com.booker.api.assertions;

import com.booker.api.models.Post;
import org.testng.Assert;

public class PostAssert {
    private final Post post;

    private PostAssert(Post post) {
        this.post = post;
    }

    public static PostAssert assertThat(Post post) {
        return new PostAssert(post);
    }

    public PostAssert hasTitle(String expectedTitle) {
        Assert.assertEquals(post.getTitle(), expectedTitle, "Post title mismatch");
        return this;
    }

    public PostAssert hasBody(String expectedBody) {
        Assert.assertEquals(post.getBody(), expectedBody, "Post body mismatch");
        return this;
    }

    public PostAssert hasUserId(Integer expectedUserId) {
        Assert.assertEquals(post.getUserId(), expectedUserId, "Post userId mismatch");
        return this;
    }
}
