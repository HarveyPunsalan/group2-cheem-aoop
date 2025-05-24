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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-24 20:01:00
