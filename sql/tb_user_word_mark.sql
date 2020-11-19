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

 Date: 26/10/2020 14:17:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user_word_mark
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_word_mark`;
CREATE TABLE `tb_user_word_mark`  (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `word_id` bigint(20) NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `mark_count` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `tb_user_word_mark_tb_user`(`user_phone`) USING BTREE,
  INDEX `tb_user_word_mark_tb_word`(`word_id`) USING BTREE,
  CONSTRAINT `tb_user_word_mark_tb_user` FOREIGN KEY (`user_phone`) REFERENCES `tb_user` (`phone`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_user_word_mark_tb_word` FOREIGN KEY (`word_id`) REFERENCES `tb_word` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_word_mark
-- ----------------------------
INSERT INTO `tb_user_word_mark` VALUES (1, '18888888888', 43, '2020-10-22 17:11:19', '2020-10-26 10:27:22', 0);
INSERT INTO `tb_user_word_mark` VALUES (2, '18511073583', 14, '2020-10-23 11:16:00', '2020-10-26 14:10:46', 4);

SET FOREIGN_KEY_CHECKS = 1;
