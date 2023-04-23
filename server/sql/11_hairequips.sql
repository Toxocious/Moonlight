/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : v210

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 22/09/2021 21:53:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hairequips
-- ----------------------------
DROP TABLE IF EXISTS `hairequips`;
CREATE TABLE `hairequips`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alid` int(11) NULL DEFAULT NULL,
  `bodypart` tinyint(2) NULL DEFAULT NULL,
  `equipid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `alid`(`alid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 38 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = FIXED;

SET FOREIGN_KEY_CHECKS = 1;
