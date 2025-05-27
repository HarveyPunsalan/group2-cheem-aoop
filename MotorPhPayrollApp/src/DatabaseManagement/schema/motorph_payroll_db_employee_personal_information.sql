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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-27 14:46:33
