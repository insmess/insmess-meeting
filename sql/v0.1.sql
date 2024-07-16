/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727 (5.7.27)
 Source Host           : localhost:3306
 Source Schema         : insmess_meeting

 Target Server Type    : MySQL
 Target Server Version : 50727 (5.7.27)
 File Encoding         : 65001

 Date: 15/05/2024 19:10:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会议名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '会议描述',
  `host_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主持人id',
  `host_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主持人名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `duration` int(20) NULL DEFAULT NULL COMMENT '时长（单位分钟）',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人id',
  `create_user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `room_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '房间密码',
  `room_type` int(11) NULL DEFAULT NULL COMMENT '0公开会议 1私密会议',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会议室' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('004fd73dfcd35f5ae6872a3b83747595', '微积分教学分享会', '水电费', '6acb128cd24161e4a75a2b171bc1c9e0', '管理员', NULL, '2024-04-29 00:08:49', 11, NULL, NULL, NULL, 0);
INSERT INTO `room` VALUES ('sdfsf', '测试会议', NULL, NULL, '张三', '2024-04-28 11:11:11', '2024-04-28 11:11:11', 15, NULL, '张三', NULL, 0);

-- ----------------------------
-- Table structure for room_file
-- ----------------------------
DROP TABLE IF EXISTS `room_file`;
CREATE TABLE `room_file`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `file_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型(扩展名)',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件url',
  `room_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会议id',
  `parent_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '上级目录id，如无上级，则为NULL',
  `entity_type` int(11) NULL DEFAULT NULL COMMENT '实体类型 0为目录 1为文件',
  `file_size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小，单位为字节',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会议资料' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_file
-- ----------------------------
INSERT INTO `room_file` VALUES ('06261184fd1e67b5abf5cb67c15763b3', '文档', '2024-05-12 21:06:25', NULL, NULL, NULL, '004fd73dfcd35f5ae6872a3b83747595', '0', 0, NULL);
INSERT INTO `room_file` VALUES ('3e84427c36620e962be9a8a5f477fce7', '视频', '2024-05-12 21:06:12', NULL, NULL, NULL, '004fd73dfcd35f5ae6872a3b83747595', '0', 0, NULL);
INSERT INTO `room_file` VALUES ('6eb5d03191094a23deb16022cab18845', '图片', '2024-05-12 21:07:46', NULL, NULL, NULL, '004fd73dfcd35f5ae6872a3b83747595', '0', 0, NULL);
INSERT INTO `room_file` VALUES ('c4fc606a560efd292e7009a7c8d3ffe8', '绿色安装Mysql.txt', '2024-05-15 13:43:57', NULL, 'txt', 'http://localhost:8380/storage/fetch/2fc500861bd9414bb228535598a04247.txt', '004fd73dfcd35f5ae6872a3b83747595', '06261184fd1e67b5abf5cb67c15763b3', 1, 558);

-- ----------------------------
-- Table structure for room_member_relation
-- ----------------------------
DROP TABLE IF EXISTS `room_member_relation`;
CREATE TABLE `room_member_relation`  (
  `id` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `room_id` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会议室id',
  `user_id` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会议室成员关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_member_relation
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `realname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6acb128cd24161e4a75a2b171bc1c9e0', 'admin', '管理员', '$2a$10$HteLNkhVPymq13/XUMlexuqaYlRQtDMyR219Yf8ISqxe5YNJyM0z.');

SET FOREIGN_KEY_CHECKS = 1;
