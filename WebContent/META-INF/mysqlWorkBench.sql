CREATE DATABASE  IF NOT EXISTS `bsexam` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bsexam`;
-- MySQL dump 10.13  Distrib 5.6.19, for Win32 (x86)
--
-- Host: localhost    Database: bsexam
-- ------------------------------------------------------
-- Server version	5.6.20-enterprise-commercial-advanced-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `classmessage`
--

DROP TABLE IF EXISTS `classmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classmessage` (
  `cname` varchar(15) NOT NULL,
  `syear` varchar(4) NOT NULL,
  `clength` char(1) NOT NULL,
  `dno` char(2) NOT NULL,
  `cspecial` varchar(20) NOT NULL,
  `cdepat` int(3) NOT NULL,
  PRIMARY KEY (`cname`),
  KEY `FK_CLASS_DEGREE_idx` (`dno`),
  KEY `FK_CLASS_DEPARTMENT_idx` (`cdepat`),
  CONSTRAINT `fk_class_degree` FOREIGN KEY (`dno`) REFERENCES `degree` (`dno`) ON UPDATE CASCADE,
  CONSTRAINT `fk_class_department` FOREIGN KEY (`cdepat`) REFERENCES `department` (`cdepat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classmessage`
--

LOCK TABLES `classmessage` WRITE;
/*!40000 ALTER TABLE `classmessage` DISABLE KEYS */;
INSERT INTO `classmessage` VALUES ('计科本121','12','4','2','计算机科学与技术(信息技术)',8);
/*!40000 ALTER TABLE `classmessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `degree`
--

DROP TABLE IF EXISTS `degree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `degree` (
  `dno` char(2) NOT NULL DEFAULT '',
  `dname` varchar(4) NOT NULL,
  PRIMARY KEY (`dno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `degree`
--

