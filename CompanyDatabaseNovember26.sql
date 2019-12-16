-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: database1
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `department_table`
--

DROP TABLE IF EXISTS `department_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department_table` (
  `Department_ID` int(11) NOT NULL,
  `Department_Name` varchar(20) NOT NULL,
  PRIMARY KEY (`Department_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_table`
--

LOCK TABLES `department_table` WRITE;
/*!40000 ALTER TABLE `department_table` DISABLE KEYS */;
INSERT INTO `department_table` VALUES (301,'Sales'),(302,'Marketing'),(303,'Public Relation'),(304,'Accounting'),(305,'IT'),(306,'Maintainance');
/*!40000 ALTER TABLE `department_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_project_table`
--

DROP TABLE IF EXISTS `emp_project_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp_project_table` (
  `Employee_ID` int(11) NOT NULL,
  `Project_ID` int(11) NOT NULL,
  PRIMARY KEY (`Employee_ID`,`Project_ID`),
  KEY `Project ID FK_idx` (`Project_ID`),
  CONSTRAINT `Employee ID FK` FOREIGN KEY (`Employee_ID`) REFERENCES `employee_table` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Project ID FK` FOREIGN KEY (`Project_ID`) REFERENCES `project_table` (`Project_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_project_table`
--

LOCK TABLES `emp_project_table` WRITE;
/*!40000 ALTER TABLE `emp_project_table` DISABLE KEYS */;
INSERT INTO `emp_project_table` VALUES (1001,401),(1003,401),(1005,401),(1007,401),(1002,402),(1004,402),(1006,402),(1008,402),(1014,403);
/*!40000 ALTER TABLE `emp_project_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_table`
--

DROP TABLE IF EXISTS `employee_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_table` (
  `Employee_ID` int(11) NOT NULL,
  `First_Name` varchar(20) NOT NULL,
  `Last_Name` varchar(20) DEFAULT NULL,
  `Pw` varchar(45) NOT NULL DEFAULT '0',
  `Age` int(11) NOT NULL,
  `Department_ID` int(11) DEFAULT NULL,
  `Attendance` tinyint(4) NOT NULL DEFAULT '0',
  `Salary_Multiplier` float unsigned NOT NULL DEFAULT '1',
  `EmployeeType_ID` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Employee_ID`),
  KEY `EmployeeTypeID FK_idx` (`EmployeeType_ID`),
  KEY `Department ID FK_idx` (`Department_ID`),
  CONSTRAINT `Department ID FK` FOREIGN KEY (`Department_ID`) REFERENCES `department_table` (`Department_ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `EmployeeTypeID FK` FOREIGN KEY (`EmployeeType_ID`) REFERENCES `employee_type_table` (`EmployeeType_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_table`
--

LOCK TABLES `employee_table` WRITE;
/*!40000 ALTER TABLE `employee_table` DISABLE KEYS */;
INSERT INTO `employee_table` VALUES (1001,'Jon','Doe','jon',34,301,0,1.2,1),(1002,'Jim','Dim','jim',29,302,0,1.1,1),(1003,'Steven','Universe','ste',26,303,0,1.5,1),(1004,'Milo','Murphy','mil',40,304,0,1.1,1),(1005,'Phineas','Flynn','phi',20,305,0,1.8,1),(1006,'Ferb','Fletcher','fer',20,305,0,1.8,1),(1007,'Candace','Flynn','can',25,306,0,1.6,1),(1008,'Lawrence','Fletcher','law',45,301,0,1.5,1),(1009,'Lindana','Flynn','lin',40,302,0,1.5,1),(1010,'Dean','Winchester','dea',37,303,0,1.7,1),(1011,'Sam','Winchester','sam',34,303,0,1.9,1),(1012,'Hue','Nguyen','hue',39,301,0,1.4,1),(1013,'Thanh','Nguyen','tha',22,304,0,1.1,1),(1014,'Julius','Caesar','jul',40,306,0,1.3,99);
/*!40000 ALTER TABLE `employee_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_type_table`
--

DROP TABLE IF EXISTS `employee_type_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_type_table` (
  `EmployeeType_ID` int(11) NOT NULL,
  `EmployeeType_Name` varchar(45) NOT NULL,
  PRIMARY KEY (`EmployeeType_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_type_table`
--

LOCK TABLES `employee_type_table` WRITE;
/*!40000 ALTER TABLE `employee_type_table` DISABLE KEYS */;
INSERT INTO `employee_type_table` VALUES (1,'Employee'),(99,'Manager');
/*!40000 ALTER TABLE `employee_type_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_table`
--

DROP TABLE IF EXISTS `project_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_table` (
  `Project_ID` int(11) NOT NULL,
  `Project_Name` varchar(45) NOT NULL,
  `Project_BeginDate` date NOT NULL,
  `Project_EndDate` date DEFAULT NULL,
  `Project_Status` varchar(15) NOT NULL,
  PRIMARY KEY (`Project_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_table`
--

LOCK TABLES `project_table` WRITE;
/*!40000 ALTER TABLE `project_table` DISABLE KEYS */;
INSERT INTO `project_table` VALUES (401,'Project A','2017-08-15','2017-12-27','Finished'),(402,'Project L','2009-10-27','2019-10-27','Finished'),(403,'Project W','1999-11-19',NULL,'On_Going');
/*!40000 ALTER TABLE `project_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-26 18:01:09
