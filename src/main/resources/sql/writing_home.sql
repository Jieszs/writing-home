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

 Date: 08/07/2021 10:36:28
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
  `parentId` int(11) NOT NULL DEFAULT 0 COMMENT '父级素材Id（0-本素材不是仿写）',
  `state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态（1-正常 0-删除）',
  `isPublic` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否共享（1是0否）',
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
-- Table structure for material_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `material_operate_log`;
CREATE TABLE `material_operate_log`  (
  `logId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `materialId` int(11) NOT NULL COMMENT '素材id',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `operateType` int(11) NOT NULL COMMENT '操作类型（1-点赞 2-取消点赞 3-收藏 ）',
  `insertTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`logId`) USING BTREE,
  INDEX `idx_materialId`(`materialId`) USING BTREE

) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '素材操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for material_comment
-- ----------------------------
DROP TABLE IF EXISTS `material_comment`;
CREATE TABLE `material_comment` (
  `commentId` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `materialId` int(11) NOT NULL COMMENT '素材id',
  `comment` varchar(200)  DEFAULT NULL COMMENT '评论',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `receiver` int(11) NOT NULL COMMENT '接收用户id',
  `commentedId` int(11) NOT NULL COMMENT '被评论的id,（可以素材，也可以是评论）',
  `insertTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `type` int NOT NULL COMMENT '1-评论素材 2-回复评论',
  `state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态（1-正常 0-删除）',

  PRIMARY KEY (`commentId`),
  KEY `idx_materialId` (`materialId`),
  KEY `idx_commentedId` (`commentedId`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT ='评论';

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '昵称',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '邮箱',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态',
  `insertTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updateTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户信息' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
