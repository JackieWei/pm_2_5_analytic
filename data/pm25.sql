/*
Navicat MySQL Data Transfer

Source Server         : LOCALHOST
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : pm25

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-09-30 16:10:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hourly
-- ----------------------------
DROP TABLE IF EXISTS `hourly`;
CREATE TABLE `hourly` (
`entry`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'Auto increment main key for table' ,
`city`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`category`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`date`  datetime(6) NOT NULL ,
`value`  smallint(32) UNSIGNED NOT NULL ,
PRIMARY KEY (`entry`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=323861

;

-- ----------------------------
-- Records of hourly
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Auto increment value for hourly
-- ----------------------------
ALTER TABLE `hourly` AUTO_INCREMENT=323861;
