package com.booker.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;

/**
 * Post Model without Lombok
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;

    public Post() {
    }

    public Post(Integer id, Integer userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static PostBuilder builder() {
        return new PostBuilder();
    }

    public static class PostBuilder {
        private Integer id;
        private Integer userId;
        private String title;
        private String body;

        PostBuilder() {
        }

        public PostBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public PostBuilder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public PostBuilder title(String title) {
            this.title = title;
            return this;
        }

        public PostBuilder body(String body) {
            this.body = body;
            return this;
        }

        public Post build() {
            return new Post(id, userId, title, body);
        }

        public String toString() {
            return "Post.PostBuilder(id=" + this.id + ", userId=" + this.userId + ", title=" + this.title + ", body="
                    + this.body + ")";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(userId, post.userId) && Objects.equals(title, post.title)
                && Objects.equals(body, post.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, body);
    }

    @Override
    public String toString() {
        return "Post(id=" + this.getId() + ", userId=" + this.getUserId() + ", title=" + this.getTitle() + ", body="
                + this.getBody() + ")";
    }
}
