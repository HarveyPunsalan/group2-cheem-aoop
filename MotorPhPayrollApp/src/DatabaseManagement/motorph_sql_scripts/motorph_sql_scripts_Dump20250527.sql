CREATE DATABASE  IF NOT EXISTS `motorph_payroll_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `motorph_payroll_db`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: motorph_payroll_db
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `access`
--

DROP TABLE IF EXISTS `access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `access` (
  `access_id` int NOT NULL AUTO_INCREMENT,
  `access_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `access_category` int DEFAULT NULL,
  `requires_approval` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`access_id`),
  UNIQUE KEY `uq_access_name` (`access_name`),
  KEY `fk_access_category` (`access_category`),
  CONSTRAINT `fk_access_category` FOREIGN KEY (`access_category`) REFERENCES `access_category` (`access_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access`
--

LOCK TABLES `access` WRITE;
/*!40000 ALTER TABLE `access` DISABLE KEYS */;
/*!40000 ALTER TABLE `access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `access_category`
--

DROP TABLE IF EXISTS `access_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `access_category` (
  `access_category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`access_category_id`),
  UNIQUE KEY `uq_access_category_name` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_category`
--

LOCK TABLES `access_category` WRITE;
/*!40000 ALTER TABLE `access_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `access_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address_type`
--

