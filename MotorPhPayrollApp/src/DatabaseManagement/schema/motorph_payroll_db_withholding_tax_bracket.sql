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
-- Table structure for table `withholding_tax_bracket`
--

DROP TABLE IF EXISTS `withholding_tax_bracket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `withholding_tax_bracket` (
  `withholding_tax_bracket_id` int NOT NULL AUTO_INCREMENT,
  `min_salary` decimal(10,2) NOT NULL,
  `max_salary` decimal(10,2) DEFAULT NULL,
  `base_tax_amount` decimal(10,2) NOT NULL,
  `tax_rate` decimal(5,2) NOT NULL,
  `effective_date` date NOT NULL,
  `notes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`withholding_tax_bracket_id`),
  CONSTRAINT `chk_wtb_base_tax_amount_non_negative` CHECK ((`base_tax_amount` >= 0)),
  CONSTRAINT `chk_wtb_max_salary_logical` CHECK (((`max_salary` is null) or (`max_salary` >= `min_salary`))),
  CONSTRAINT `chk_wtb_min_salary_non_negative` CHECK ((`min_salary` >= 0)),
  CONSTRAINT `chk_wtb_tax_rate_non_negative` CHECK ((`tax_rate` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `withholding_tax_bracket`
--

LOCK TABLES `withholding_tax_bracket` WRITE;
/*!40000 ALTER TABLE `withholding_tax_bracket` DISABLE KEYS */;
INSERT INTO `withholding_tax_bracket` VALUES (1,0.00,20832.00,0.00,0.00,'2025-01-01','No withholding tax'),(2,20833.00,33332.99,0.00,0.20,'2025-01-01','20% on excess over 20,833'),(3,33333.00,66666.99,2500.00,0.25,'2025-01-01','₱2,500 + 25% on excess over 33,333'),(4,66667.00,166666.99,10833.00,0.30,'2025-01-01','₱10,833 + 30% on excess over 66,667'),(5,166667.00,666666.99,40833.33,0.32,'2025-01-01','₱40,833.33 + 32% on excess over 166,667'),(6,666667.00,NULL,200833.33,0.35,'2025-01-01','₱200,833.33 + 35% on excess over 666,667');
/*!40000 ALTER TABLE `withholding_tax_bracket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-24 20:01:03
