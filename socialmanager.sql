/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : socialmanager

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 24/05/2021 15:32:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', '123456');

-- ----------------------------
-- Table structure for t_base
-- ----------------------------
DROP TABLE IF EXISTS `t_base`;
CREATE TABLE `t_base`  (
  `base_id` int(0) NOT NULL AUTO_INCREMENT,
  `block_id` int(0) NOT NULL COMMENT '所属区号',
  `base_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '基础设施名称',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`base_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_base
-- ----------------------------
INSERT INTO `t_base` VALUES (2, 2, '滑梯', '待维护');

-- ----------------------------
-- Table structure for t_block
-- ----------------------------
DROP TABLE IF EXISTS `t_block`;
CREATE TABLE `t_block`  (
  `block_id` int(0) NOT NULL AUTO_INCREMENT,
  `block_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区名，如（文明楼）',
  PRIMARY KEY (`block_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_block
-- ----------------------------
INSERT INTO `t_block` VALUES (1, '文明楼');
INSERT INTO `t_block` VALUES (2, '月光楼');
INSERT INTO `t_block` VALUES (4, '明理大楼');

-- ----------------------------
-- Table structure for t_guest
-- ----------------------------
DROP TABLE IF EXISTS `t_guest`;
CREATE TABLE `t_guest`  (
  `guest_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '访客编号',
  `host_id` int(0) NOT NULL COMMENT '登记户主',
  `guest_uid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `guest_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `guest_mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`guest_id`, `guest_uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_guest
-- ----------------------------
INSERT INTO `t_guest` VALUES (2, 3, '1621694401719X', '王三', '13811111111');
INSERT INTO `t_guest` VALUES (3, 1, '1621694516933X', '李四', '13800000001');
INSERT INTO `t_guest` VALUES (4, 3, '1621762748050X', '黄三', '13611111111');

-- ----------------------------
-- Table structure for t_host
-- ----------------------------
DROP TABLE IF EXISTS `t_host`;
CREATE TABLE `t_host`  (
  `host_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '户主编号',
  `uid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `block_id` int(0) NOT NULL COMMENT '区号',
  `political_id` int(0) NOT NULL COMMENT '政治编号',
  `rent_id` int(0) NULL DEFAULT NULL COMMENT '租赁编号（可为空）',
  `pet_id` int(0) NULL DEFAULT NULL COMMENT '宠物编号（可为空）',
  `park_id` int(0) NULL DEFAULT NULL COMMENT '停车场编号',
  `host_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `room_id` int(0) NOT NULL,
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`host_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_host
-- ----------------------------
INSERT INTO `t_host` VALUES (1, '12010455555X', 1, 1, 1, 1, 1, '王志', '654321', 830, '13111111111');
INSERT INTO `t_host` VALUES (2, '1201678744X', 1, 1, NULL, 4, NULL, '王莽', '123456', 630, '13000000000');
INSERT INTO `t_host` VALUES (3, '1621582121579X', 2, 2, NULL, NULL, NULL, '李明', '123456', 730, '13000000001');
INSERT INTO `t_host` VALUES (5, '1621840212901X', 4, 1, NULL, 6, 13, '俊杰', '123456', 601, '13821735637');

-- ----------------------------
-- Table structure for t_park
-- ----------------------------
DROP TABLE IF EXISTS `t_park`;
CREATE TABLE `t_park`  (
  `park_id` int(0) NOT NULL,
  `block_id` int(0) NOT NULL,
  PRIMARY KEY (`park_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_park
-- ----------------------------
INSERT INTO `t_park` VALUES (1, 1);
INSERT INTO `t_park` VALUES (4, 2);
INSERT INTO `t_park` VALUES (13, 4);

-- ----------------------------
-- Table structure for t_pet
-- ----------------------------
DROP TABLE IF EXISTS `t_pet`;
CREATE TABLE `t_pet`  (
  `pet_id` int(0) NOT NULL AUTO_INCREMENT,
  `host_id` int(0) NOT NULL COMMENT '主人ID',
  `pet_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '宠物名称',
  `pet_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '宠物类型',
  PRIMARY KEY (`pet_id`, `host_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_pet
-- ----------------------------
INSERT INTO `t_pet` VALUES (1, 1, '雨雨', '狗狗');
INSERT INTO `t_pet` VALUES (4, 2, '大黄', '猫');
INSERT INTO `t_pet` VALUES (6, 5, '小福狗', '狗');

-- ----------------------------
-- Table structure for t_political
-- ----------------------------
DROP TABLE IF EXISTS `t_political`;
CREATE TABLE `t_political`  (
  `political_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '政治编号',
  `political_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '政治类型',
  `charge` double NOT NULL COMMENT '费用',
  PRIMARY KEY (`political_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_political
-- ----------------------------
INSERT INTO `t_political` VALUES (1, '党员', 50);
INSERT INTO `t_political` VALUES (2, '团员', 20);
INSERT INTO `t_political` VALUES (3, '群众', 0);

-- ----------------------------
-- Table structure for t_property
-- ----------------------------
DROP TABLE IF EXISTS `t_property`;
CREATE TABLE `t_property`  (
  `property_id` int(0) NOT NULL AUTO_INCREMENT,
  `block_id` int(0) NOT NULL COMMENT '所属区号',
  `uid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '物业身份证号',
  `property_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '物业名称',
  PRIMARY KEY (`property_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_property
-- ----------------------------
INSERT INTO `t_property` VALUES (1, 2, '120107777X', '王松');
INSERT INTO `t_property` VALUES (2, 1, '1621773514315X', '王鑫');

-- ----------------------------
-- Table structure for t_rent
-- ----------------------------
DROP TABLE IF EXISTS `t_rent`;
CREATE TABLE `t_rent`  (
  `rent_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '租赁编号',
  `host_id` int(0) NOT NULL COMMENT '房主编号',
  `rent_uid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租客身份证号',
  `rent_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租客名称',
  PRIMARY KEY (`rent_id`, `host_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_rent
-- ----------------------------
INSERT INTO `t_rent` VALUES (1, 1, '12010777777X', '张一珊');

-- ----------------------------
-- Table structure for t_security
-- ----------------------------
DROP TABLE IF EXISTS `t_security`;
CREATE TABLE `t_security`  (
  `security_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '安保编号',
  `uid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '安保人员身份证号',
  `block_id` int(0) NULL DEFAULT NULL COMMENT '所属区号',
  `security_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`security_id`, `uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_security
-- ----------------------------
INSERT INTO `t_security` VALUES (1, '1201082222X', 1, '李溜');
INSERT INTO `t_security` VALUES (3, '1621762701434X', 2, '彰武');

-- ----------------------------
-- Table structure for t_store
-- ----------------------------
DROP TABLE IF EXISTS `t_store`;
CREATE TABLE `t_store`  (
  `store_id` int(0) NOT NULL AUTO_INCREMENT,
  `host_id` int(0) NOT NULL COMMENT '业主ID',
  `store_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '底商名称',
  `store_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '底商类型',
  PRIMARY KEY (`store_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_store
-- ----------------------------
INSERT INTO `t_store` VALUES (1, 1, '有家文具店', '文具销售');

SET FOREIGN_KEY_CHECKS = 1;
