/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : hotel

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 08/03/2022 23:00:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_dinner_table
-- ----------------------------
DROP TABLE IF EXISTS `tb_dinner_table`;
CREATE TABLE `tb_dinner_table`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `table_Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '餐桌名称',
  `table_status` int(11) NULL DEFAULT 0 COMMENT '0 未使用 1正在使用',
  `begin_use_date` datetime NULL DEFAULT NULL COMMENT '餐桌开始占用',
  `create_date` datetime NULL DEFAULT NULL COMMENT '餐桌的创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '餐桌信息的修改的时间',
  `disabled` int(11) NULL DEFAULT 0 COMMENT '1为删除',
  `use_user_id` int(11) NULL DEFAULT NULL COMMENT '餐桌当前使用者id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_dinner_table
-- ----------------------------
INSERT INTO `tb_dinner_table` VALUES (1, 'Lily(百合)', 1, NULL, '2022-03-06 00:00:00', '2022-03-06 15:07:03', 0, 2);
INSERT INTO `tb_dinner_table` VALUES (2, 'Peony(牡丹)', 0, NULL, '2022-03-06 00:00:00', '2022-03-06 16:31:32', 0, 6);
INSERT INTO `tb_dinner_table` VALUES (3, 'Rose(玫瑰)', 1, NULL, '2022-03-06 00:00:00', '2022-03-02 15:45:57', 0, 6);
INSERT INTO `tb_dinner_table` VALUES (4, 'Sakura(樱花)', 0, NULL, '2022-03-06 18:12:15', '2022-03-06 16:33:08', 0, NULL);
INSERT INTO `tb_dinner_table` VALUES (5, 'Lily(睡莲)', 1, NULL, '2022-03-06 00:00:00', '2022-03-06 22:05:25', 0, 6);
INSERT INTO `tb_dinner_table` VALUES (6, 'Lotus(荷花)', 0, NULL, '2022-03-06 16:32:37', '2022-03-06 16:32:40', 0, NULL);
INSERT INTO `tb_dinner_table` VALUES (7, 'Datura(曼陀罗)', 0, NULL, '2022-03-06 22:54:36', NULL, 0, NULL);
INSERT INTO `tb_dinner_table` VALUES (8, 'Wisteria(紫藤)', 0, NULL, '2022-03-06 22:54:41', NULL, 0, NULL);

-- ----------------------------
-- Table structure for tb_food
-- ----------------------------
DROP TABLE IF EXISTS `tb_food`;
CREATE TABLE `tb_food`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `food_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜名',
  `foodType_id` int(11) NULL DEFAULT NULL COMMENT '菜品类型id',
  `price` double NULL DEFAULT NULL COMMENT '价格',
  `discount` double NULL DEFAULT 1 COMMENT '折扣',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜的图片',
  `create_date` datetime NULL DEFAULT NULL COMMENT '菜品创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '菜品信息更新时间',
  `disabled` int(11) NULL DEFAULT 0 COMMENT '0 未删  1已删',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_food_foodType_id`(`foodType_id`) USING BTREE,
  CONSTRAINT `fk_food_foodType_id` FOREIGN KEY (`foodType_id`) REFERENCES `tb_food_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_food