DROP TABLE IF EXISTS `address_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address_type` (
  `address_type_id` int NOT NULL AUTO_INCREMENT,
  `address_type_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address_type_description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`address_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address_type`
--

LOCK TABLES `address_type` WRITE;
/*!40000 ALTER TABLE `address_type` DISABLE KEYS */;
INSERT INTO `address_type` VALUES (1,'permanent','Permanent/Home'),(2,'current','Current Residence'),(3,'billing','Billing Address'),(4,'mailing','Mailing Address'),(5,'office','Office Location'),(6,'temporary','Temporary Stay'),(7,'other','Other');
/*!40000 ALTER TABLE `address_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `allowance`
--

DROP TABLE IF EXISTS `allowance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allowance` (
  `allowance_id` int NOT NULL AUTO_INCREMENT,
  `allowance_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`allowance_id`),
  UNIQUE KEY `uq_allowance_name` (`allowance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allowance`
--

LOCK TABLES `allowance` WRITE;
/*!40000 ALTER TABLE `allowance` DISABLE KEYS */;
/*!40000 ALTER TABLE `allowance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance_processing`
--

DROP TABLE IF EXISTS `attendance_processing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance_processing` (
  `attendance_processing_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `pay_period_id` int DEFAULT NULL,
  `total_late_hours` decimal(5,2) DEFAULT NULL,
  `total_approved_overtime_hours` decimal(5,2) DEFAULT NULL,
  `total_worked_hours` decimal(5,2) DEFAULT NULL,
  `total_paid_leave_hours` decimal(5,2) DEFAULT NULL,
  `payable_hours` decimal(5,2) DEFAULT NULL,
  `status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `approved_by` int DEFAULT NULL,
  PRIMARY KEY (`attendance_processing_id`),
  KEY `fk_attendance_employee` (`employee_id`),
  KEY `fk_attendance_pay_period` (`pay_period_id`),
  KEY `fk_attendance_approver` (`approved_by`),
  CONSTRAINT `fk_attendance_approver` FOREIGN KEY (`approved_by`) REFERENCES `employee_personal_information` (`employee_id`),
  CONSTRAINT `fk_attendance_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee_personal_information` (`employee_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_attendance_pay_period` FOREIGN KEY (`pay_period_id`) REFERENCES `pay_period` (`pay_period_id`),
  CONSTRAINT `chk_payable_hours_non_negative` CHECK ((`payable_hours` >= 0)),
  CONSTRAINT `chk_total_late_hours_non_negative` CHECK ((`total_late_hours` >= 0)),
  CONSTRAINT `chk_total_overtime_hours_non_negative` CHECK ((`total_approved_overtime_hours` >= 0)),
  CONSTRAINT `chk_total_worked_hours_non_negative` CHECK ((`total_worked_hours` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance_processing`
--

LOCK TABLES `attendance_processing` WRITE;
/*!40000 ALTER TABLE `attendance_processing` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance_processing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily_time_record`
--

DROP TABLE IF EXISTS `daily_time_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `daily_time_record` (
  `dtr_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `login` time DEFAULT NULL,
  `logout` time DEFAULT NULL,
  `late_hours` decimal(5,2) DEFAULT NULL,
  `overtime_hours` decimal(5,2) DEFAULT NULL,
  `worked_hours` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`dtr_id`),
  KEY `fk_dtr_employee` (`employee_id`),
  CONSTRAINT `fk_dtr_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee_personal_information` (`employee_id`) ON DELETE CASCADE,
  CONSTRAINT `chk_late_hours_non_negative` CHECK ((`late_hours` >= 0)),
  CONSTRAINT `chk_overtime_hours_non_negative` CHECK ((`overtime_hours` >= 0)),
  CONSTRAINT `chk_worked_hours_non_negative` CHECK ((`worked_hours` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_time_record`
--

LOCK TABLES `daily_time_record` WRITE;
/*!40000 ALTER TABLE `daily_time_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `daily_time_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deduction_chart_type`
--

DROP TABLE IF EXISTS `deduction_chart_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deduction_chart_type` (
  `chart_id` int NOT NULL AUTO_INCREMENT,
  `chart_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `chart_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `chart_description` text COLLATE utf8mb4_unicode_ci,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`chart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deduction_chart_type`
--

LOCK TABLES `deduction_chart_type` WRITE;
/*!40000 ALTER TABLE `deduction_chart_type` DISABLE KEYS */;
INSERT INTO `deduction_chart_type` VALUES (1,'SSS','SSS Contribution Chart','Social Security System contribution rules','2024-12-31 16:00:00','2024-12-31 16:00:00'),(2,'PAGIBIG','Pag-IBIG Contribution Chart','Home Development Mutual Fund (Pag-IBIG) contribution rules','2024-12-31 16:00:00','2024-12-31 16:00:00'),(3,'PHILHEALTH','PhilHealth Contribution Chart','Philippine Health Insurance (PhilHealth) contribution rules','2024-12-31 16:00:00','2024-12-31 16:00:00'),(4,'WITHHOLDING_TAX','Withholding Tax Bracket','Income tax withholding brackets as of 2025-01-01','2024-12-31 16:00:00','2024-12-31 16:00:00');
/*!40000 ALTER TABLE `deduction_chart_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `department_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department_head` int DEFAULT NULL,
  PRIMARY KEY (`department_id`),
  UNIQUE KEY `uq_department_name` (`department_name`),
  KEY `fk_department_head` (`department_head`),
  CONSTRAINT `fk_department_head` FOREIGN KEY (`department_head`) REFERENCES `employee_personal_information` (`employee_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Executive',NULL),(2,'Operations',NULL),(3,'Finance',NULL),(4,'Marketing',NULL),(5,'IT Operations and Systems',NULL),(6,'Human Resources',NULL),(7,'Accounting',NULL),(8,'Payroll',NULL),(9,'Account',NULL),(10,'Sales & Marketing',NULL),(11,'Supply Chain and Logistics',NULL),(12,'Customer Service and Relations',NULL);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_address`
--

DROP TABLE IF EXISTS `employee_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `house_number` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `street` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `barangay` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `municipality` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `province` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `postal_code` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `country` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address_type` int DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `fk_address_employee` (`employee_id`),
  KEY `fk_address_type` (`address_type`),
  CONSTRAINT `fk_address_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee_personal_information` (`employee_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_address_type` FOREIGN KEY (`address_type`) REFERENCES `address_type` (`address_type_id`),
  CONSTRAINT `chk_postal_code_format` CHECK (regexp_like(`postal_code`,_utf8mb4'^[0-9]{4}$'))
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_address`
--

LOCK TABLES `employee_address` WRITE;
/*!40000 ALTER TABLE `employee_address` DISABLE KEYS */;
INSERT INTO `employee_address` VALUES (1,10001,NULL,'Valero Carpark Building Valero Street',NULL,'Makati',NULL,'1227','Philippines',1),(2,10002,NULL,'San Antonio De Padua 2, Block 1 Lot 8 and 2',NULL,'Dasmarinas','Cavite',NULL,'Philippines',1),(3,10003,'402','4th Floor Jiao Building Timog Avenue Cor. Quezon Avenue',NULL,'Quezon',NULL,'1100','Philippines',1),(4,10004,'460','Solanda Street',NULL,'Intramuros','Manila','1000','Philippines',1),(5,10005,NULL,'National Highway',NULL,'Gingoog','Misamis Occidental',NULL,'Philippines',1),(6,10006,'17/85','Stracke Via Suite 042','Poblacion','Las Pinas','Dinagat Islands','4783','Philippines',1),(7,10007,'99','Strosin Hills','Poblacion','Bislig','Tawi-Tawi','5340','Philippines',1),(8,10008,'12A/33','Upton Isle Apt. 420',NULL,'Roxas City','Surigao del Norte','1814','Philippines',1),(9,10009,'90A','Dibbert Terrace Apt. 190',NULL,'San Lorenzo','Davao del Norte','6056','Philippines',1),(10,10010,'284','T. Morato corner, Scout Rallos Street',NULL,'Quezon',NULL,NULL,'Philippines',1),(11,10011,'93/54','Shanahan Alley Apt. 183',NULL,'Santo Tomas','Masbate','1572','Philippines',1),(12,10012,'49','Springs Apt. 266','Poblacion','Taguig','Occidental Mindoro','3200','Philippines',1),(13,10013,'42/25','Sawayn Stream',NULL,'Ubay','Zamboanga del Norte','1208','Philippines',1),(14,10014,'37/46','Kulas Roads',NULL,'Maragondon','Quirino','0962','Philippines',1),(15,10015,'22A/52','Lubowitz Meadows',NULL,'Pililla','Zambales','4895','Philippines',1),(16,10016,'90','O\'Keefe Spur Apt. 379',NULL,'Catigbian','Sulu','2772','Philippines',1),(17,10017,'89A','Armstrong Trace',NULL,'Compostela','Maguindanao','7874','Philippines',1),(18,10018,'8','Grant Drive Suite 406','Poblacion','Iloilo City','La Union','9186','Philippines',1),(19,10019,'93A/21','Berge Points',NULL,'Tapaz','Quezon','2180','Philippines',1),(20,10020,'65','Murphy Center Suite 094','Poblacion','Palayan','Quirino','5636','Philippines',1),(21,10021,'47A/94','Larkin Plaza Apt. 179','Poblacion','Caloocan','Quirino','2751','Philippines',1),(22,10022,'06A','Gulgowski Extensions',NULL,'Bongabon','Zamboanga del Sur','6085','Philippines',1),(23,10023,'99A','Padberg Spring','Poblacion','Mabalacat','Lanao del Sur','3959','Philippines',1),(24,10024,'80A/48','Ledner Ridges','Poblacion','Kabankalan','Marinduque','8870','Philippines',1),(25,10025,'96/48','Watsica Flats Suite 734','Poblacion','Malolos','Ifugao','1844','Philippines',1),(26,10026,'58A','Wilderman Walks','Poblacion','Digos','Davao del Sur','5822','Philippines',1),(27,10027,'60','Goyette Valley Suite 219','Poblacion','Tabuk','Lanao del Sur','3159','Philippines',1),(28,10028,'66/77','Mann Views',NULL,'Luisiana','Dinagat Islands','1263','Philippines',1),(29,10029,'72/70','Stamm Spurs',NULL,'Bustos','Iloilo','4550','Philippines',1),(30,10030,'50A/83','Bahringer Oval Suite 145',NULL,'Kiamba','Nueva Ecija','7688','Philippines',1),(31,10031,'95','Cremin Junction',NULL,'Surallah','Cotabato','2809','Philippines',1),(32,10032,NULL,'Hi-way','Yati','Liloan','Cebu','6002','Philippines',1),(33,10033,NULL,'Bulala',NULL,'Camalaniugan','Cagayan','3510','Philippines',1),(34,10034,NULL,'Agapita Building',NULL,NULL,'Manila',NULL,'Philippines',1);
/*!40000 ALTER TABLE `employee_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_allowance`
--

DROP TABLE IF EXISTS `employee_allowance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_allowance` (
  `employee_allowance_id` int NOT NULL AUTO_INCREMENT,
  `allowance_id` int DEFAULT NULL,
  `employee_id` int DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `effective_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `allowance_frequency` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`employee_allowance_id`),
  KEY `fk_employee_allowance_employee` (`employee_id`),
  KEY `fk_employee_allowance_type` (`allowance_id`),
  CONSTRAINT `fk_employee_allowance_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee_personal_information` (`employee_id`),
  CONSTRAINT `fk_employee_allowance_type` FOREIGN KEY (`allowance_id`) REFERENCES `allowance` (`allowance_id`),
  CONSTRAINT `chk_allowance_amount_non_negative` CHECK ((`amount` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_allowance`
--

LOCK TABLES `employee_allowance` WRITE;
/*!40000 ALTER TABLE `employee_allowance` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_allowance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_employment_information`
--

DROP TABLE IF EXISTS `employee_employment_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_employment_information` (
  `employment_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `job_id` int DEFAULT NULL,
  `salary_id` int DEFAULT NULL,
  `employment_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `employment_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_hired` date DEFAULT NULL,
  PRIMARY KEY (`employment_id`),
  KEY `fk_employment_employee` (`employee_id`),
  KEY `fk_employment_job` (`job_id`),
  KEY `fk_employment_salary` (`salary_id`),
  CONSTRAINT `fk_employment_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee_personal_information` (`employee_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_employment_job` FOREIGN KEY (`job_id`) REFERENCES `job` (`job_id`),
  CONSTRAINT `fk_employment_salary` FOREIGN KEY (`salary_id`) REFERENCES `salary` (`salary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10034 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_employment_information`
--

LOCK TABLES `employee_employment_information` WRITE;
/*!40000 ALTER TABLE `employee_employment_information` DISABLE KEYS */;
INSERT INTO `employee_employment_information` VALUES (10000,10001,10000,10000,'Regular','Active','2000-10-08'),(10001,10002,10001,10001,'Regular','Active','2001-02-09'),(10002,10003,10002,10001,'Regular','Active','2002-12-12'),(10003,10004,10003,10001,'Regular','Active','2003-03-22'),(10004,10005,10004,10002,'Regular','Active','2003-05-21'),(10005,10006,10005,10002,'Regular','Active','2004-03-05'),(10006,10007,10006,10005,'Regular','Active','2004-07-26'),(10007,10008,10007,10008,'Regular','Active','2005-01-27'),(10008,10009,10007,10008,'Regular','Active','2006-09-25'),(10009,10010,10008,10002,'Regular','Active','2007-02-14'),(10010,10011,10009,10003,'Regular','Active','2007-11-19'),(10011,10012,10010,10006,'Regular','Active','2008-07-14'),(10012,10013,10011,10009,'Regular','Active','2009-04-22'),(10013,10014,10011,10009,'Regular','Active','2009-06-30'),(10014,10015,10012,10004,'Regular','Active','2011-12-30'),(10015,10016,10013,10005,'Regular','Active','2010-11-23'),(10016,10017,10013,10007,'Regular','Active','2012-06-07'),(10017,10018,10014,10008,'Regular','Active','2012-11-05'),(10018,10019,10014,10008,'Regular','Active','2013-09-08'),(10019,10020,10014,10010,'Regular','Active','2014-08-31'),(10020,10021,10014,10010,'Probationary','Active','2015-03-22'),(10021,10022,10014,10009,'Probationary','Active','2015-05-05'),(10022,10023,10014,10008,'Probationary','Active','2016-06-11'),(10023,10024,10014,10008,'Probationary','Active','2016-09-01'),(10024,10025,10014,10009,'Probationary','Active','2017-04-30'),(10025,10026,10014,10011,'Probationary','Active','2018-01-15'),(10026,10027,10014,10011,'Probationary','Active','2018-11-03'),(10027,10028,10014,10009,'Probationary','Active','2019-10-19'),(10028,10029,10014,10008,'Probationary','Active','2020-01-16'),(10029,10030,10014,10008,'Probationary','Active','2021-02-11'),(10030,10031,10014,10008,'Probationary','Active','2021-07-09'),(10031,10032,10015,10002,'Regular','Active','2022-12-04'),(10032,10033,10016,10002,'Regular','Active','2023-03-17'),(10033,10034,10017,10002,'Regular','Active','2016-09-12');
/*!40000 ALTER TABLE `employee_employment_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_government_deduction`
--

DROP TABLE IF EXISTS `employee_government_deduction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_government_deduction` (
  `employee_government_deduction_id` int NOT NULL AUTO_INCREMENT,
  `chart_id` int DEFAULT NULL,
  `payroll_id` int DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `effective_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`employee_government_deduction_id`),
  KEY `fk_gov_deduction_chart` (`chart_id`),
  KEY `fk_gov_deduction_payroll` (`payroll_id`),
  CONSTRAINT `fk_gov_deduction_chart` FOREIGN KEY (`chart_id`) REFERENCES `government_deduction_chart` (`gov_deduction_chart_id`),
  CONSTRAINT `fk_gov_deduction_payroll` FOREIGN KEY (`payroll_id`) REFERENCES `payroll_record` (`payroll_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_government_deduction`
--

LOCK TABLES `employee_government_deduction` WRITE;
/*!40000 ALTER TABLE `employee_government_deduction` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_government_deduction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_government_information`
--

DROP TABLE IF EXISTS `employee_government_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_government_information` (
  `gov_info_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `sss_number` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `philhealth_number` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pagibig_number` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tax_identification_number` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `withholding_tax_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`gov_info_id`),
  UNIQUE KEY `uq_sss_number` (`sss_number`),
  UNIQUE KEY `uq_philhealth_number` (`philhealth_number`),
  UNIQUE KEY `uq_pagibig_number` (`pagibig_number`),
  UNIQUE KEY `uq_tin_number` (`tax_identification_number`),
  KEY `fk_government_info_employee` (`employee_id`),
  CONSTRAINT `fk_government_info_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee_personal_information` (`employee_id`) ON DELETE CASCADE,
  CONSTRAINT `chk_pagibig_format` CHECK (regexp_like(`pagibig_number`,_utf8mb4'^[0-9]{12}$')),
  CONSTRAINT `chk_philhealth_format` CHECK (regexp_like(`philhealth_number`,_utf8mb4'^[0-9]{12}$')),
  CONSTRAINT `chk_sss_number_format` CHECK (regexp_like(`sss_number`,_utf8mb4'^[0-9]{10}$')),
  CONSTRAINT `chk_tin_format` CHECK (regexp_like(`tax_identification_number`,_utf8mb4'^[0-9]{12}$'))
) ENGINE=InnoDB AUTO_INCREMENT=10034 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_government_information`
--

LOCK TABLES `employee_government_information` WRITE;
/*!40000 ALTER TABLE `employee_government_information` DISABLE KEYS */;
INSERT INTO `employee_government_information` VALUES (10000,10001,'4445060573','820126853951','691295330870','442605657000',NULL),(10001,10002,'5220612749','331735646338','663904995411','683102776000',NULL),(10002,10003,'3088704062','177451189665','171519773969','971711280000',NULL),(10003,10004,'4025118150','341911411254','416946776041','876809437000',NULL),(10004,10005,'5055776381','957436191812','952347222457','031702374000',NULL),(10005,10006,'4916320208','382189453145','441093369646','317674022000',NULL),(10006,10007,'4024007141','239192926939','210850209964','672474690000',NULL),(10007,10008,'5544765272','545652640232','211385556888','888572294000',NULL),(10008,10009,'4106446923','708988234853','260107732354','604997793000',NULL),(10009,10010,'6476050544','578114853194','799254095212','525420419000',NULL),(10010,10011,'2696476083','126445315651','218002473454','210805911000',NULL),(10011,10012,'4485634483','431709011012','113071293354','218489737000',NULL),(10012,10013,'4556563750','233693897247','631130283546','210835851000',NULL),(10013,10014,'2720909964','515741057496','101205445886','275792513000',NULL),(10014,10015,'2687683741','308366860059','223057707853','598065761000',NULL),(10015,10016,'4929593126','824187961962','631052853464','103100522000',NULL),(10016,10017,'2720902088','587272469938','719007608464','482259498000',NULL),(10017,10018,'4532513830','745148459521','114901859343','121203336000',NULL),(10018,10019,'4916299002','579253435499','265104358643','122244511000',NULL),(10019,10020,'4916473425','399665157135','260054585575','273970941000',NULL),(10020,10021,'4556171682','606386917510','104907708845','354650951000',NULL),(10021,10022,'5201095706','357451271274','113017988667','187500345000',NULL),(10022,10023,'5298835243','548670482885','360028104576','101558994000',NULL),(10023,10024,'4558663316','953901539995','913108649964','560735732000',NULL),(10024,10025,'4716927930','753800654114','210546661243','841177857000',NULL),(10025,10026,'4095046578','797639382265','210897095686','502995671000',NULL),(10026,10027,'4532981664','810909286264','211274476563','336676445000',NULL),(10027,10028,'4024007194','934389652994','122238077997','210395397000',NULL),(10028,10029,'6011522064','351830469744','212141893454','395032717000',NULL),(10029,10030,'5413310050','465087894112','515012579765','215973013000',NULL),(10030,10031,'5218592531','136451303068','110018813465','599312588000',NULL),(10031,10032,'2671451334','601644902402','697764069311','404768309000',NULL),(10032,10033,'1150629727','380685387212','993372963726','256436296000',NULL),(10033,10034,'2029875015','918460050077','874042259378','911529713000',NULL);
/*!40000 ALTER TABLE `employee_government_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_leave`
--

DROP TABLE IF EXISTS `employee_leave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_leave` (
  `leave_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `leave_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `total_days` decimal(5,2) DEFAULT NULL,
  `request_id` int DEFAULT NULL,
  PRIMARY KEY (`leave_id`),
  KEY `fk_leave_employee` (`employee_id`),
  KEY `fk_leave_request` (`request_id`),
  CONSTRAINT `fk_leave_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee_personal_information` (`employee_id`),
  CONSTRAINT `fk_leave_request` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`) ON DELETE CASCADE,
  CONSTRAINT `chk_total_days_non_negative` CHECK ((`total_days` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_leave`
--

LOCK TABLES `employee_leave` WRITE;
/*!40000 ALTER TABLE `employee_leave` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_leave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_personal_information`
--

DROP TABLE IF EXISTS `employee_personal_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_personal_information` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthday` date DEFAULT NULL,
  `phone_number` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `uq_employee_email` (`email`),
  CONSTRAINT `chk_personal_phone_numeric` CHECK (regexp_like(`phone_number`,_utf8mb4'^[0-9]+$'))
) ENGINE=InnoDB AUTO_INCREMENT=10035 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_personal_information`
--

LOCK TABLES `employee_personal_information` WRITE;
/*!40000 ALTER TABLE `employee_personal_information` DISABLE KEYS */;
INSERT INTO `employee_personal_information` VALUES (10001,'Manuel III','Garcia','1983-10-11','09171234567','mgarcia@motorph.com','Valero Carpark Building Valero Street 1227, Makati City'),(10002,'Antonio','Lim','1988-06-19','09282341234','alim@motorph.com','San Antonio De Padua 2, Block 1 Lot 8 and 2, Dasmarinas, Cavite'),(10003,'Bianca Sofia','Aquino','1989-08-04','09393456789','baquino@motorph.com','Rm. 402 4/F Jiao Building Timog Avenue Cor. Quezon Avenue 1100, Quezon City'),(10004,'Isabella','Reyes','1994-06-16','09454567890','ireyes@motorph.com','460 Solanda Street Intramuros 1000, Manila'),(10005,'Eduard','Hernandez','1989-09-23','09565678901','ehernandez@motorph.com','National Highway, Gingoog,  Misamis Occidental'),(10006,'Andrea Mae','Villanueva','1988-02-14','09676789012','avillanueva@motorph.com','17/85 Stracke Via Suite 042, Poblacion, Las Piï¿½as 4783 Dinagat Islands '),(10007,'Brad','San Jose','1996-03-15','09787890123','bsanjose@motorph.com','99 Strosin Hills, Poblacion, Bislig 5340 Tawi-Tawi'),(10008,'Alice','Romualdez','1992-05-14','09998901234','aromualdez@motorph.com','12A/33 Upton Isle Apt. 420, Roxas City 1814 Surigao del Norte '),(10009,'Rosie','Atienza','1948-09-24','09159012345','ratienza@motorph.com','90A Dibbert Terrace Apt. 190, San Lorenzo 6056 Davao del Norte'),(10010,'Roderick','Alvaro','1988-03-30','09230123456','ralvaro@motorph.com','#284 T. Morato corner, Scout Rallos Street, Quezon City'),(10011,'Anthony','Salcedo','1993-09-14','09301234567','asalcedo@motorph.com','93/54 Shanahan Alley Apt. 183, Santo Tomas 1572 Masbate'),(10012,'Josie','Lopez','1987-01-14','09472345678','jlopez@motorph.com','49 Springs Apt. 266, Poblacion, Taguig 3200 Occidental Mindoro'),(10013,'Martha','Farala','1942-01-11','09583456789','mfarala@motorph.com','42/25 Sawayn Stream, Ubay 1208 Zamboanga del Norte '),(10014,'Leila','Martinez','1970-07-11','09634567890','lmartinez@motorph.com','37/46 Kulas Roads, Maragondon 0962 Quirino '),(10015,'Fredrick','Romualdez','1985-03-10','09755678901','fromualdez@motorph.com','22A/52 Lubowitz Meadows, Pililla 4895 Zambales'),(10016,'Christian','Mata','1987-10-21','09755678902','cmata@motorph.com','90 O\'Keefe Spur Apt. 379, Catigbian 2772 Sulu'),(10017,'Selena','De Leon','1987-10-21','09187890123','sdeleon@motorph.com','89A Armstrong Trace, Compostela 7874 Maguindanao'),(10018,'Allison','San Jose','1975-02-20','09298901234','asanjose@motorph.com','08 Grant Drive Suite 406, Poblacion, Iloilo City 9186 La Union'),(10019,'Cydney','Rosario','1986-06-24','09369012345','crosario@motorph.com','93A/21 Berge Points, Tapaz 2180 Quezon'),(10020,'Mark','Bautista','1996-10-06','09480123456','mbautista@motorph.com','65 Murphy Center Suite 094, Poblacion, Palayan 5636 Quirino'),(10021,'Darlene','Lazaro','1991-02-12','09591234567','dlazaro@motorph.com','47A/94 Larkin Plaza Apt. 179, Poblacion, Caloocan 2751 Quirino'),(10022,'Kolby','Delos Santos','1985-11-25','09642345678','kdelossantos@motorph.com','06A Gulgowski Extensions, Bongabon 6085 Zamboanga del Sur'),(10023,'Vella','Santos','1980-02-26','09733456789','vsantos@motorph.com','99A Padberg Spring, Poblacion, Mabalacat 3959 Lanao del Sur'),(10024,'Tomas','Del Rosario','1983-12-31','09924567890','tdelrosario@motorph.com','80A/48 Ledner Ridges, Poblacion, Kabankalan 8870 Marinduque'),(10025,'Jacklyn','Tolentino','1978-12-18','09165678901','jtolentino@motorph.com','96/48 Watsica Flats Suite 734, Poblacion, Malolos 1844 Ifugao'),(10026,'Percival','Gutierrez','1984-05-19','09276789012','pgutierrez@motorph.com','58A Wilderman Walks, Poblacion, Digos 5822 Davao del Sur'),(10027,'Garfield','Manalaysay','1970-12-18','09387890123','gmanalaysay@motorph.com','60 Goyette Valley Suite 219, Poblacion, Tabuk 3159 Lanao del Sur'),(10028,'Lizeth','Villegas','1986-08-28','09498901234','lvillegas@motorph.com','66/77 Mann Views, Luisiana 1263 Dinagat Islands'),(10029,'Carol','Ramos','1981-12-12','09559012345','cramos@motorph.com','72/70 Stamm Spurs, Bustos 4550 Iloilo'),(10030,'Emelia','Maceda','1978-08-20','09680123456','emaceda@motorph.com','50A/83 Bahringer Oval Suite 145, Kiamba 7688 Nueva Ecija'),(10031,'Delia','Aguilar','1973-04-14','09741234567','daguilar@motorph.com','95 Cremin Junction, Surallah 2809 Cotabato'),(10032,'John Rafael','Castro','1989-01-27','09932345678','jcastro@motorph.com','Hi-way, Yati, Liloan Cebu'),(10033,'Carlos Ian','Martinez','1992-02-09','09193456789','cmartinez@motorph.com','Bulala, Camalaniugan'),(10034,'Beatriz','Santos','1990-11-16','09254567890','bsantos@motorph.com','Agapita Building, Metro Manila');
/*!40000 ALTER TABLE `employee_personal_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `government_deduction_chart`
--

DROP TABLE IF EXISTS `government_deduction_chart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `government_deduction_chart` (
  `gov_deduction_chart_id` int NOT NULL AUTO_INCREMENT,
  `gov_deduction_type_id` int DEFAULT NULL,
  `minimum_range` decimal(10,2) NOT NULL,
  `maximum_range` decimal(10,2) DEFAULT NULL,
  `fixed_amount` decimal(10,2) DEFAULT NULL,
  `employee_deduction_rate` decimal(5,2) DEFAULT NULL,
  `employer_contribution_share` decimal(5,2) DEFAULT NULL,
  `has_contribution_cap` tinyint(1) NOT NULL DEFAULT '0',
  `min_contribution` decimal(10,2) DEFAULT NULL,
  `max_contribution` decimal(10,2) DEFAULT NULL,
  `effective_at` date NOT NULL,
  `updated_at` date DEFAULT NULL,
  `notes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`gov_deduction_chart_id`),
  KEY `fk_gov_deduction_type` (`gov_deduction_type_id`),
  CONSTRAINT `fk_gov_deduction_type` FOREIGN KEY (`gov_deduction_type_id`) REFERENCES `deduction_chart_type` (`chart_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `chk_employee_deduction_rate_valid` CHECK ((`employee_deduction_rate` between 0 and 100)),
  CONSTRAINT `chk_employer_contribution_rate_maximum` CHECK ((`employer_contribution_share` <= 100)),
  CONSTRAINT `chk_maximum_range_greater_than_minimum` CHECK ((`maximum_range` > `minimum_range`)),
  CONSTRAINT `chk_minimum_range_non_negative` CHECK ((`minimum_range` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `government_deduction_chart`
--

LOCK TABLES `government_deduction_chart` WRITE;
/*!40000 ALTER TABLE `government_deduction_chart` DISABLE KEYS */;
INSERT INTO `government_deduction_chart` VALUES (1,1,0.00,3250.00,135.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'Below 3,250'),(2,1,3250.00,3750.00,157.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'3250 - 3750'),(3,1,3750.00,4250.00,180.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'3750 - 4250'),(4,1,4250.00,4750.00,202.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'4250 - 4750'),(5,1,4750.00,5250.00,225.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'4750 - 5250'),(6,1,5250.00,5750.00,247.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'5250 - 5750'),(7,1,5750.00,6250.00,270.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'5750 - 6250'),(8,1,6250.00,6750.00,292.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'6250 - 6750'),(9,1,6750.00,7250.00,315.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'6750 - 7250'),(10,1,7250.00,7750.00,337.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'7250 - 7750'),(11,1,7750.00,8250.00,360.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'7750 - 8250'),(12,1,8250.00,8750.00,382.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'8250 - 8750'),(13,1,8750.00,9250.00,405.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'8750 - 9250'),(14,1,9250.00,9750.00,427.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'9250 - 9750'),(15,1,9750.00,10250.00,450.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'9750 - 10250'),(16,1,10250.00,10750.00,472.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'10250 - 10750'),(17,1,10750.00,11250.00,495.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'10750 - 11250'),(18,1,11250.00,11750.00,517.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'11250 - 11750'),(19,1,11750.00,12250.00,540.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'11750 - 12250'),(20,1,12250.00,12750.00,562.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'12250 - 12750'),(21,1,12750.00,13250.00,585.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'12750 - 13250'),(22,1,13250.00,13750.00,607.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'13250 - 13750'),(23,1,13750.00,14250.00,630.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'13750 - 14250'),(24,1,14250.00,14750.00,652.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'14250 - 14750'),(25,1,14750.00,15250.00,675.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'14750 - 15250'),(26,1,15250.00,15750.00,697.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'15250 - 15750'),(27,1,15750.00,16250.00,720.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'15750 - 16250'),(28,1,16250.00,16750.00,742.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'16250 - 16750'),(29,1,16750.00,17250.00,765.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'16750 - 17250'),(30,1,17250.00,17750.00,787.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'17250 - 17750'),(31,1,17750.00,18250.00,810.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'17750 - 18250'),(32,1,18250.00,18750.00,832.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'18250 - 18750'),(33,1,18750.00,19250.00,855.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'18750 - 19250'),(34,1,19250.00,19750.00,877.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'19250 - 19750'),(35,1,19750.00,20250.00,900.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'19750 - 20250'),(36,1,20250.00,20750.00,922.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'20250 - 20750'),(37,1,20750.00,21250.00,945.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'20750 - 21250'),(38,1,21250.00,21750.00,967.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'21250 - 21750'),(39,1,21750.00,22250.00,990.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'21750 - 22250'),(40,1,22250.00,22750.00,1012.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'22250 - 22750'),(41,1,22750.00,23250.00,1035.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'22750 - 23250'),(42,1,23250.00,23750.00,1057.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'23250 - 23750'),(43,1,23750.00,24250.00,1080.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'23750 - 24250'),(44,1,24250.00,24750.00,1102.50,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'24250 - 24750'),(45,1,24750.00,NULL,1125.00,NULL,NULL,0,NULL,NULL,'2025-01-01',NULL,'24750 - Over'),(46,2,0.00,10000.00,NULL,3.00,50.00,1,300.00,NULL,'2025-01-01',NULL,'Premium varies with salary and minimum at 300'),(47,2,10000.01,60000.00,NULL,3.00,50.00,1,NULL,1800.00,'2025-01-01',NULL,'Max premium capped at 1800'),(48,3,0.00,1499.00,NULL,1.00,2.00,0,NULL,NULL,'2025-01-01',NULL,'Contribution capped at 100 PHP'),(49,3,1500.00,NULL,NULL,2.00,2.00,1,NULL,100.00,'2025-01-01',NULL,'Contribution capped at 100 PHP'),(50,4,0.00,20832.00,0.00,0.00,NULL,0,NULL,NULL,'2025-01-01',NULL,'No withholding tax'),(51,4,20833.00,33332.99,0.00,20.00,NULL,0,NULL,NULL,'2025-01-01',NULL,'20% on excess over 20,833'),(52,4,33333.00,66666.99,2500.00,25.00,NULL,0,NULL,NULL,'2025-01-01',NULL,'₱2,500 + 25% on excess over 33,333'),(53,4,66667.00,166666.99,10833.00,30.00,NULL,0,NULL,NULL,'2025-01-01',NULL,'₱10,833 + 30% on excess over 66,667'),(54,4,166667.00,666666.99,40833.33,32.00,NULL,0,NULL,NULL,'2025-01-01',NULL,'₱40,833.33 + 32% on excess over 166,667'),(55,4,666667.00,NULL,200833.33,35.00,NULL,0,NULL,NULL,'2025-01-01',NULL,'₱200,833.33 + 35% on excess over 666,667');
/*!40000 ALTER TABLE `government_deduction_chart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `job_id` int NOT NULL AUTO_INCREMENT,
  `job_title` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department_id` int DEFAULT NULL,
  PRIMARY KEY (`job_id`),
  UNIQUE KEY `uq_job_title` (`job_title`),
  KEY `fk_job_department` (`department_id`),
  CONSTRAINT `fk_job_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10018 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (10000,'Chief Executive Officer',1),(10001,'Chief Operating Officer',2),(10002,'Chief Finance Officer',3),(10003,'Chief Marketing Officer',4),(10004,'IT Operations and Systems',5),(10005,'HR Manager',6),(10006,'HR Team Leader',6),(10007,'HR Rank and File',6),(10008,'Accounting Head',7),(10009,'Payroll Manager',8),(10010,'Payroll Team Leader',8),(10011,'Payroll Rank and File',8),(10012,'Account Manager',9),(10013,'Account Team Leader',9),(10014,'Account Rank and File',9),(10015,'Sales & Marketing',10),(10016,'Supply Chain and Logistics',11),(10017,'Customer Service and Relations',12);
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `overtime`
--

DROP TABLE IF EXISTS `overtime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `overtime` (
  `overtime_id` int NOT NULL AUTO_INCREMENT,
  `request_id` int DEFAULT NULL,
  `employee_id` int DEFAULT NULL,
  `dtr_id` int DEFAULT NULL,
  PRIMARY KEY (`overtime_id`),
  KEY `fk_overtime_employee` (`employee_id`),
  KEY `fk_overtime_request` (`request_id`),
  KEY `fk_overtime_dtr` (`dtr_id`),
  CONSTRAINT `fk_overtime_dtr` FOREIGN KEY (`dtr_id`) REFERENCES `daily_time_record` (`dtr_id`),
  CONSTRAINT `fk_overtime_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee_personal_information` (`employee_id`),
  CONSTRAINT `fk_overtime_request` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `overtime`
--

LOCK TABLES `overtime` WRITE;
/*!40000 ALTER TABLE `overtime` DISABLE KEYS */;
/*!40000 ALTER TABLE `overtime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay_period`
--

DROP TABLE IF EXISTS `pay_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pay_period` (
  `pay_period_id` int NOT NULL AUTO_INCREMENT,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `pay_day` date DEFAULT NULL,
  `payroll_due` date DEFAULT NULL,
  PRIMARY KEY (`pay_period_id`),
  CONSTRAINT `chk_pay_period_due_before_payday` CHECK ((`payroll_due` <= `pay_day`)),
  CONSTRAINT `chk_pay_period_end_after_start` CHECK ((`end_date` > `start_date`)),
  CONSTRAINT `chk_pay_period_payday_after_end` CHECK ((`pay_day` > `end_date`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay_period`
--

LOCK TABLES `pay_period` WRITE;
/*!40000 ALTER TABLE `pay_period` DISABLE KEYS */;
/*!40000 ALTER TABLE `pay_period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payroll_record`
--

DROP TABLE IF EXISTS `payroll_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payroll_record` (
  `payroll_id` int NOT NULL AUTO_INCREMENT,
  `pay_period_id` int DEFAULT NULL,
  `employee_id` int DEFAULT NULL,
  `attendance_processing_id` int DEFAULT NULL,
  `employee_allowance_id` int DEFAULT NULL,
  `employee_government_deduction_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `submitted_date` date DEFAULT NULL,
  PRIMARY KEY (`payroll_id`),
  KEY `fk_payroll_period` (`pay_period_id`),
  KEY `fk_payroll_employee` (`employee_id`),
  KEY `fk_payroll_attendance` (`attendance_processing_id`),
  KEY `fk_payroll_allowance` (`employee_allowance_id`),
  KEY `fk_payroll_deduction` (`employee_government_deduction_id`),
  CONSTRAINT `fk_payroll_allowance` FOREIGN KEY (`employee_allowance_id`) REFERENCES `employee_allowance` (`employee_allowance_id`),
  CONSTRAINT `fk_payroll_attendance` FOREIGN KEY (`attendance_processing_id`) REFERENCES `attendance_processing` (`attendance_processing_id`),
  CONSTRAINT `fk_payroll_deduction` FOREIGN KEY (`employee_government_deduction_id`) REFERENCES `employee_government_deduction` (`employee_government_deduction_id`),
  CONSTRAINT `fk_payroll_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee_personal_information` (`employee_id`),
  CONSTRAINT `fk_payroll_period` FOREIGN KEY (`pay_period_id`) REFERENCES `pay_period` (`pay_period_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll_record`
--

LOCK TABLES `payroll_record` WRITE;
/*!40000 ALTER TABLE `payroll_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payslip`
--

DROP TABLE IF EXISTS `payslip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payslip` (
  `payslip_id` int NOT NULL AUTO_INCREMENT,
  `payroll_id` int DEFAULT NULL,
  `employee_id` int DEFAULT NULL,
  `position` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `monthly_rate` decimal(10,2) DEFAULT NULL,
  `daily_rate` decimal(10,2) DEFAULT NULL,
  `days_worked` int DEFAULT NULL,
  `overtime` decimal(5,2) DEFAULT NULL,
  `gross_salary` decimal(10,2) DEFAULT NULL,
  `employee_allowance_id` int DEFAULT NULL,
  `total_allowance` decimal(10,2) DEFAULT NULL,
  `employee_government_deduction_id` int DEFAULT NULL,
  `total_deductions` decimal(10,2) DEFAULT NULL,
  `net_salary` decimal(10,2) DEFAULT NULL,
  `generated_date` date DEFAULT NULL,
  PRIMARY KEY (`payslip_id`),
  KEY `fk_payslip_payroll` (`payroll_id`),
  KEY `fk_payslip_employee` (`employee_id`),
  KEY `fk_payslip_allowance` (`employee_allowance_id`),
  KEY `fk_payslip_deduction` (`employee_government_deduction_id`),
  CONSTRAINT `fk_payslip_allowance` FOREIGN KEY (`employee_allowance_id`) REFERENCES `employee_allowance` (`employee_allowance_id`),
  CONSTRAINT `fk_payslip_deduction` FOREIGN KEY (`employee_government_deduction_id`) REFERENCES `employee_government_deduction` (`employee_government_deduction_id`),
  CONSTRAINT `fk_payslip_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee_personal_information` (`employee_id`),
  CONSTRAINT `fk_payslip_payroll` FOREIGN KEY (`payroll_id`) REFERENCES `payroll_record` (`payroll_id`),
  CONSTRAINT `chk_payslip_net_salary_non_negative` CHECK ((`net_salary` >= 0)),
  CONSTRAINT `chk_payslip_total_deductions_non_negative` CHECK ((`total_deductions` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payslip`
--

LOCK TABLES `payslip` WRITE;
/*!40000 ALTER TABLE `payslip` DISABLE KEYS */;
/*!40000 ALTER TABLE `payslip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `request_date` date DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `request_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'Pending',
  `created_at` timestamp NULL DEFAULT NULL,
  `processed_by` int DEFAULT NULL,
  `processed_date` date DEFAULT NULL,
  `remarks` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `fk_request_employee` (`employee_id`),
  KEY `fk_processed_by` (`processed_by`),
  CONSTRAINT `fk_processed_by` FOREIGN KEY (`processed_by`) REFERENCES `employee_personal_information` (`employee_id`),
  CONSTRAINT `fk_request_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee_personal_information` (`employee_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_access`
--

DROP TABLE IF EXISTS `role_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_access` (
  `role_access_id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL,
  `access_id` int DEFAULT NULL,
  PRIMARY KEY (`role_access_id`),
  UNIQUE KEY `uq_role_access_mapping` (`role_id`,`access_id`),
  KEY `fk_role_access_access` (`access_id`),
  CONSTRAINT `fk_role_access_access` FOREIGN KEY (`access_id`) REFERENCES `access` (`access_id`),
  CONSTRAINT `fk_role_access_user_role` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_access`
--

LOCK TABLES `role_access` WRITE;
/*!40000 ALTER TABLE `role_access` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salary` (
  `salary_id` int NOT NULL AUTO_INCREMENT,
  `salary_grade` int NOT NULL,
  `basic_salary` decimal(10,2) NOT NULL,
  `gross_semi_monthly_rate` decimal(10,2) NOT NULL,
  `hourly_rate` decimal(10,2) NOT NULL,
  PRIMARY KEY (`salary_id`),
  CONSTRAINT `chk_basic_salary_positive` CHECK ((`basic_salary` > 0)),
  CONSTRAINT `chk_gross_semi_monthly_rate_positive` CHECK ((`gross_semi_monthly_rate` > 0)),
  CONSTRAINT `chk_hourly_rate_positive` CHECK ((`hourly_rate` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=10012 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` VALUES (10000,5,90000.00,45000.00,535.71),(10001,5,60000.00,30000.00,357.14),(10002,3,52670.00,26335.00,313.51),(10003,3,50825.00,25413.00,302.53),(10004,3,53500.00,26750.00,318.45),(10005,2,42975.00,21488.00,255.80),(10006,2,38475.00,19238.00,229.02),(10007,2,41850.00,20925.00,249.11),(10008,1,22500.00,11250.00,133.93),(10009,1,24000.00,12000.00,142.86),(10010,1,23250.00,116625.00,138.39),(10011,1,24750.00,12375.00,147.32);
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supervisor_assignment`
--

DROP TABLE IF EXISTS `supervisor_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supervisor_assignment` (
  `supervisor_assignment_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `supervisor_id` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`supervisor_assignment_id`),
  KEY `fk_supervisor_employee` (`employee_id`),
  KEY `fk_supervisor_supervisor` (`supervisor_id`),
  CONSTRAINT `fk_supervisor_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee_personal_information` (`employee_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_supervisor_supervisor` FOREIGN KEY (`supervisor_id`) REFERENCES `employee_personal_information` (`employee_id`) ON DELETE SET NULL,
  CONSTRAINT `chk_supervision_period` CHECK ((`end_date` > `start_date`))
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supervisor_assignment`
--

LOCK TABLES `supervisor_assignment` WRITE;
/*!40000 ALTER TABLE `supervisor_assignment` DISABLE KEYS */;
/*!40000 ALTER TABLE `supervisor_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password_hashed` char(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `employee_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `account_created` timestamp NULL DEFAULT NULL,
  `last_login` timestamp NULL DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_system_user_employee` (`employee_id`),
  KEY `fk_system_user_role` (`role_id`),
  CONSTRAINT `fk_system_user_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee_personal_information` (`employee_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_system_user_role` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user`
--

LOCK TABLES `system_user` WRITE;
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_description` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `uq_user_role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-27 14:45:11
