package com.booker.api.assertions;

import com.booker.api.models.User;
import org.testng.Assert;

public class UserAssert {
    private final User user;

    private UserAssert(User user) {
        this.user = user;
    }

    public static UserAssert assertThat(User user) {
        return new UserAssert(user);
    }

    public UserAssert hasName(String expectedName) {
        Assert.assertEquals(user.getName(), expectedName, "User name mismatch");
        return this;
    }

    public UserAssert hasEmail(String expectedEmail) {
        Assert.assertEquals(user.getEmail(), expectedEmail, "User email mismatch");
        return this;
    }

    public UserAssert hasUsername(String expectedUsername) {
        Assert.assertEquals(user.getUsername(), expectedUsername, "User username mismatch");
        return this;
    }
}
