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

 Date: 01/12/2020 20:42:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_word_learn_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_word_learn_record`;
CREATE TABLE `tb_word_learn_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tb_word_learn_time_tb_user`(`user_phone`) USING BTREE,
  CONSTRAINT `tb_word_learn_time_tb_user` FOREIGN KEY (`user_phone`) REFERENCES `tb_user` (`phone`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_word_learn_record
-- ----------------------------
INSERT INTO `tb_word_learn_record` VALUES (2, '2020-11-20 15:26:00', '18888888888', 5);
INSERT INTO `tb_word_learn_record` VALUES (3, '2020-11-23 11:27:15', '18511073583', 18);
INSERT INTO `tb_word_learn_record` VALUES (4, '2020-11-24 14:01:55', '18888888888', 2);
INSERT INTO `tb_word_learn_record` VALUES (5, '2020-11-24 14:34:40', '18511073583', 10);
INSERT INTO `tb_word_learn_record` VALUES (6, '2020-11-27 11:44:21', '18888888888', 3);

SET FOREIGN_KEY_CHECKS = 1;
