-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: nic_dataservice
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
-- Table structure for table `filedetail`
--

DROP TABLE IF EXISTS `filedetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filedetail` (
  `fileId` char(5) NOT NULL,
  `UserId` char(5) DEFAULT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `uploadDate` date DEFAULT NULL,
  PRIMARY KEY (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filedetail`
--

LOCK TABLES `filedetail` WRITE;
/*!40000 ALTER TABLE `filedetail` DISABLE KEYS */;
INSERT INTO `filedetail` VALUES ('F001','U008','Untitled spreadsheet - Sheet1.csv','2026-03-13'),('F002','U007','moc-set-4 - Sheet1 (2).csv','2026-03-14'),('F003','U009','MOC-Set-4 - Sheet1 (3).csv','2026-03-15'),('F004','U009','moc-Data-5 - Sheet1.csv','2026-03-15'),('F005','U009','moc-Data-5 - Sheet1.csv','2026-03-15'),('F006','U009','moc-Data-5 - Sheet1.csv','2026-03-15'),('F007','U009','moc-Data-5 - Sheet1.csv','2026-03-15'),('F008','U002','moc-Data-6 - Sheet1.csv','2026-03-15'),('F009','U002','moc-Data-6 - Sheet1 (1).csv','2026-03-15'),('F010','U002','MOC-set-07 - Sheet1.csv','2026-03-16'),('F011','U009','MOC-set-07 - Sheet1.csv','2026-03-17'),('F012','U009','moc-Data-6 - Sheet1 (1).csv','2026-03-17'),('F013','U009','MOC-set-07 - Sheet1.csv','2026-03-17');
/*!40000 ALTER TABLE `filedetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nicdetail`
--

DROP TABLE IF EXISTS `nicdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nicdetail` (
  `NICNumber` varchar(255) NOT NULL,
  `UserId` char(5) DEFAULT NULL,
  `fileId` char(5) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `birthYear` date DEFAULT NULL,
  PRIMARY KEY (`NICNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nicdetail`
--

LOCK TABLES `nicdetail` WRITE;
/*!40000 ALTER TABLE `nicdetail` DISABLE KEYS */;
INSERT INTO `nicdetail` VALUES ('199814001234','U009','F011','Male','1998-05-19'),('200126508899','U009','F012','Male','2001-09-21'),('200272705678','U009','F011','Female','2002-05-29'),('625894455V','U009','F012','Female','1962-03-29'),('753669876V','U009','F011','Male','1975-12-31'),('820691234V','U009','F011','Male','1982-03-09'),('948104567V','U009','F011','Female','1994-11-05');
/*!40000 ALTER TABLE `nicdetail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-17  9:51:38
