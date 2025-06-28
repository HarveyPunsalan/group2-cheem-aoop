# 📦 MotorPH Payroll – Database Module 🎯🧱📁

This module provides the foundational database access and configuration for the MotorPH Payroll System. It is built using **Java**, utilizes **JDBC** for database interaction, and connects to a **MySQL** backend. It abstracts JDBC operations to ensure consistent, reusable, and maintainable integration. 💡🔄🛠️

## 🧩 Responsibilities 🎯📌📋

* Load database configuration from `db.properties`
* Establish JDBC connections
* Execute SQL scripts (insert, update, delete, select)
* Map database `ResultSet` to Java objects

## 📂 Package Structure 🗂️📦🧭

The database module is organized into focused packages that promote separation of concerns and simplify maintenance:

| Package      | Purpose                                        |
| ------------ | ---------------------------------------------- |
| `config`     | Reads DB configuration files                   |
| `connection` | Manages JDBC connection and service layer      |
| `execution`  | Executes SQL commands and maps results         |
| `test`       | Utilities for manually testing DB connectivity |

## 📚 Key Classes 🧠🧰📘

* **`DatabaseConfigFile`** – 
* **`DatabaseConfigReader`** – Loads properties from the config file
* **`DatabaseConnector`** – Establishes raw JDBC connection
* **`DatabaseService`** – Provides a shared connection instance wrapper
* **`SQLExecutor`** – Executes parameterized SQL queries (insert, update, delete, select)
* **`Script`** – Enum that stores reusable SQL statements
* **`ResultSetMapper<T>`** – Functional interface for mapping query results to Java objects
* **`DatabaseTest`** – Simple test class to verify DB setup

## 🧪 Example Usage 💻🧬📝

This example demonstrates how to retrieve an employee record using a parameterized query with the `SQLExecutor` and map the result to an `Employee` object:

```java
Connection conn = DatabaseService.connectToMotorPH();
SQLExecutor executor = new SQLExecutor(conn);

List<Object> params = List.of("employee_id");
List<Employee> results = executor.executeQuery(
    Script.FIND_EMPLOYEE_BY_ID,
    params,
    resultSet -> new Employee(
        resultSet.getInt("employee_id"),
        resultSet.getString("first_name")
        // Add remaining mappings here
    )
);
```

## 🔧 Design Principles & Notes ⚙️📐🧩

This section outlines the core principles guiding the module's development, alongside implementation tips to ensure consistency and reliability:

* **Modular Structure**: The codebase is structured into distinct packages that isolate concerns (e.g., config loading vs. connection logic), improving clarity and manageability.
* **Reusability**: Core classes like `SQLExecutor` and `DatabaseService` are designed to be reused across modules for consistent interaction with the database.
* **Testability**: The `DatabaseTest` utility supports isolated testing of connectivity to quickly identify configuration or access issues.
* **Scalability**: New entities or SQL operations can be easily introduced by extending mappers, scripts, and DAO-level logic.
* **Best Practices**:

  * Use try-with-resources for safe and efficient resource management
  * Ensure proper validation when adding new SQL scripts to the `Script` enum
  * Always verify that parameter lists match the `?` placeholders in SQL statements

---

## 📚 Notes 

* Use try-with-resources for safe and efficient resource management

* Ensure proper validation when adding new SQL scripts to Script enum

* Parameter lists must match placeholders in SQL statements

For more details, refer to related modules (e.g., Employee Management, Payroll Processing) that utilize this database layer. 📖📂🔗
