# MotorPH Payroll SQL Setup & Migration Scripts ✨📂🗃️

This repository contains the structured SQL scripts used to build and manage the MotorPH Payroll System Database. The files are organized logically to follow a step-by-step implementation aligned with database design best practices and project milestones. 🧱⚙️🗂️

---

## Folder Structure Overview 📁📌📑

### 01\_schema/ 🧾🛠️🧱

Includes all SQL files required to define the database schema. Tables are grouped by functional module, and filenames begin with numeric prefixes to ensure correct execution order. 🗃️📋📐

* 00\_create\_database.sql — Creates the main MotorPH database instance.
* 01\_create\_employee\_management.sql to 07\_create\_payroll\_processing.sql — Defines table structures per system module.
* run\_schema.sql — A batch runner script that executes all schema files sequentially.

### 02\_constraints/ 🔐📎🗜️

Stores scripts for applying integrity constraints, separated for modular clarity. Executing constraints separately supports better debugging and schema validation. 🧐🔍📘

* 01\_add\_primary\_keys.sql — Adds primary key definitions.
* 02\_add\_not\_null\_constraints.sql — Enforces mandatory fields.
* 03\_add\_default\_values.sql — Establishes default values for specific columns.
* 04\_add\_unique\_constraints.sql — Prevents duplicate entries in targeted columns.
* 05\_add\_check\_constraints.sql — Applies domain-specific validation rules.
* 06\_add\_foreign\_keys.sql — Adds foreign key constraints (run last to guarantee table dependencies).

### 03\_seed\_data/ 🌱📦📊

Populates the database with starter data. Scripts are numbered to reflect dependency order (e.g., roles before users). 🚀📈📤

* 01\_insert\_address\_type.sql
* 08\_insert\_system\_user.sql
* 19\_insert\_supervisor\_assignment.sql

### 04\_functions/ 🔁🧮📝

Reserved for reusable MySQL user-defined functions that perform custom operations. 🧠💾🧩

* No scripts available yet (as of 2025-06-03).

### 05\_views/ 👁️📊🔎

Includes view definitions that simplify common queries, especially for reporting and data display. 📈💬🧷

* 01\_create\_view\_employee\_details.sql
* Future additions will support payroll summaries and payslip generation.

### 06\_stored\_procedures/ 🧰📐📎

Contains scripts for reusable procedures that encapsulate payroll-related logic such as calculations or data generation. 📌📊🛠️

* Content pending implementation.

### 07\_migration/ 📦🔄🧭

Reserved for future use, including schema migrations or data transformation scripts after deployment. ⏳📜🔧

### 08\_triggers/ ⛓️🔁⚙️

This directory will contain trigger scripts for automating data integrity and business rule enforcement during database operations. 🚨🔧🧠

* To be developed in later phases.

### 09\_indexes/ 📇🚀📘

Includes scripts for creating indexes that optimize query performance and data retrieval. 🧮📉🗃️

### 10\_tests/ ✅🔍🧪

Provides test SQL scripts for validating database behavior, including schema rules, sample data accuracy, and constraint logic. 🧾🛡️🔍

### 11\_utilities/ 🛠️🧰📥

Utility tools such as mock data generators, export/import helpers, and maintenance scripts. 🧮📤📊

---

## Development Workflow Reference 📆🛠️📋

This folder structure aligns with the development stages outlined in the MotorPH Gantt Chart and Database Design Document (DBDD). 📈📘🗂️

* **Week 3–4**: Schema creation and database structure definition
* **Week 4**: Constraint enforcement and testing
* **Week 4–5**: Initial data seeding
* **Week 5+**: Development of views and stored procedures
* **Week 5–6**: Integration testing and debugging

---

## Notes 🗒️📎📢

* Filenames use `snake_case` and start with numeric prefixes for proper execution sequencing.
* Data insertions assume prerequisite constraints are already enforced.
* Constraints are divided by type for maintainability and cleaner rollback handling.
* Use caution when executing `06_add_foreign_keys.sql` — confirm referenced tables are fully populated.
* To rebuild the entire schema, run `run_schema.sql` from the root.

---

## Authors & Contributors 🧑‍💻👩‍💼👨‍🔧

* **Project Team**: Charmaine Nabor, Harvey Punsalan, Eugenio Villaverde, Michelle Joi Quesada, Emmar John Alvarez 🧑‍💻🧠🤝
* **Last Updated**: 2025-06-03 🗓️📅✅
