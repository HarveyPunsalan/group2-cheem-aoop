# 🛠️ MotorPH Payroll – Database Module Setup Guide

To ensure smooth integration and reliable functionality of the MotorPH Payroll Database Module, each team member must properly configure their local development environment. This setup guide outlines all necessary tools, settings, and verification steps to maintain consistency across the team and avoid issues such as connection failures, invalid credentials, or classpath misconfigurations.

## 🧰 Software Requirements

* **Java Development Kit (JDK)**: Version 17 or higher
* **MySQL Server**: Version 8.0 or later
* **MySQL Workbench** *(optional but recommended for visualizing database structures)*
* **NetBeans IDE** or any compatible Java IDE
  ℹ️ *NetBeans users: Add the JDBC driver via "Projects > Libraries > Add JAR/Folder".*
* **MySQL JDBC Driver** (Connector/J)
  🔗 [Download from official site](https://dev.mysql.com/downloads/connector/j/) if it is not already included in your project repository.

## 🗃️ Database Setup Instructions

1. **Create the MySQL database** using the provided schema script (`01_schema.sql`).
2. **Insert reference data**, such as roles, departments, and tax brackets, using any available insert scripts.
3. **Configure the ****`db.properties`**** file** to reflect your local MySQL environment:

   ```properties
   db.url=jdbc:mysql://localhost:3306/motorph_payroll_db
   db.username=root
   db.password=yourpassword
   ```

   > ⚠️ Replace `yourpassword` with your actual MySQL root password, or configure a dedicated development user with appropriate access.

## 📁 Project Configuration Steps

* Place the `db.properties` file in the `resources` or `config` directory so that it is included in the classpath.
* Ensure that `mysql-connector-java.jar` is added to your project’s build path.
* Compile and run the `DatabaseTest` class to verify that your database connection is successful.

## ✅ Pre-Integration Checklist

Verify the following before running the application:

* Java is installed and environment variables are configured
* MySQL Server is running and accessible
* Database schema has been initialized successfully
* \`db.properties\` file is correctly set up
* Connection test passes using \`DatabaseTest\`

---

Documenting these setup steps in a dedicated `SETUP.md` file or referencing them from your module's main README helps ensure consistency and simplifies onboarding for all developers contributing to the MotorPH Payroll System.
