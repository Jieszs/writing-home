/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : writing_home

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 02/07/2021 17:31:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material`  (
  `materialId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '素材内容',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态（1-正常 0-删除）',
  `source` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '来源',
  `insertTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '插入时间',
  `updateTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`materialId`) USING BTREE,
  INDEX `idx_userId`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '素材' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for material_type
-- ----------------------------
DROP TABLE IF EXISTS `material_type`;
CREATE TABLE `material_type`  (
  `typeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `typeName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `parentId` int(11) NOT NULL DEFAULT 0 COMMENT '父亲id',
  `orderId` int(11) NOT NULL DEFAULT 0 COMMENT '排序值，小的在前',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `insertTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '插入时间',
  `updateTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`typeId`) USING BTREE,
  INDEX `idx_userId`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '素材分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for material_type_rela
-- ----------------------------
DROP TABLE IF EXISTS `material_type_rela`;
CREATE TABLE `material_type_rela`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `materialId` int(11) NOT NULL COMMENT '素材id',
  `typeId` int(11) NOT NULL COMMENT '类型id',
  `insertTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '素材分类关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for parody
-- ----------------------------
DROP TABLE IF EXISTS `parody`;
CREATE TABLE `parody`  (
  `parodyId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `materialId` int(11) NOT NULL COMMENT '素材id',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态（1-正常 0-删除）',
  `insertTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '插入时间',
  `updateTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`parodyId`) USING BTREE,
  INDEX `idx_userId`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仿写' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
