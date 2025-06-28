# 🧪 Report Management Testing Guide

## 🔭 Purpose

This guide covers the testing strategy, tools, and practices used in validating the Report Management Module of the MotorPH Payroll System.

---

## 🛡️ Technologies

- **JUnit 4** – for unit and integration testing
- **Mockito** – for mocking services, database connections, and JasperReports behavior
- **JasperReports Test Utilities** – for simulating `JasperPrint` objects

---

## 🔬 Package Structure

```plaintext
com.motorph.reportmanagement
└── test
    ├── controller       → Integration tests for controllers (PDF export behavior)
    ├── exporter         → Unit test for PDF exporter functionality
    ├── service          → Unit tests for report generation and mapping
    ├── suite            → Test suite classes to group test categories
    └── util             → Tests for file utilities and parameter builders
```

---

## 🔄 Test Suites

| Suite Name                  | Description                                          |
| --------------------------- | ---------------------------------------------------- |
| `ReportControllerTestSuite` | Runs all controller-level test cases                 |
| `ServiceTestSuite`          | Runs all service and mapper test cases               |
| `UtilTestSuite`             | Runs all utility test cases                          |
| `FullReportModuleTestSuite` | Runs all report-related test suites as a single unit |

---

## 📂 Test Output

- All generated test reports and PDFs are stored in the `test-output/` directory.
- Temporary files are cleaned up after each test unless debugging is required.

---

## ✅ Running Tests

Use the following command to run all tests:

```bash
ant test
```

> Ensure test dependencies are resolved and mock configurations are properly initialized.

---

## 📋 Notes & Assumptions

- **PDF Output Validation**: Tests assert file existence and validate file size or content where applicable.
- **Preview Mode**: Disabled during tests using `controller.setPreviewEnabled(false)`.
- **Mocking**: Services and JDBC calls are mocked using Mockito where feasible.
- **Isolation**: Each test runs in isolation with cleanup handled via `@After` methods.
- **Export Path**: Defaults to `test-output/` but can be overridden for integration scenarios.

---

## 🔹 Recommendation

If new reports, exporters, or services are introduced, corresponding test classes should follow the same structure and be added to the appropriate test suite. Ensure high coverage for business logic, report parameter construction, exporter logic, and integration paths.

---

## 📌 Authors & Contributors

See main module [README](../../../../reportmanagement/report_module_readme.md) for full credits under Group No. 2 – MotorPH Payroll System.

