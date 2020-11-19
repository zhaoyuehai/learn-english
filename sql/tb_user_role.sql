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

 Date: 26/10/2020 14:17:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_code` int(11) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`uid`, `user_id`) USING BTREE,
  INDEX `tb_user_role_tb_role`(`role_code`) USING BTREE,
  INDEX `tb_user_role_tb_user`(`user_id`) USING BTREE,
  CONSTRAINT `tb_user_role_tb_role` FOREIGN KEY (`role_code`) REFERENCES `tb_role` (`code`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `tb_user_role_tb_user` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`uid`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES (1, 1, 1, '2020-07-16 18:16:20', NULL);
INSERT INTO `tb_user_role` VALUES (5, 6, 0, '2020-08-07 14:43:30', NULL);
INSERT INTO `tb_user_role` VALUES (6, 7, 0, '2020-10-23 11:13:50', NULL);

SET FOREIGN_KEY_CHECKS = 1;
