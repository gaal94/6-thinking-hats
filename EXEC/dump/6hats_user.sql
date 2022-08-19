CREATE DATABASE  IF NOT EXISTS `6hats` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `6hats`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: i7a709.p.ssafy.io    Database: 6hats
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `birth` date DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `gender` varchar(5) NOT NULL,
  `is_active` bit(1) NOT NULL DEFAULT b'1',
  `job` varchar(20) NOT NULL,
  `login_type` varchar(10) NOT NULL DEFAULT 'GENERAL',
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `profile_image_url` varchar(100) NOT NULL DEFAULT 'basic',
  `user_type` varchar(5) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (11,'1997-03-17','a@a.com','MAN',_binary '','JOBLESS','GENERAL','kkk1','ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb','basic','USER'),(15,'1996-06-05','weejee@gmail.com','WOMAN',_binary '','JOBLESS','GENERAL','최지원','961b6dd3ede3cb8ecbaacbd68de040cd78eb2ed5889130cceb4c49268ea4d506','basic','USER'),(16,'1997-03-17','osj2387@naver.com','MAN',_binary '','STUDENT','GENERAL','오성준','0ad9b15887b1e35156dc78be1619daa14f482ed6704c78a7b77eba4adf43ffa6','gL2z907v4j','USER'),(17,'1997-01-22','tnfls7070@naver.com','WOMAN',_binary '','JOBLESS','GENERAL','권수린','8a295d0a9d0362257451b3993dab6a43927d879869726bc76d3dd7bfa845a546','basic','USER'),(18,'2022-08-01','hsy@asdf.com','MAN',_binary '','STUDENT','GENERAL','건담','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','su6G1c1Sgv','USER'),(19,'2022-08-02','admin','MAN',_binary '','OFFICE','GENERAL','admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','9Uc20Kklmx','ADMIN'),(20,'2022-08-19','chlqudtjd@naver.com','MAN',_binary '','OTHER','GENERAL','최병성','c80e0588b4857d237c75d5e6c38ce1015bcfc25ec18880b36c14eeca8f610025','basic','USER'),(21,'1995-06-28','heua717@naver.com','MAN',_binary '','JOBLESS','GENERAL','김성진','ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb','basic','USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 10:29:13
