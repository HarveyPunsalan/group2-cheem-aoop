# 📊 Report Management Module – MotorPH Payroll System

## 🧹 Overview

The **Report Management Module** is a core submodule of the MotorPH Payroll Application responsible for generating, exporting, and managing various payroll-related reports such as payslips, bi-weekly summaries, and monthly summaries. It leverages **JasperReports** for dynamic PDF generation and provides integration points with other system modules.

---

## 📁 Package Structure

```plaintext
com.motorph.reportmanagement

🗋 config         → Configuration constants and template paths
🗋 controller     → Entry point classes connecting GUI to services
🗋 exporter       → Export logic for various file formats (PDF, etc.)
🗋 model          → Data Transfer Objects (DTOs) for reports
🗋 service        → Business logic and report generation handlers
🗋 util           → Utility classes for parameter building and file operations
```

---

## 🔍 Key Features

- 📄 **Payslip Generation** (per employee per payroll ID)
- 📅 **Bi-Weekly Summary Reports** (based on pay periods)
- 📆 **Monthly Payroll Summary Reports** (based on Year-Month)
- 📄 **PDF Export Support** via JasperReports
- ✅ **Structured Test Cases** for internal validation (JUnit 4)
- 🧪 Mockable and testable service components with JDBC integration

---

## 🚦 Class Responsibilities

### 🔧 `config`

| Class                 | Description                                         |
| --------------------- | --------------------------------------------------- |
| `ReportConfig`        | General configuration class for report settings     |
| `ReportConstants`     | Filename formats, extensions, and general constants |
| `ReportMessages`      | Standardized error and log messages                 |
| `ReportParameterKeys` | Centralized parameter key definitions               |
| `ReportTemplate`      | Enum for template file references                   |

### 🔹 `controller`

| Class                              | Description                                              |
| ---------------------------------- | -------------------------------------------------------- |
| `BaseReportController`             | Generic base for handling report generation with logging |
| `PayslipController`                | Handles generation of payslip reports                    |
| `PayrollBiWeeklySummaryController` | Manages bi-weekly payroll summary reports                |

### 📆 `exporter`

| Class               | Description                                |
| ------------------- | ------------------------------------------ |
| `PdfReportExporter` | Converts JasperPrint objects to PDF format |
| `ReportExporter`    | Interface for pluggable export strategies  |

### 📁 `model`

| Class                          | Description                                     |
| ------------------------------ | ----------------------------------------------- |
| `Payslip`                      | DTO for individual employee payslip             |
| `PayrollBiWeeklySummaryReport` | DTO for bi-weekly summaries                     |
| `BiWeeklyPayPeriodRange`       | DTO for pay period metadata (date ranges, etc.) |

### 🧠 `service`

| Class                                 | Description                                 |
| ------------------------------------- | ------------------------------------------- |
| `PayslipService`                      | Handles report logic for payslip generation |
| `PayrollBiWeeklySummaryReportService` | Main service for bi-weekly summaries        |
| `ReportService`                       | Common reusable logic for all reports       |
| Mappers                               | Convert JDBC ResultSets to report DTOs      |

### 🛠️ `util`

| Class                    | Description                                        |
| ------------------------ | -------------------------------------------------- |
| `ReportParameterBuilder` | Constructs `Map<String, Object>` for JasperReports |
| `ReportFileUtils`        | Handles file creation and directory validation     |
| `PDFExportHelper`        | Sets PDF metadata like title and author            |

---

## 🧲 Testing

Test cases are implemented using **JUnit 4**. Refer to the test suite for:

- `PayslipControllerTest`
- `PayrollBiWeeklySummaryControllerTest`
- Utility and service class unit tests
- Integration tests for PDF generation

> ✅ The module is verified using internal test cases as documented in the test case file (`MO-IT113` - Advanced OOP).

---

## 💪 Dependencies

- `jasperreports` (PDF rendering)
- `mysql-connector-java` (JDBC)
- `junit` (testing)

---

## ✅ How to Use

1. Ensure DB connectivity via `DatabaseConnection`
2. Call `generatePayslipPDF(employeeId, payrollId)` from `PayslipController`
3. Call `generateBiWeeklySummaryPDF(payPeriodId)` or `generateMonthlySummary(yearMonth)` from corresponding controllers
4. Output will be generated under configured export directory (e.g., `src/resources/report/generated-reports/`)

---

## 📌 Authors & Contributors

Group No. 2 – MotorPH Payroll System

- Charmaine Nabor
- Harvey Punsalan
- **Eugenio Villaverde**
- Emmar John Alvarez
- Michelle Joi Quesada