LOCK TABLES `degree` WRITE;
/*!40000 ALTER TABLE `degree` DISABLE KEYS */;
INSERT INTO `degree` VALUES ('1','专科'),('2','本科'),('3','研究生');
/*!40000 ALTER TABLE `degree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `cdepat` int(3) NOT NULL AUTO_INCREMENT,
  `depatname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cdepat`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (8,'数学与计算机信息工程系');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examtype`
--

DROP TABLE IF EXISTS `examtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examtype` (
  `eno` char(1) NOT NULL DEFAULT '',
  `ename` varchar(14) DEFAULT NULL,
  `edate` datetime DEFAULT NULL,
  PRIMARY KEY (`eno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examtype`
--

LOCK TABLES `examtype` WRITE;
/*!40000 ALTER TABLE `examtype` DISABLE KEYS */;
INSERT INTO `examtype` VALUES ('4','全国英语四级考试','2014-12-20 08:30:00'),('6','全国英语六级考试','2014-12-20 15:30:00');
/*!40000 ALTER TABLE `examtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `excelfile`
--

DROP TABLE IF EXISTS `excelfile`;
/*!50001 DROP VIEW IF EXISTS `excelfile`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `excelfile` AS SELECT 
 1 AS `sname`,
 1 AS `ssex`,
 1 AS `sno`,
 1 AS `IDNO`,
 1 AS `sid`,
 1 AS `dno`,
 1 AS `clength`,
 1 AS `syear`,
 1 AS `nj`,
 1 AS `depatname`,
 1 AS `cspecial`,
 1 AS `cname`,
 1 AS `eno`,
 1 AS `edate`,
 1 AS `etime`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `excelfile_1`
--

DROP TABLE IF EXISTS `excelfile_1`;
/*!50001 DROP VIEW IF EXISTS `excelfile_1`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `excelfile_1` AS SELECT 
 1 AS `姓名`,
 1 AS `性别`,
 1 AS `学号`,
 1 AS `证件类型`,
 1 AS `证件号码`,
 1 AS `学历`,
 1 AS `学制`,
 1 AS `入学年份`,
 1 AS `年级`,
 1 AS `院系`,
 1 AS `专业`,
 1 AS `班级`,
 1 AS `考试类型`,
 1 AS `考试成绩`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `idcard`
--

DROP TABLE IF EXISTS `idcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `idcard` (
  `IDNO` char(2) NOT NULL DEFAULT '',
  `IDname` varchar(20) NOT NULL,
  PRIMARY KEY (`IDNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idcard`
--

LOCK TABLES `idcard` WRITE;
/*!40000 ALTER TABLE `idcard` DISABLE KEYS */;
INSERT INTO `idcard` VALUES ('1','居民身份证'),('2','军人及武警人员证件'),('3','护照'),('4','其它');
/*!40000 ALTER TABLE `idcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `se`
--

DROP TABLE IF EXISTS `se`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `se` (
  `sno` char(21) NOT NULL,
  `eno` char(1) NOT NULL,
  `edate` char(10) NOT NULL,
  `egrade` decimal(5,2) DEFAULT '0.00',
  `etime` datetime DEFAULT NULL,
  PRIMARY KEY (`sno`,`edate`),
  KEY `fk_se_examtype` (`eno`),
  CONSTRAINT `fk_se_examtype` FOREIGN KEY (`eno`) REFERENCES `examtype` (`eno`) ON UPDATE CASCADE,
  CONSTRAINT `fk_se_student` FOREIGN KEY (`sno`) REFERENCES `student` (`sno`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `se`
--

LOCK TABLES `se` WRITE;
/*!40000 ALTER TABLE `se` DISABLE KEYS */;
INSERT INTO `se` VALUES ('2012110928','4','2014-12-20',0.00,'2014-12-20 08:30:00');
/*!40000 ALTER TABLE `se` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `showstu`
--

DROP TABLE IF EXISTS `showstu`;
/*!50001 DROP VIEW IF EXISTS `showstu`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `showstu` AS SELECT 
 1 AS `sname`,
 1 AS `sno`,
 1 AS `cname`,
 1 AS `depatname`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `sno` char(21) NOT NULL DEFAULT '',
  `sname` varchar(16) DEFAULT NULL,
  `ssex` char(2) DEFAULT NULL,
  `sid` varchar(18) DEFAULT NULL,
  `IDNO` char(2) DEFAULT '1',
  `cname` varchar(15) DEFAULT NULL,
  `image_f` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`sno`),
  UNIQUE KEY `idCard` (`sid`),
  KEY `stuClass` (`cname`),
  KEY `FK_STUDENT_IDCard_idx` (`IDNO`),
  CONSTRAINT `FK_STUDENT_CLASS` FOREIGN KEY (`cname`) REFERENCES `classmessage` (`cname`) ON UPDATE CASCADE,
  CONSTRAINT `fk_student_idcard` FOREIGN KEY (`IDNO`) REFERENCES `idcard` (`IDNO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('2012110928','钟金军','男','450902199112106253','1','计科本121',1);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suser`
--

DROP TABLE IF EXISTS `suser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suser` (
  `SNO` char(21) NOT NULL,
  `password` varchar(16) NOT NULL,
  PRIMARY KEY (`SNO`),
  KEY `studentId` (`SNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suser`
--

LOCK TABLES `suser` WRITE;
/*!40000 ALTER TABLE `suser` DISABLE KEYS */;
INSERT INTO `suser` VALUES ('2012110927','123'),('2012110928','2012110928'),('2012110929','2012110929');
/*!40000 ALTER TABLE `suser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sysuser`
--

DROP TABLE IF EXISTS `sysuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysuser` (
  `suser` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`suser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sysuser`
--

LOCK TABLES `sysuser` WRITE;
/*!40000 ALTER TABLE `sysuser` DISABLE KEYS */;
INSERT INTO `sysuser` VALUES ('admin','admin');
/*!40000 ALTER TABLE `sysuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `excelfile`
--

/*!50001 DROP VIEW IF EXISTS `excelfile`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `excelfile` AS select `s`.`sname` AS `sname`,`s`.`ssex` AS `ssex`,`s`.`sno` AS `sno`,`s`.`IDNO` AS `IDNO`,`s`.`sid` AS `sid`,`d`.`dno` AS `dno`,`c`.`clength` AS `clength`,`c`.`syear` AS `syear`,`c`.`syear` AS `nj`,`de`.`depatname` AS `depatname`,`c`.`cspecial` AS `cspecial`,`s`.`cname` AS `cname`,`se`.`eno` AS `eno`,`se`.`edate` AS `edate`,`se`.`etime` AS `etime` from ((((`student` `s` left join `classmessage` `c` on((`s`.`cname` = `c`.`cname`))) left join `degree` `d` on((`c`.`dno` = `d`.`dno`))) left join `department` `de` on((`c`.`cdepat` = `de`.`cdepat`))) join `se` on((`s`.`sno` = `se`.`sno`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `excelfile_1`
--

/*!50001 DROP VIEW IF EXISTS `excelfile_1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `excelfile_1` AS select `s`.`sname` AS `姓名`,`s`.`ssex` AS `性别`,`s`.`sno` AS `学号`,`s`.`IDNO` AS `证件类型`,`s`.`sid` AS `证件号码`,`d`.`dno` AS `学历`,`c`.`clength` AS `学制`,`c`.`syear` AS `入学年份`,`c`.`syear` AS `年级`,`de`.`depatname` AS `院系`,`c`.`cspecial` AS `专业`,`s`.`cname` AS `班级`,`se`.`eno` AS `考试类型`,`se`.`edate` AS `考试成绩` from ((((`student` `s` left join `classmessage` `c` on((`s`.`cname` = `c`.`cname`))) left join `degree` `d` on((`c`.`dno` = `d`.`dno`))) left join `department` `de` on((`c`.`cdepat` = `de`.`cdepat`))) left join `se` on((`s`.`sno` = `se`.`sno`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `showstu`
--

/*!50001 DROP VIEW IF EXISTS `showstu`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `showstu` AS select `s`.`sname` AS `sname`,`s`.`sno` AS `sno`,`s`.`cname` AS `cname`,`de`.`depatname` AS `depatname` from ((`student` `s` left join `classmessage` `c` on((`s`.`cname` = `c`.`cname`))) left join `department` `de` on((`c`.`cdepat` = `de`.`cdepat`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-08  8:49:07
