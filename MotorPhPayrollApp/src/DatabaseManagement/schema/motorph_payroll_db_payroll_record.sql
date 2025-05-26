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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-24 20:01:01
