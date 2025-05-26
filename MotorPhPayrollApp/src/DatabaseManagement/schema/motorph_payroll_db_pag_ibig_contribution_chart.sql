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
-- Table structure for table `pag_ibig_contribution_chart`
--

DROP TABLE IF EXISTS `pag_ibig_contribution_chart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pag_ibig_contribution_chart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `min_salary` decimal(10,2) NOT NULL,
  `max_salary` decimal(10,2) DEFAULT NULL,
  `employee_rate` decimal(5,2) NOT NULL,
  `employer_rate` decimal(5,2) NOT NULL,
  `max_contribution` decimal(10,2) NOT NULL,
  `effective_date` date NOT NULL,
  `notes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `chk_pibig_employee_rate_non_negative` CHECK ((`employee_rate` >= 0)),
  CONSTRAINT `chk_pibig_employer_rate_non_negative` CHECK ((`employer_rate` >= 0)),
  CONSTRAINT `chk_pibig_max_contribution_non_negative` CHECK ((`max_contribution` >= 0)),
  CONSTRAINT `chk_pibig_max_salary_logical` CHECK (((`max_salary` is null) or (`max_salary` >= `min_salary`))),
  CONSTRAINT `chk_pibig_min_salary_non_negative` CHECK ((`min_salary` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pag_ibig_contribution_chart`
--

LOCK TABLES `pag_ibig_contribution_chart` WRITE;
/*!40000 ALTER TABLE `pag_ibig_contribution_chart` DISABLE KEYS */;
INSERT INTO `pag_ibig_contribution_chart` VALUES (1,1000.00,1500.00,0.01,0.02,100.00,'2025-01-01','Contribution capped at 100 PHP'),(2,1500.01,NULL,0.02,0.02,100.00,'2025-01-01','Max contribution amount');
/*!40000 ALTER TABLE `pag_ibig_contribution_chart` ENABLE KEYS */;
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