-- ----------------------------
INSERT INTO `tb_food` VALUES (1, '花盖香辣蟹', 1, 99, 0.9, '', '1.jpg', '2020-03-29 13:15:33', '2020-03-29 14:00:15', 0);
INSERT INTO `tb_food` VALUES (2, '西红柿蛋汤', 5, 39, 0.9, '', '2.jpg', '2020-03-29 13:16:22', '2020-03-29 13:19:41', 0);
INSERT INTO `tb_food` VALUES (3, '香椿炒鸡翅', 1, 79, 0.8, '', '3.jpg', '2020-03-29 13:18:38', '2020-03-29 13:58:09', 0);
INSERT INTO `tb_food` VALUES (4, '清新柠檬水', 2, 29, 0.9, '', '4.jpg', '2020-03-29 13:19:17', '2022-03-02 15:47:48', 0);
INSERT INTO `tb_food` VALUES (5, '酸奶泡木瓜', 3, 39, 0.9, '', '5.jpg', '2020-03-29 13:20:15', '2022-03-02 15:47:44', 0);
INSERT INTO `tb_food` VALUES (6, '麻辣水煮鱼', 1, 99, 0.9, '', '6.jpg', '2020-03-29 14:40:36', '2022-03-02 15:47:43', 0);
INSERT INTO `tb_food` VALUES (7, '金陵烤鸭', 7, 39, 0.9, NULL, '7.jpg', '2022-03-02 15:30:32', '2022-03-02 15:30:23', 0);
INSERT INTO `tb_food` VALUES (8, '王府泡椒鸡', 7, 32, 0.8, NULL, '8.jpg', '2022-03-02 15:33:48', '2022-03-02 15:33:59', 0);
INSERT INTO `tb_food` VALUES (9, '椒盐局麻虾', 7, 45, 0.7, NULL, '9.jpg', '2022-03-02 15:35:37', '2022-03-02 15:47:34', 0);
INSERT INTO `tb_food` VALUES (10, '鸡汁豆腐', 7, 28, 0.9, NULL, '10.jpg', '2022-03-02 15:36:58', '2022-03-02 15:47:37', 0);
INSERT INTO `tb_food` VALUES (11, '鸡鸣汤包', 7, 20, 0.99, NULL, '11.jpg', '2022-03-02 15:37:42', '2022-03-02 15:47:41', 0);
INSERT INTO `tb_food` VALUES (12, '黄金肚肺丝', 7, 45, 0.8, NULL, '12.jpg', '2022-03-02 15:39:22', '2022-03-02 15:47:40', 0);
INSERT INTO `tb_food` VALUES (13, '赤豆元宵', 7, 10, 0.5, NULL, '13.jpg', '2022-03-02 15:51:54', '2022-03-02 15:51:57', 0);
INSERT INTO `tb_food` VALUES (14, '大麦拉格啤酒', 2, 18, 1, NULL, '14.jpg', '2022-03-02 16:01:27', '2022-03-02 16:01:30', 0);
INSERT INTO `tb_food` VALUES (15, '德式小麦啤酒', 2, 10, 1, NULL, '15.jpg', '2022-03-02 16:02:16', '2022-03-02 16:02:19', 0);
INSERT INTO `tb_food` VALUES (16, '淡爽拉格拉格啤酒', 2, 14, 1, NULL, '16.jpg', '2022-03-02 16:03:02', '2022-03-02 16:03:05', 0);
INSERT INTO `tb_food` VALUES (17, '淡爽拉格拉格啤酒', 2, 14, 1, NULL, '17.jpg', '2022-03-02 16:03:02', '2022-03-02 16:03:05', 0);
INSERT INTO `tb_food` VALUES (18, '现炸酥肉', 8, 15, 0.8, '', '18.jpg', '2022-03-02 16:05:31', '2022-03-02 16:05:34', 0);
INSERT INTO `tb_food` VALUES (19, '葱味飞饼', 8, 22, 0.9, NULL, '19.jpg', '2022-03-02 16:06:33', '2022-03-02 16:06:35', 0);
INSERT INTO `tb_food` VALUES (20, '金银百合樱花鸭', 1, 48, 1, NULL, '20.jpg', '2022-03-05 15:58:20', '2022-03-05 15:58:22', 0);
INSERT INTO `tb_food` VALUES (21, '香蒜雪花牛肉粒', 1, 168, 1, NULL, '21.jpg', '2022-03-05 15:59:06', '2022-03-05 15:59:09', 0);
INSERT INTO `tb_food` VALUES (22, '海参有机鱼头煲', 1, 368, 0.9, NULL, '22.jpg', '2022-03-05 15:59:57', '2022-03-05 15:59:59', 0);
INSERT INTO `tb_food` VALUES (23, '青团', 3, 18, 0.8, NULL, '23.jpg', '2022-03-05 16:01:04', '2022-03-05 16:01:06', 0);
INSERT INTO `tb_food` VALUES (24, '美玲粥', 3, 12, 0.9, NULL, '24.jpg', '2022-03-05 16:01:40', '2022-03-05 16:01:42', 0);
INSERT INTO `tb_food` VALUES (25, '葱油鲈鱼', 7, 416, 0.95, NULL, '25.jpg', '2022-03-05 16:03:07', '2022-03-05 16:03:09', 0);
INSERT INTO `tb_food` VALUES (26, '鲍鱼参花胶滋补羹', 7, 416, 0.95, NULL, '26.jpg', '2022-03-05 16:03:07', '2022-03-05 16:03:09', 0);
INSERT INTO `tb_food` VALUES (27, '佛跳墙', 5, 888, 1, NULL, '27.jpg', '2022-03-05 16:05:45', '2022-03-05 16:05:49', 0);
INSERT INTO `tb_food` VALUES (28, '淮扬烫干丝', 1, 78, 1, NULL, '28.jpg', '2022-03-05 16:08:20', '2022-03-05 16:08:22', 0);
INSERT INTO `tb_food` VALUES (29, '夫妻肺片', 4, 45, 0.8, '', '29.jpg', '2022-03-05 16:42:42', '2022-03-05 16:42:44', 0);
INSERT INTO `tb_food` VALUES (30, '蔬菜拼盘', 6, 20, 0.8, NULL, '34.jpg', '2022-03-05 16:52:09', '2022-03-05 16:52:13', 0);
INSERT INTO `tb_food` VALUES (31, '干炒牛肚丝', 1, 55, 0.8, NULL, '30.jpg', '2022-03-05 16:52:15', '2022-03-05 16:52:17', 0);
INSERT INTO `tb_food` VALUES (32, '鲍鱼和牛汤', 5, 188, 0.8, NULL, '31.jpg', '2022-03-05 16:52:20', '2022-03-05 16:52:22', 0);
INSERT INTO `tb_food` VALUES (33, '大版肥牛', 6, 99, 0.95, NULL, '32.jpg', '2022-03-05 16:52:24', '2022-03-05 16:52:26', 0);
INSERT INTO `tb_food` VALUES (34, '信鲍鸡汁豆腐羹', 5, 33, 0.95, NULL, '33.jpg', '2022-03-05 16:52:28', '2022-03-05 16:52:30', 0);
INSERT INTO `tb_food` VALUES (35, '鹌鹑蛋', 6, 24, 0.95, NULL, '35.jpg', '2022-03-05 16:55:24', '2022-03-05 16:55:26', 0);
INSERT INTO `tb_food` VALUES (36, '腐竹片', 6, 14, 0.95, NULL, '36.jpg', '2022-03-05 16:55:28', '2022-03-05 16:55:30', 0);
INSERT INTO `tb_food` VALUES (37, '老北京毛肚', 6, 88, 0.95, NULL, '37.jpg', '2022-03-05 16:55:37', '2022-03-05 16:55:35', 0);
INSERT INTO `tb_food` VALUES (38, '清丝藕片', 6, 14, 0.95, NULL, '38.jpg', '2022-03-05 16:55:38', '2022-03-05 16:55:43', 0);
INSERT INTO `tb_food` VALUES (39, '滋养鸽子汤', 5, 99, 0.95, NULL, '39.jpg', '2022-03-05 16:57:57', '2022-03-05 16:58:02', 0);
INSERT INTO `tb_food` VALUES (40, '减脂汤', 5, 108, 0.95, NULL, '40.jpg', '2022-03-05 16:57:59', '2022-03-05 16:58:04', 0);
INSERT INTO `tb_food` VALUES (41, '羊肉丸子冬瓜汤', 5, 199, 0.95, NULL, '41.jpg', '2022-03-05 16:58:46', '2022-03-05 16:58:48', 0);
INSERT INTO `tb_food` VALUES (42, '凉拌削肉', 4, 18, 1, NULL, '42.jpg', '2022-03-05 17:05:17', '2022-03-05 17:05:27', 0);
INSERT INTO `tb_food` VALUES (43, '凉拌黄瓜', 4, 12, 1, NULL, '43.jpg', '2022-03-05 17:05:20', '2022-03-05 17:05:30', 0);
INSERT INTO `tb_food` VALUES (44, '凉拌海蜇', 4, 18, 1, NULL, '44.jpg', '2022-03-05 17:05:23', '2022-03-05 17:05:32', 0);
INSERT INTO `tb_food` VALUES (45, '皮蛋豆腐', 4, 12, 1, NULL, '45.jpg', '2022-03-05 17:05:25', '2022-03-05 17:05:34', 0);
INSERT INTO `tb_food` VALUES (46, '一鸣酸奶', 2, 6, 1, NULL, '46.jpg', '2022-03-05 22:22:59', '2022-03-05 22:23:02', 0);

