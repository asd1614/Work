-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.20-enterprise-commercial-advanced-log - MySQL Enterprise Server - Advanced Edition (Commercial)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 bsexam 的数据库结构
CREATE DATABASE IF NOT EXISTS `bsexam` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bsexam`;


-- 导出  表 bsexam.classmessage 结构
CREATE TABLE IF NOT EXISTS `classmessage` (
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

-- 正在导出表  bsexam.classmessage 的数据：~0 rows (大约)
DELETE FROM `classmessage`;
/*!40000 ALTER TABLE `classmessage` DISABLE KEYS */;
INSERT INTO `classmessage` (`cname`, `syear`, `clength`, `dno`, `cspecial`, `cdepat`) VALUES
	('计科本121', '12', '4', '2', '计算机科学与技术(信息技术)', 8);
/*!40000 ALTER TABLE `classmessage` ENABLE KEYS */;


-- 导出  表 bsexam.degree 结构
CREATE TABLE IF NOT EXISTS `degree` (
  `dno` char(2) NOT NULL DEFAULT '',
  `dname` varchar(4) NOT NULL,
  PRIMARY KEY (`dno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  bsexam.degree 的数据：~3 rows (大约)
DELETE FROM `degree`;
/*!40000 ALTER TABLE `degree` DISABLE KEYS */;
INSERT INTO `degree` (`dno`, `dname`) VALUES
	('1', '专科'),
	('2', '本科'),
	('3', '研究生');
/*!40000 ALTER TABLE `degree` ENABLE KEYS */;


-- 导出  表 bsexam.department 结构
CREATE TABLE IF NOT EXISTS `department` (
  `cdepat` int(3) NOT NULL AUTO_INCREMENT,
  `depatname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cdepat`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- 正在导出表  bsexam.department 的数据：~0 rows (大约)
DELETE FROM `department`;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`cdepat`, `depatname`) VALUES
	(8, '数学与计算机信息工程系');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;


