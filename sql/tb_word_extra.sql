/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : learn_english

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 26/10/2020 14:18:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_word_extra
-- ----------------------------
DROP TABLE IF EXISTS `tb_word_extra`;
CREATE TABLE `tb_word_extra`  (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `word_id` bigint(20) NOT NULL,
  `content_en` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content_cn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`uid`, `content_en`) USING BTREE,
  INDEX `tb_word_extra_tb_word`(`word_id`) USING BTREE,
  CONSTRAINT `tb_word_extra_tb_word` FOREIGN KEY (`word_id`) REFERENCES `tb_word` (`uid`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_word_extra
-- ----------------------------
INSERT INTO `tb_word_extra` VALUES (1, 1, 'starve', '饿死', '2020-10-16 18:27:53', NULL);
INSERT INTO `tb_word_extra` VALUES (2, 1689, 'clarify', '澄清', '2020-10-16 18:28:01', '2020-10-19 17:12:04');
INSERT INTO `tb_word_extra` VALUES (3, 1689, 'clarification', '[.n] 说明，净化，澄清', '2020-10-19 17:13:14', '2020-10-19 17:13:38');

SET FOREIGN_KEY_CHECKS = 1;
