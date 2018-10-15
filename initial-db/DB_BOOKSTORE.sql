/*
SQLyog Community v11.31 (64 bit)
MySQL - 5.5.36 : Database - book_store
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`book_store` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Table structure for table `log` */

DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
  `log_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `log_type` VARCHAR(255) DEFAULT NULL,
  `username` VARCHAR(255) NOT NULL,
  `user_id` BIGINT(20) NOT NULL,
  `order_mst_id` BIGINT(20) DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`log_id`),
  KEY `IDX1` (`log_type`,`user_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Table structure for table `order_dtl` */

DROP TABLE IF EXISTS `order_dtl`;

CREATE TABLE `order_dtl` (
  `order_dtl_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `order_mst_id` BIGINT(20) NOT NULL,
  `book_id` BIGINT(20) NOT NULL,
  `qty` BIGINT(20) DEFAULT NULL,
  `unit_price` DECIMAL(19,2) DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`order_dtl_id`),
  UNIQUE KEY `UNIQUE_IDX1` (`order_mst_id`,`book_id`),
  CONSTRAINT `FKhj0jhxx8fe4wblcn8fc36rihu` FOREIGN KEY (`order_mst_id`) REFERENCES `order_mst` (`order_mst_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Table structure for table `order_mst` */

DROP TABLE IF EXISTS `order_mst`;

CREATE TABLE `order_mst` (
  `order_mst_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL,
  `order_date` DATE DEFAULT NULL,
  `total_price` DECIMAL(19,2) DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`order_mst_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  `surname` VARCHAR(255) DEFAULT NULL,
  `date_of_birth` DATE DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UNIQUE_IDX1` (`username`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