-- 导出  表 bsexam.examtype 结构
CREATE TABLE IF NOT EXISTS `examtype` (
  `eno` char(1) NOT NULL DEFAULT '',
  `ename` varchar(14) DEFAULT NULL,
  `edate` datetime DEFAULT NULL,
  PRIMARY KEY (`eno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  bsexam.examtype 的数据：~2 rows (大约)
DELETE FROM `examtype`;
/*!40000 ALTER TABLE `examtype` DISABLE KEYS */;
INSERT INTO `examtype` (`eno`, `ename`, `edate`) VALUES
	('4', '全国英语四级考试', '2014-12-20 08:30:00'),
	('6', '全国英语六级考试', '2014-12-20 15:30:00');
/*!40000 ALTER TABLE `examtype` ENABLE KEYS */;


-- 导出  视图 bsexam.excelfile 结构
-- 创建临时表以解决视图依赖性错误
CREATE TABLE `excelfile` (
	`sname` VARCHAR(16) NULL COLLATE 'utf8_general_ci',
	`ssex` CHAR(2) NULL COLLATE 'utf8_general_ci',
	`sno` CHAR(21) NOT NULL COLLATE 'utf8_general_ci',
	`IDNO` CHAR(2) NULL COLLATE 'utf8_general_ci',
	`sid` VARCHAR(18) NULL COLLATE 'utf8_general_ci',
	`dno` CHAR(2) NULL COLLATE 'utf8_general_ci',
	`clength` CHAR(1) NULL COLLATE 'utf8_general_ci',
	`syear` VARCHAR(4) NULL COLLATE 'utf8_general_ci',
	`nj` VARCHAR(4) NULL COLLATE 'utf8_general_ci',
	`depatname` VARCHAR(20) NULL COLLATE 'utf8_general_ci',
	`cspecial` VARCHAR(20) NULL COLLATE 'utf8_general_ci',
	`cname` VARCHAR(15) NULL COLLATE 'utf8_general_ci',
	`eno` CHAR(1) NOT NULL COLLATE 'utf8_general_ci',
	`edate` CHAR(10) NOT NULL COLLATE 'utf8_general_ci',
	`etime` DATETIME NULL
) ENGINE=MyISAM;


-- 导出  视图 bsexam.excelfile_1 结构
-- 创建临时表以解决视图依赖性错误
CREATE TABLE `excelfile_1` (
	`姓名` VARCHAR(16) NULL COLLATE 'utf8_general_ci',
	`性别` CHAR(2) NULL COLLATE 'utf8_general_ci',
	`学号` CHAR(21) NOT NULL COLLATE 'utf8_general_ci',
	`证件类型` CHAR(2) NULL COLLATE 'utf8_general_ci',
	`证件号码` VARCHAR(18) NULL COLLATE 'utf8_general_ci',
	`学历` CHAR(2) NULL COLLATE 'utf8_general_ci',
	`学制` CHAR(1) NULL COLLATE 'utf8_general_ci',
	`入学年份` VARCHAR(4) NULL COLLATE 'utf8_general_ci',
	`年级` VARCHAR(4) NULL COLLATE 'utf8_general_ci',
	`院系` VARCHAR(20) NULL COLLATE 'utf8_general_ci',
	`专业` VARCHAR(20) NULL COLLATE 'utf8_general_ci',
	`班级` VARCHAR(15) NULL COLLATE 'utf8_general_ci',
	`考试类型` CHAR(1) NULL COLLATE 'utf8_general_ci',
	`考试成绩` CHAR(10) NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;


-- 导出  表 bsexam.idcard 结构
CREATE TABLE IF NOT EXISTS `idcard` (
  `IDNO` char(2) NOT NULL DEFAULT '',
  `IDname` varchar(20) NOT NULL,
  PRIMARY KEY (`IDNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  bsexam.idcard 的数据：~4 rows (大约)
DELETE FROM `idcard`;
/*!40000 ALTER TABLE `idcard` DISABLE KEYS */;
INSERT INTO `idcard` (`IDNO`, `IDname`) VALUES
	('1', '居民身份证'),
	('2', '军人及武警人员证件'),
	('3', '护照'),
	('4', '其它');
/*!40000 ALTER TABLE `idcard` ENABLE KEYS */;


-- 导出  表 bsexam.se 结构
CREATE TABLE IF NOT EXISTS `se` (
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

-- 正在导出表  bsexam.se 的数据：~0 rows (大约)
DELETE FROM `se`;
/*!40000 ALTER TABLE `se` DISABLE KEYS */;
INSERT INTO `se` (`sno`, `eno`, `edate`, `egrade`, `etime`) VALUES
	('2012110928', '4', '2014-12-20', 0.00, '2014-12-20 08:30:00');
/*!40000 ALTER TABLE `se` ENABLE KEYS */;


-- 导出  视图 bsexam.showstu 结构
-- 创建临时表以解决视图依赖性错误
CREATE TABLE `showstu` (
	`sname` VARCHAR(16) NULL COLLATE 'utf8_general_ci',
	`sno` CHAR(21) NOT NULL COLLATE 'utf8_general_ci',
	`cname` VARCHAR(15) NULL COLLATE 'utf8_general_ci',
	`depatname` VARCHAR(20) NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;


-- 导出  表 bsexam.student 结构
CREATE TABLE IF NOT EXISTS `student` (
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

-- 正在导出表  bsexam.student 的数据：~0 rows (大约)
DELETE FROM `student`;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`sno`, `sname`, `ssex`, `sid`, `IDNO`, `cname`, `image_f`) VALUES
	('2012110928', '钟金军', '男', '450902199112106253', '1', '计科本121', 1);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;


-- 导出  表 bsexam.suser 结构
CREATE TABLE IF NOT EXISTS `suser` (
  `SNO` char(21) NOT NULL,
  `password` varchar(16) NOT NULL,
  PRIMARY KEY (`SNO`),
  KEY `studentId` (`SNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  bsexam.suser 的数据：~2 rows (大约)
DELETE FROM `suser`;
/*!40000 ALTER TABLE `suser` DISABLE KEYS */;
INSERT INTO `suser` (`SNO`, `password`) VALUES
	('2012110927', '123'),
	('2012110928', '2012110928'),
	('2012110929', '2012110929');
/*!40000 ALTER TABLE `suser` ENABLE KEYS */;


-- 导出  表 bsexam.sysuser 结构
CREATE TABLE IF NOT EXISTS `sysuser` (
  `suser` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`suser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  bsexam.sysuser 的数据：~0 rows (大约)
DELETE FROM `sysuser`;
/*!40000 ALTER TABLE `sysuser` DISABLE KEYS */;
INSERT INTO `sysuser` (`suser`, `password`) VALUES
	('admin', 'admin');
/*!40000 ALTER TABLE `sysuser` ENABLE KEYS */;


-- 导出  视图 bsexam.excelfile 结构
-- 移除临时表并创建最终视图结构
DROP TABLE IF EXISTS `excelfile`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` VIEW `excelfile` AS SELECT     s.sname, s.ssex, s.sno, s.IDNO, s.sid, d.dno, c.clength, c.syear, c.syear AS nj, de.depatname, c.cspecial, s.cname, se.eno, se.edate,se.etime
FROM         student AS s LEFT OUTER JOIN
                      classmessage AS c ON s.cname = c.cname LEFT OUTER JOIN
                      degree AS d ON c.dno = d.dno LEFT OUTER JOIN
                      department AS de ON c.cdepat = de.cdepat INNER JOIN
                      se ON s.sno = se.sno ;


-- 导出  视图 bsexam.excelfile_1 结构
-- 移除临时表并创建最终视图结构
DROP TABLE IF EXISTS `excelfile_1`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` VIEW `excelfile_1` AS select  s.sname AS '姓名',
        s.ssex AS '性别',
        s.sno AS '学号',
        s.IDNO AS '证件类型',
        s.sid AS '证件号码',
        d.dno AS '学历',
        c.clength AS '学制',
        c.syear AS '入学年份',
        c.syear AS '年级',
        de.depatname AS '院系',
        c.cspecial AS '专业',
        s.cname AS '班级',
        se.eno AS '考试类型',
        se.edate AS '考试成绩'
from student s left outer join classmessage c on s.cname = c.cname
	left outer join degree d on c.dno = d.dno
	left outer join department de on c.cdepat = de.cdepat
	left outer join se on s.sno = se.sno ;


-- 导出  视图 bsexam.showstu 结构
-- 移除临时表并创建最终视图结构
DROP TABLE IF EXISTS `showstu`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` VIEW `showstu` AS SELECT     s.sname , s.sno , s.cname , de.depatname 
FROM        student AS s LEFT OUTER JOIN
			classmessage AS c ON s.cname = c.cname LEFT OUTER JOIN
			department AS de ON c.cdepat = de.cdepat ;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
