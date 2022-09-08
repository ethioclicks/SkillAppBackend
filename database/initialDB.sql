CREATE DATABASE  IF NOT EXISTS `betdelala_database` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `betdelala_database`;
-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: localhost    Database: betdelala_database
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `ROLE`
--

DROP TABLE IF EXISTS `ROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ROLE` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLE`
--

LOCK TABLES `ROLE` WRITE;
/*!40000 ALTER TABLE `ROLE` DISABLE KEYS */;
INSERT INTO `ROLE` VALUES (1,'USER'),(2,'ADMIN');
/*!40000 ALTER TABLE `ROLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLE_USER`
--

DROP TABLE IF EXISTS `ROLE_USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ROLE_USER` (
  `USER_ID` int NOT NULL,
  `ROLE_ID` int NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `FKb4194r0gikvfqkbpepyhprsuw` (`ROLE_ID`),
  CONSTRAINT `FK4st7m766yqangwhcgp714o5sx` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FKb4194r0gikvfqkbpepyhprsuw` FOREIGN KEY (`ROLE_ID`) REFERENCES `ROLE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLE_USER`
--

LOCK TABLES `ROLE_USER` WRITE;
/*!40000 ALTER TABLE `ROLE_USER` DISABLE KEYS */;
INSERT INTO `ROLE_USER` VALUES (1,1),(28,1),(2,2);
/*!40000 ALTER TABLE `ROLE_USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USER` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `CREATED_ON` datetime DEFAULT NULL,
  `FIRST_NAME` varchar(255) DEFAULT NULL,
  `IS_ENABLED` bit(1) NOT NULL,
  `IS_LOCKED` bit(1) NOT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONE_NUMBER` varchar(255) DEFAULT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `USER_PUBLIC_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES (1,'2021-01-06 22:04:18','Alemayew',_binary '',_binary '\0','testma','$2a$10$4jJgDv3Wbr3iKJ0jXPgaT.TwlwZlg62EzMR3Ow3HJUVA2DtGzLKiK','4699871814','user@gmail.com','6b04b0b7-9011-4606-82de-e53c708b58c6'),(2,'2021-01-06 22:04:25','Miftah',_binary '',_binary '\0','Kemal ','$2a$10$KBs7tTAptFk7hhwWBlA27OAa/dWM2YsJIN3Ow8uny.zyoGKEgNseW','4699871815','admin@gmail.com','9de2535d-43c8-42e6-b423-51d2e475c037'),(28,'2021-10-18 19:47:17','ezedin',_binary '',_binary '\0','wangoria','$2a$10$Hmg4GiAIhFBq9YSgy8FsCudgvLw7YHimrJiegbgJGnja/KfPFrqm2','1234567890','ezedin@gmail.com','4bef565b-245d-47f8-9fac-a0ea723d2a04');
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-18 15:00:15
