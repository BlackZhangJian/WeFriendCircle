/*
Navicat MySQL Data Transfer

Source Server         : allen
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : wefriendcircle

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2018-12-16 12:58:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `publishtime` datetime NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `imgurl` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`publishtime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2018-12-01 01:10:12', 'zhangsan', '1234', '我要飞的更高', 'images/publish/7eac23f2c4cb281a3e425bded60c00e6.jpg');
INSERT INTO `user` VALUES ('2018-12-02 02:30:01', '李清照', '123456', '生当作人杰，死亦为鬼雄。\r\n至今思项羽，不肯过江东。', 'images/publish/39200a97c165862f67a1e12666d98e0f.jpg');
INSERT INTO `user` VALUES ('2018-12-03 03:10:03', '杜甫', '123456', '八月秋高风怒号，卷我屋上三重茅。', 'images/publish/53d2d49709354994c2920e175ce5e550.jpg');
INSERT INTO `user` VALUES ('2018-12-04 04:05:04', '李白', '123456', '床前明月光，疑是地上霜。\r\n举头望明月，低头思故乡。', 'images/publish/f7cb6098f452afc3b80158db892d0096.jpg');
INSERT INTO `user` VALUES ('2018-12-05 05:40:05', '纳兰性德 ', '1234', '人生若只如初见，何事秋风悲画扇。', 'images/publish/fe879361268567726193fa8ebd3e6538.jpg');
INSERT INTO `user` VALUES ('2018-12-06 06:20:01', '辛弃疾 ', '123456', '醉里挑灯看剑，梦回吹角连营。八百里分麾下炙，五十弦翻塞外声。沙场秋点兵。\r\n马作的卢飞快，弓如霹雳弦惊。了却君王天下事，赢得生前身后名。可怜白发生！', 'images/publish/afa9ccfb39dbbbf432b25904aec2db16.png');
INSERT INTO `user` VALUES ('2018-12-07 07:01:50', '王维 ', '1234', '独在异乡为异客，每逢佳节倍思亲。\r\n遥知兄弟登高处，遍插茱萸少一人。', 'images/publish/03d5b3157446f60a46c641aec9c9993e.png');
INSERT INTO `user` VALUES ('2018-12-15 09:30:02', '白居易', '123', '一道残阳铺水中，半江瑟瑟半江红。\r\n可怜九月初三夜，露似珍珠月似弓。', 'images/publish/u=1867016340,711072067&fm=26&gp=0.jpg');
INSERT INTO `user` VALUES ('2018-12-16 08:01:03', 'admin', '123456', '干炸蘑菇', 'images/publish/0def69b9324bc55c2251f5685ddc954c.jpg');
INSERT INTO `user` VALUES ('2018-12-16 10:37:05', '天天', '123', '你好呀', null);

-- ----------------------------
-- Table structure for user_copy
-- ----------------------------
DROP TABLE IF EXISTS `user_copy`;
CREATE TABLE `user_copy` (
  `publishtime` datetime NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `imgurl` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`publishtime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user_copy
-- ----------------------------
INSERT INTO `user_copy` VALUES ('2018-12-01 00:00:00', 'zhangsan', '1234', '我要飞的更高', 'images/publish/7eac23f2c4cb281a3e425bded60c00e6.jpg');
INSERT INTO `user_copy` VALUES ('2018-12-02 00:00:00', '李清照', '123456', '生当作人杰，死亦为鬼雄。\r\n至今思项羽，不肯过江东。', 'images/publish/39200a97c165862f67a1e12666d98e0f.jpg');
INSERT INTO `user_copy` VALUES ('2018-12-03 00:00:00', '杜甫', '123456', '八月秋高风怒号，卷我屋上三重茅。', 'images/publish/53d2d49709354994c2920e175ce5e550.jpg');
INSERT INTO `user_copy` VALUES ('2018-12-04 00:00:00', '李白', '123456', '床前明月光，疑是地上霜。\r\n举头望明月，低头思故乡。', 'images/publish/f7cb6098f452afc3b80158db892d0096.jpg');
INSERT INTO `user_copy` VALUES ('2018-12-05 00:00:00', '纳兰性德 ', '1234', '人生若只如初见，何事秋风悲画扇。', 'images/publish/fe879361268567726193fa8ebd3e6538.jpg');
INSERT INTO `user_copy` VALUES ('2018-12-06 00:00:00', '辛弃疾 ', '123456', '醉里挑灯看剑，梦回吹角连营。八百里分麾下炙，五十弦翻塞外声。沙场秋点兵。\r\n马作的卢飞快，弓如霹雳弦惊。了却君王天下事，赢得生前身后名。可怜白发生！', 'images/publish/afa9ccfb39dbbbf432b25904aec2db16.png');
INSERT INTO `user_copy` VALUES ('2018-12-07 00:00:00', '王维 ', '1234', '独在异乡为异客，每逢佳节倍思亲。\r\n遥知兄弟登高处，遍插茱萸少一人。', 'images/publish/03d5b3157446f60a46c641aec9c9993e.png');
INSERT INTO `user_copy` VALUES ('2018-12-15 00:00:00', '白居易', '123', '一道残阳铺水中，半江瑟瑟半江红。\r\n可怜九月初三夜，露似珍珠月似弓。', 'images/publish/u=1867016340,711072067&fm=26&gp=0.jpg');
INSERT INTO `user_copy` VALUES ('2018-12-16 00:00:00', 'admin', '123456', '干炸蘑菇', 'images/publish/0def69b9324bc55c2251f5685ddc954c.jpg');
INSERT INTO `user_copy` VALUES ('2018-12-16 10:37:05', '天天', '123', '你好呀', null);
