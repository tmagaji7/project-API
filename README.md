# Booking API Test Automation Framework

## Overview
This project is a robust, modular, and scalable test automation framework for the JSONPlaceholder API. It is built using **Java**, **RestAssured**, **TestNG**, and **Allure Report**, adhering to clean code principles and industry best practices.

The framework is designed to handle complex nested objects (like Users with Addresses and Companies) and validates standard CRUD operations.

## 🛠 Technology Stack
- **Language**: Java 17
- **HTTP Client**: RestAssured 5.3.x
- **Testing Framework**: TestNG 7.8.x
- **Reporting**: Allure Report
- **Build Tool**: Maven
- **Architecture**: POJO-based, Builder Pattern, Singleton Configuration

## 📂 Project Structure

```
src
├── main
│   ├── java.com.booker.api
│   │   ├── api          # API Layer (Endpoints & Request wrapping)
│   │   ├── builders     # Builder Pattern for complex objects
│   │   ├── config       # Configuration Management (Singleton)
│   │   ├── models       # POJO Models (User, Address, Company, etc.)
│   │   ├── utils        # Utilities (RestUtils, Routes, TestDataFactory)
│   └── resources
│       └── config       # Configuration files (qa.properties)
└── test
    ├── java.com.booker.api
    │   ├── assertions   # Validating constraints (Fluent Assertions)
    │   ├── base         # Base Test Class (Setup/Teardown)
    │   └── tests        # Actual Test Classes (UserCrudTest, PostCrudTest)
```

## ⚙️ Configuration
The framework uses a **Singleton ConfigManager** to load properties securely.
- **File**: `src/main/resources/config/qa.properties`
- **Property**: `base.url=https://jsonplaceholder.typicode.com`

*Note: This file was vital for the framework to run and was added during the recent audit.*

## 🧪 Key Features & Design Patterns

### 1. Builder Pattern
We use Builders (`UserBuilder`, `AddressBuilder`) to construct complex objects fluently. This avoids "telescoping constructor" anti-patterns and makes test data creation readable.
```java
// Example
User user = new UserBuilder().name("John").email("john@test.com").build();
```

### 2. Random Data Generation
**`TestDataFactory`** ensures isolation between tests.
- Every test run generates unique UUID-based strings for Names, Emails, and Titles.
- **Benefit**: Prevents data collisions and flaky tests.

### 3. API Abstraction Layer
Tests do not make HTTP calls directly. They delegate to `UserApi` or `PostApi`.
- **Benefit**: If an endpoint changes (e.g., `/users` becomes `/v1/users`), we only update one file (`Routes.java`), not 50 tests.

### 4. Fluent Assertions
We implemented custom assertion classes (`ResponseAssert`) to make validation readable:
```java
assertThat(response)
    .statusCodeIs(200)
    .bodyContains("email", expectedEmail);
```

## 🧠 Test Logic & JSONPlaceholder Quirks
The mock API (JSONPlaceholder) has specific behaviors that our tests account for:

- **Non-Persistence**: Resources created via `POST` are **NOT** actually saved on the server. They return a success response but disappear immediately.
- **Fixed IDs**:
    - Creation always returns ID `11` (for Users) or `101` (for Posts).
    - We assert against these specific IDs because the mock server forces them.
- **Statelessness**:
    - In `UserCrudTest`, the `testGetUser` method deliberately fetches ID `1` because the ID `11` we "created" in the previous step doesn't really exist.
    - This ensures our tests pass green even on a stateless mock server.

## 🚀 How to Run
(Assuming Maven is installed)

1. **Compile**:
   ```bash
   mvn clean compile
   ```
2. **Run Tests**:
   ```bash
   mvn clean test
   ```
3. **Generate Report**:
   ```bash
   mvn allure:serve
   ```

## ✅ Recent Fixes (Audit Log)
- **Compilation**: Fixed missing imports in `UserCrudTest.java`.
- **Infrastructure**: Created missing `src/main/resources` directory.
- **Logic**: Refined `UserCrudTest` to use `dependsOnMethods` for strict execution order (`Create` -> `Get` -> `Update` -> `Delete`).
