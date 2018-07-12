/*
SQLyog Community v12.5.1 (64 bit)
MySQL - 10.0.34-MariaDB-0ubuntu0.16.04.1 : Database - app_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`app_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `app_db`;

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Role` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Role_Id` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `roles` */

insert  into `roles`(`Id`,`Role`) values 
(1,'ROLE_ADMIN'),
(2,'ROLE_PARTNER'),
(3,'ROLE_INSTRUCTOR'),
(4,'ROLE_STUDENT');

/*Table structure for table `user_profile` */

DROP TABLE IF EXISTS `user_profile`;

CREATE TABLE `user_profile` (
  `User_Email_Id` varchar(60) NOT NULL,
  `Name` varchar(60) NOT NULL,
  `DOB` date DEFAULT NULL,
  `DOJ` datetime NOT NULL,
  `MobileNo` varchar(10) DEFAULT NULL,
  `Skype` text,
  `LinkedIn` text,
  `Facebook` text,
  `Twitter` text,
  `GooglePlus` text,
  `Website` text,
  `Address` text,
  `ProfilePic` text,
  `ApprovedBy` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`User_Email_Id`),
  KEY `User_Email_Id` (`User_Email_Id`),
  KEY `ix_some_id` (`User_Email_Id`),
  KEY `ApprovedBy` (`ApprovedBy`),
  CONSTRAINT `user_profile_ibfk_1` FOREIGN KEY (`ApprovedBy`) REFERENCES `user_profile` (`User_Email_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_profile` */

insert  into `user_profile`(`User_Email_Id`,`Name`,`DOB`,`DOJ`,`MobileNo`,`Skype`,`LinkedIn`,`Facebook`,`Twitter`,`GooglePlus`,`Website`,`Address`,`ProfilePic`,`ApprovedBy`) values 
('surajmba.mba@gmail.com','Suraj Sinha',NULL,'2017-08-26 13:24:54','','surajmba.mba@gmail.com','https://www.linkedin.com/in/suraj-sinha-909750b7/','http://www.facebook.com/suraj.sinha.77736','https://twitter.com/SurajSi73451444','https://plus.google.com/102616516840863727271','www.cybotech.in','Gola Road, Near Imambara,  Nawada, Bihar - 805110','profilePic/surajmba.mba@gmail.com.jpg','surajmba.mba@gmail.com');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `User_Id` int(11) NOT NULL,
  `Role_Id` int(11) NOT NULL,
  KEY `FK143BF46A7214966` (`Role_Id`),
  KEY `FK143BF46AAC4C0D46` (`User_Id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`User_Id`) REFERENCES `users` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`Role_Id`) REFERENCES `roles` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_role` */

insert  into `user_role`(`User_Id`,`Role_Id`) values 
(2,2),
(2,3),
(2,1);

/*Table structure for table `user_token` */

DROP TABLE IF EXISTS `user_token`;

CREATE TABLE `user_token` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `secret_key` blob NOT NULL,
  `token` text NOT NULL,
  `lastUsed` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_toke_user_idx` (`user_id`),
  CONSTRAINT `FK_user_role_user_idx` FOREIGN KEY (`user_id`) REFERENCES `users` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_token` */

insert  into `user_token`(`id`,`user_id`,`last_used`,`secret_key`,`token`,`lastUsed`) values 
(1,2,'2018-06-05 07:46:38','§⁄óç7bûÊE£ã\neî‡Bw¿ØËÄó¶W¬Úú∆CZíÚÓ~9M[\ZµRPîÖVeS|—3÷¥9gªx¯`ﬂ∏Y','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiQ0xBSU1fVE9LRU5fVkVSU0lPTiI6MC43MTM2ODEyNTIzMDE2NTA5fQ.RzrbkVNdEMCjYBKTJ1R8ehzBcGSTMAZdEPSi4GX34Rzei3hn6q83frKHjJUynnAc-NNbVNDvs30x_VwBb274mw','2018-06-05 13:16:36');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_Email_Id` varchar(60) NOT NULL,
  `Password` varchar(60) NOT NULL,
  `Enable` tinyint(1) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `User_Id` (`Id`),
  UNIQUE KEY `Email_Id` (`User_Email_Id`),
  UNIQUE KEY `UK_gnbpe3y6ec8ghsjep8myvpn0u` (`User_Email_Id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`User_Email_Id`) REFERENCES `user_profile` (`User_Email_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=622 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`Id`,`User_Email_Id`,`Password`,`Enable`) values 
(2,'surajmba.mba@gmail.com','12345678',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