-- ----------------------------
-- Table structure for tb_food_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_food_type`;
CREATE TABLE `tb_food_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品类型名称',
  `create_date` datetime NULL DEFAULT NULL COMMENT '菜品类型创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '菜品类型更新时间',
  `disabled` int(11) NULL DEFAULT 0 COMMENT '0未删  1已删',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_food_type
-- ----------------------------
INSERT INTO `tb_food_type` VALUES (1, '推荐', '2022-03-02 13:14:22', '2022-03-02 13:15:06', 0);
INSERT INTO `tb_food_type` VALUES (2, '酒水饮料', '2022-03-02 13:14:28', '2022-03-02 16:04:22', 0);
INSERT INTO `tb_food_type` VALUES (3, '甜点饮品', '2022-03-02 13:14:31', '2022-03-02 16:04:25', 0);
INSERT INTO `tb_food_type` VALUES (4, '开胃凉菜', '2022-03-02 13:14:36', '2022-03-02 16:04:28', 0);
INSERT INTO `tb_food_type` VALUES (5, '慢火熬煮', '2022-03-02 13:19:34', '2022-03-02 16:04:32', 0);
INSERT INTO `tb_food_type` VALUES (6, '特色火锅', '2022-03-02 18:12:35', '2022-03-02 16:04:35', 0);
INSERT INTO `tb_food_type` VALUES (7, '商家招牌', '2022-03-02 18:12:42', '2022-03-02 16:04:38', 0);
INSERT INTO `tb_food_type` VALUES (8, '小吃', '2022-03-02 18:13:33', '2021-04-25 18:13:57', 0);

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编码',
  `table_id` int(11) NULL DEFAULT NULL COMMENT '订单所属餐桌',
  `total_Price` double NULL DEFAULT NULL COMMENT '总价',
  `order_Status` int(11) NULL DEFAULT 0 COMMENT '0已下单未付款  1已付款',
  `order_Date` datetime NULL DEFAULT NULL COMMENT '下单时间',
  `pay_date` datetime NULL DEFAULT NULL COMMENT '付款时间',
  `disabled` int(11) NULL DEFAULT 0 COMMENT '0未删  1已删',
  `update_date` datetime NULL DEFAULT NULL,
  `create_userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_table_id`(`table_id`) USING BTREE,
  CONSTRAINT `order_table_id` FOREIGN KEY (`table_id`) REFERENCES `tb_dinner_table` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES (13, 'OP-20210505150747620dc8f7-b460-494b-a38a-f101c17f4ec9', 2, 89.10000000000001, 1, '2021-05-05 15:07:47', '2021-05-05 15:10:45', 0, '2021-05-05 15:10:45', '2');
