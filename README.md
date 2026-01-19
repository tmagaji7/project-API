# API Test Framework

A professional REST API test automation framework using **Java 17**, **REST Assured**, **TestNG**, **Lombok**, and **Allure Reporting**.

## 🛠️ Tech Stack

| Component | Library | Version |
|-----------|---------|---------|
| Language | Java | 17 |
| Build Tool | Maven | 3.x |
| API Client | REST Assured | 5.4.0 |
| Test Framework | TestNG | 7.9.0 |
| Reporting | Allure | 2.25.0 |
| Boilerplate Reduction | Lombok | edge |
| JSON | Jackson | 2.16.1 |

## 📁 Project Structure

```
src/
├── main/java/com/booker/api/
│   ├── api/                    # API layer (PostApi, UserApi)
│   ├── config/                 # ConfigManager (singleton)
│   ├── constants/              # Routes (endpoint constants)
│   ├── core/                   # RequestSpecFactory
│   ├── models/                 # POJOs with Lombok (@Data, @Builder)
│   └── utils/                  # RestUtils, TestDataFactory
│
└── test/
    ├── java/com/booker/api/
    │   ├── base/               # BaseTest with Allure listener
    │   └── tests/              # Test classes
    │       ├── PostCrudTest    # Post CRUD operations
    │       ├── PostNegativeTest# Post error scenarios
    │       ├── UserCrudTest    # User CRUD with nested objects
    │       └── UserNegativeTest# User error scenarios
    └── resources/
        ├── config/qa.properties
        └── allure.properties
```

## ✨ Key Features

### **Lombok Integration**
```java
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Post {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
}
```

### **Allure Annotations**
```java
@Epic("Posts API")
@Feature("Post CRUD Operations")
public class PostCrudTest {
    
    @Test(priority = 1, groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create a new post")
    @Story("Create Post")
    public void testCreatePost() { ... }
}
```

### **TestNG Features**
- **Priorities**: `@Test(priority = 1)`
- **Groups**: `@Test(groups = {"smoke", "regression"})`
- **Dependencies**: `@Test(dependsOnMethods = "testCreatePost")`
- **Parallel Execution**: Configured in testng.xml

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven 3.x
- IntelliJ IDEA (optional)

### Clone & Run
```bash
git clone https://github.com/AyushRatan1/booking-api.git
cd booking-api
mvn clean test
```

### Run by Groups
```bash
# Smoke tests only
mvn test -Dgroups=smoke

# Regression tests
mvn test -Dgroups=regression
```

### Generate Allure Report
```bash
mvn allure:serve
```

## 📊 Test Summary

| Test Class | Tests | Groups |
|------------|-------|--------|
| PostCrudTest | 6 | smoke, regression |
| PostNegativeTest | 3 | regression, negative |
| UserCrudTest | 5 | smoke, regression |
| UserNegativeTest | 2 | regression, negative |
| **Total** | **16** | |

## 🌐 API Under Test

[JSONPlaceholder](https://jsonplaceholder.typicode.com) - Free fake REST API

| Endpoint | Description |
|----------|-------------|
| `/posts` | Posts CRUD |
| `/users` | Users with nested Address, Geo, Company |
| `/comments` | Post comments |
| `/todos` | Todo items |

## 📝 License

MIT License
