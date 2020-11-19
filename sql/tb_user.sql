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

 Date: 26/10/2020 14:17:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT 1 COMMENT '1:启用 2:禁用',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`uid`, `phone`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '18888888888', '$2a$10$2KZSxFeSsRoAXRMiSjmLjuCwarUGIpTK5jPFSsb9ZZMVtlds5kx.G', 'root', NULL, NULL, 1, '2020-07-16 18:02:22', '2020-07-20 10:47:13');
INSERT INTO `tb_user` VALUES (6, '15555555555', '$2a$10$RR2cJ1bEvyigBC/Vk4qnTu382o1v6ROuRnj6M4Fua9T2bWYZh3LXG', '', NULL, NULL, 1, '2020-08-07 14:43:29', '2020-10-23 11:15:47');
INSERT INTO `tb_user` VALUES (7, '18511073583', '$2a$10$MfUYt73.291VRVfPoglnMOWCQ4kbz1KhT457j3n4VCpNi3eYeBXti', '月海', NULL, NULL, 1, '2020-10-23 11:13:49', '2020-10-23 11:15:42');

SET FOREIGN_KEY_CHECKS = 1;
