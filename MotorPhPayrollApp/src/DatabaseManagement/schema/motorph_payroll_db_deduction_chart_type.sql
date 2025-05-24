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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-24 20:01:02
