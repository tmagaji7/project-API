package com.booker.api.base;

import com.booker.api.core.RequestSpecFactory;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

/**
 * Base Test Class
 * Common setup and teardown for all tests
 */
@Listeners({ AllureTestNg.class })
public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        System.out.println("========================================");
        System.out.println("Starting API Test Suite");
        System.out.println("========================================");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        RequestSpecFactory.reset();
        System.out.println("========================================");
        System.out.println("API Test Suite Completed");
        System.out.println("========================================");
    }
}
