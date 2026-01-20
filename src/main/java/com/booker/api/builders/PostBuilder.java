package com.booker.api.builders;

import com.booker.api.models.Post;

public class PostBuilder {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;

    public PostBuilder() {
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
}
