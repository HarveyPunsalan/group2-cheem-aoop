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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-27 14:46:33