INSERT INTO `tb_order` VALUES (14, 'OP-2022030121193947afbd44-df3b-4b38-b6e7-a8b1ff4539a6', 5, 63.2, 1, '2022-03-01 21:19:39', '2022-03-01 21:19:45', 0, '2022-03-01 21:19:45', '2');
INSERT INTO `tb_order` VALUES (15, 'OP-202203012204158eb7a605-8a67-4725-a206-e12ca33a7254', 5, 89.10000000000001, 1, '2022-03-01 22:04:15', '2022-03-01 22:04:22', 0, '2022-03-01 22:04:22', '6');

-- ----------------------------
-- Table structure for tb_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_detail`;
CREATE TABLE `tb_order_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) NULL DEFAULT NULL COMMENT '所属订单id',
  `food_id` int(11) NULL DEFAULT NULL COMMENT '菜品id',
  `buyNum` int(11) NULL DEFAULT NULL COMMENT '够买数量',
  `disabled` int(11) NULL DEFAULT 0 COMMENT '0 表示正常 1表示已删除',
  `discount` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `orderDetail_order_id`(`orderId`) USING BTREE,
  INDEX `orderDetail_food_id`(`food_id`) USING BTREE,
  CONSTRAINT `orderDetail_food_id` FOREIGN KEY (`food_id`) REFERENCES `tb_food` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orderDetail_order_id` FOREIGN KEY (`orderId`) REFERENCES `tb_order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_order_detail
-- ----------------------------
INSERT INTO `tb_order_detail` VALUES (1, 13, 1, 1, 0, 0.9);
INSERT INTO `tb_order_detail` VALUES (2, 14, 3, 1, 0, 0.8);
INSERT INTO `tb_order_detail` VALUES (3, 15, 1, 1, 0, 0.9);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `PASSWORD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '用户创建时间',
  `DISABLED` tinyint(1) NULL DEFAULT 0 COMMENT '0 表示正常 1表示已删除',
  `create_userId` int(11) NULL DEFAULT NULL COMMENT '该用户的创建人id',
  `ROLE_ID` int(11) NULL DEFAULT 1 COMMENT '0表示管理员  1表示普通用户',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `LOGIN_NAME`(`LOGIN_NAME`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', '111', 'test1@qq.com', '13539909241', '2020-03-19 12:55:44', 0, NULL, 0);
INSERT INTO `tb_user` VALUES (2, 'ces', '111111', '1022829@qq.com', '13539902243', '2021-04-11 21:34:12', 0, 0, 0);
INSERT INTO `tb_user` VALUES (3, 'zhangs', '111111', '1022829@qq.com', '13539902243', '2021-04-11 21:34:56', 0, 1, 1);
INSERT INTO `tb_user` VALUES (4, 'asd', '111111', '1022829@qq.com', '13539909243', '2021-04-25 21:41:05', 0, 0, 1);
INSERT INTO `tb_user` VALUES (5, '21阿斯达', '111111', '1022829@qq.com', '13539902243', '2021-04-30 11:09:14', 0, 0, 1);
INSERT INTO `tb_user` VALUES (6, 'zhangsan', '111111', '3157676390@qq.com', '18052070763', '2022-03-01 21:21:18', 0, NULL, 1);
INSERT INTO `tb_user` VALUES (7, 'lisi', '123456', '123321@163.com', '18052070761', '2022-03-05 15:52:55', 0, NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
