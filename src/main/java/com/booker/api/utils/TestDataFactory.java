package com.booker.api.utils;

import com.booker.api.builders.*;
import com.booker.api.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Test Data Factory
 * Generates random test data for API tests
 */
public class TestDataFactory {

    private static int postIdCounter = 1000;
    private static int userIdCounter = 100;

    private TestDataFactory() {
    }

    // ============ Post Data ============

    public static Post createRandomPost() {
        String unique = UUID.randomUUID().toString().substring(0, 6);
        return new PostBuilder()
                .userId(1)
                .title("Test Post " + unique)
                .body("This is a test post body with unique ID: " + unique)
                .build();
    }

    public static Post createPost(int userId, String title, String body) {
        return new PostBuilder()
                .userId(userId)
                .title(title)
                .body(body)
                .build();
    }

    public static List<Post> createPostList(int count) {
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            posts.add(createRandomPost());
        }
        return posts;
    }

    // ============ User Data ============

    public static User createRandomUser() {
        String unique = UUID.randomUUID().toString().substring(0, 6);

        Geo geo = new GeoBuilder()
                .lat("40.7128")
                .lng("-74.0060")
                .build();

        Address address = new AddressBuilder()
                .street("123 Test Street " + unique)
                .suite("Suite " + unique)
                .city("Test City")
                .zipcode("10001")
                .geo(geo)
                .build();

        Company company = new CompanyBuilder()
                .name("Test Company " + unique)
                .catchPhrase("Innovation " + unique)
                .bs("enterprise solutions")
                .build();

        return new UserBuilder()
                .name("Test User " + unique)
                .username("testuser_" + unique)
                .email("test_" + unique + "@example.com")
                .phone("555-" + unique)
                .website("test" + unique + ".com")
                .address(address)
                .company(company)
                .build();
    }

    public static List<User> createUserList(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(createRandomUser());
        }
        return users;
    }
}
