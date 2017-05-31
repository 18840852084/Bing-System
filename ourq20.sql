/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : ourq20

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2016-05-04 12:11:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attrlevel
-- ----------------------------
DROP TABLE IF EXISTS `attrlevel`;
CREATE TABLE `attrlevel` (
  `attrID` int(11) NOT NULL AUTO_INCREMENT,
  `attrName` varchar(30) DEFAULT NULL,
  `attrLevel` int(11) DEFAULT NULL,
  PRIMARY KEY (`attrID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attrlevel
-- ----------------------------
INSERT INTO `attrlevel` VALUES ('1', 'gender', '0');
INSERT INTO `attrlevel` VALUES ('2', 'isMarriage', '0');
INSERT INTO `attrlevel` VALUES ('3', 'isAlive', '0');
INSERT INTO `attrlevel` VALUES ('4', 'nationality', '2');
INSERT INTO `attrlevel` VALUES ('5', 'occupation', '1');
INSERT INTO `attrlevel` VALUES ('6', 'dateOfBorn', '0');
INSERT INTO `attrlevel` VALUES ('7', 'nativePlace', '2');
INSERT INTO `attrlevel` VALUES ('8', 'popuAttr1', '3');
INSERT INTO `attrlevel` VALUES ('9', 'popuAttr2', '3');
INSERT INTO `attrlevel` VALUES ('10', 'popuAttr3', '3');
INSERT INTO `attrlevel` VALUES ('11', 'popuAttr4', '3');

-- ----------------------------
-- Table structure for attrvalue
-- ----------------------------
DROP TABLE IF EXISTS `attrvalue`;
CREATE TABLE `attrvalue` (
  `value` varchar(30) NOT NULL,
  `attrID` int(11) DEFAULT NULL,
  KEY `attrId` (`attrID`),
  CONSTRAINT `attrId` FOREIGN KEY (`attrID`) REFERENCES `attrlevel` (`attrID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attrvalue
-- ----------------------------
INSERT INTO `attrvalue` VALUES ('女', '1');
INSERT INTO `attrvalue` VALUES ('男', '1');
INSERT INTO `attrvalue` VALUES ('0', '2');
INSERT INTO `attrvalue` VALUES ('1', '2');
INSERT INTO `attrvalue` VALUES ('1', '3');
INSERT INTO `attrvalue` VALUES ('韩国', '4');
INSERT INTO `attrvalue` VALUES ('加拿大', '4');
INSERT INTO `attrvalue` VALUES ('中国', '4');
INSERT INTO `attrvalue` VALUES ('澳大利亚', '4');
INSERT INTO `attrvalue` VALUES ('演员', '5');
INSERT INTO `attrvalue` VALUES ('歌手', '5');
INSERT INTO `attrvalue` VALUES ('模特儿', '5');
INSERT INTO `attrvalue` VALUES ('主持人', '5');
INSERT INTO `attrvalue` VALUES ('30', '6');
INSERT INTO `attrvalue` VALUES ('40', '6');
INSERT INTO `attrvalue` VALUES ('50', '6');
INSERT INTO `attrvalue` VALUES ('60', '6');
INSERT INTO `attrvalue` VALUES ('内地', '7');
INSERT INTO `attrvalue` VALUES ('香港', '7');
INSERT INTO `attrvalue` VALUES ('台湾', '7');
INSERT INTO `attrvalue` VALUES ('0', '3');
INSERT INTO `attrvalue` VALUES ('20', '6');
INSERT INTO `attrvalue` VALUES ('美国', '4');
INSERT INTO `attrvalue` VALUES ('《仙剑奇侠传》', '8');
INSERT INTO `attrvalue` VALUES ('《爱情公寓》', '9');
INSERT INTO `attrvalue` VALUES ('《小时代》', '10');
INSERT INTO `attrvalue` VALUES ('《古剑奇谭》', '11');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `name` varchar(30) DEFAULT NULL,
  `gender` char(4) DEFAULT NULL,
  `dateOfBorn` datetime DEFAULT NULL,
  `occupation` varchar(30) DEFAULT NULL,
  `nationality` varchar(30) DEFAULT NULL,
  `nativePlace` varchar(30) DEFAULT NULL,
  `isMarriage` tinyint(1) DEFAULT NULL,
  `isAlive` tinyint(1) DEFAULT NULL,
  `popuAttr1` int(30) DEFAULT '0',
  `popuAttr2` int(30) DEFAULT '0',
  `popuAttr3` int(30) DEFAULT '0',
  `popuAttr4` int(30) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('刘德华', '男', '1961-09-27 00:00:00', '演员、歌手、填词人', '中国', '香港', '1', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('吴亦凡', '男', '1990-11-06 00:00:00', '歌手、演员', '加拿大', '内地', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('宋智孝', '女', '1981-08-15 00:00:00', '演员、主持人、模特儿', '韩国', '韩国', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('张学友', '男', '1961-07-10 00:00:00', '歌手\n', '中国', '香港', '1', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('朴信惠', '女', '1990-02-18 00:00:00', '演员', '韩国', '韩国', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('林俊杰', '男', '1981-03-27 00:00:00', '歌手', '新加坡', '台湾', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('王菲', '女', '1969-08-08 00:00:00', '歌手', '中国', '香港', '1', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('范玮琪', '女', '1976-03-18 00:00:00', '歌手、演员', '美国', '台湾', '1', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('蔡依林', '女', '1980-09-15 00:00:00', '歌手，演员', '中国', '台湾', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('郑恺', '男', '1986-04-17 00:00:00', '演员', '中国', '内地', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('高圆圆', '女', '1979-10-05 00:00:00', '演员', '中国', '内地', '1', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('angelababy', '女', '1989-02-28 00:00:00', '演员、歌手', '中国', '内地', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('刘亦菲', '女', '1987-08-25 00:00:00', '演员、歌手', '美国', '内地', '0', '1', '1', '0', '0', '0');
INSERT INTO `person` VALUES ('刘诗诗', '女', '1987-03-10 00:00:00', '演员', '中国', '内地', '1', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('唐嫣', '女', '1983-12-06 00:00:00', '演员', '中国', '内地', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('孙俪', '女', '1982-09-26 00:00:00', '演员', '中国', '内地', '1', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('张智霖', '男', '1971-08-27 00:00:00', '歌手、演员', '澳大利亚', '香港', '1', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('李钟硕', '男', '1989-09-14 00:00:00', '演员、模特儿', '韩国', '韩国', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('杨幂', '女', '1986-09-12 00:00:00', '演员、歌手', '中国', '内地', '1', '1', '1', '0', '1', '1');
INSERT INTO `person` VALUES ('柳岩', '女', '1980-11-08 00:00:00', '主持人、演员、歌手', '中国', '内地', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('田馥甄', '女', '1983-03-30 00:00:00', '歌手、演员、主持人', '中国', '内地', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('胡歌', '男', '1982-09-20 00:00:00', '演员、歌手', '中国', '内地', '0', '1', '1', '0', '0', '0');
INSERT INTO `person` VALUES ('赵丽颖', '女', '1987-10-16 00:00:00', '演员', '中国', '内地', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('金钟国', '男', '1976-04-25 00:00:00', '歌手', '韩国', '韩国', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('陈赫', '男', '1985-11-09 00:00:00', '演员', '中国', '内地', '0', '1', '0', '1', '0', '0');
INSERT INTO `person` VALUES ('周杰伦', '男', '1979-01-18 00:00:00', '歌手、导演、演员、音乐人', '中国', '台湾', '1', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('李易峰', '男', '1987-05-04 00:00:00', '歌手、演员', '中国', '内地', '0', '1', '0', '0', '0', '1');
INSERT INTO `person` VALUES ('娄艺潇', '女', '1988-12-27 00:00:00', '演员', '中国', '内地', '0', '1', '0', '1', '0', '0');
INSERT INTO `person` VALUES ('王俊凯', '男', '1999-09-21 00:00:00', '歌手', '中国', '内地', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('成龙', '男', '1954-04-07 00:00:00', '演员、歌手', '中国', '香港', '1', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('金秀贤', '男', '1988-02-16 00:00:00', '演员', '韩国', '韩国', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('陈奕迅', '男', '1974-07-24 00:00:00', '歌手', '中国', '香港', '1', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('李晨', '男', '1978-11-24 00:00:00', '演员', '中国', '内地', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('鹿晗', '男', '1990-04-20 00:00:00', '歌手、演员', '中国', '内地', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('王宝强', '男', '1984-01-01 00:00:00', '演员', '中国', '内地', '1', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('权志龙', '男', '1988-08-18 00:00:00', '歌手', '韩国', '韩国', '0', '1', '0', '0', '0', '0');
INSERT INTO `person` VALUES ('邓紫棋', '女', '1991-08-16 00:00:00', '歌手', '中国', '香港', '0', '1', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for specpro
-- ----------------------------
DROP TABLE IF EXISTS `specpro`;
CREATE TABLE `specpro` (
  `name` varchar(255) NOT NULL DEFAULT '',
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of specpro
-- ----------------------------
INSERT INTO `specpro` VALUES ('angelababy', '艺名是英文名', '做过模特', '整过容', '《奔跑吧兄弟》中的女汉子', '6');
INSERT INTO `specpro` VALUES ('刘亦菲', '出国留学过', '演过金庸武侠拍成的影视作品', '曾经在美国生活过', '神仙姐姐', '7');
INSERT INTO `specpro` VALUES ('刘德华', '得过金马奖', '出演过有名的电影系列片', '有女儿', '导演或出演过《无间道》', '8');
INSERT INTO `specpro` VALUES ('刘诗诗', '游戏《轩辕剑》中出现的人物', '出演过《仙剑奇侠传三》', '出演过穿越题材的电视剧', '和比她年龄大的人结婚了', '0');
INSERT INTO `specpro` VALUES ('吴亦凡', '在EXO中', '退出了Sm公司的艺人', '是队长', '会中文的加拿大华裔', '4');
INSERT INTO `specpro` VALUES ('周杰伦', '名字中有J', '会在舞台上弹钢琴', '在一部飙车电影演出过', '会使用双节棍', '9');
INSERT INTO `specpro` VALUES ('唐嫣', '最好的朋友是杨幂', '曾与邱泽有过一段', '与糖果有关', '穿著紫色衣服', '0');
INSERT INTO `specpro` VALUES ('娄艺潇', '虐弟狂魔', '经常暴粗口', '很喜欢艾莉丝', '跆拳道黑段', '0');
INSERT INTO `specpro` VALUES ('孙俪', '甄嬛传里的人物', '是短发', '有个儿子', '在作品中很有权力', '0');
INSERT INTO `specpro` VALUES ('宋智孝', 'Running Man的其中一员', '周一情侣中的人', '一直带着帽子', '个性温柔', '0');
INSERT INTO `specpro` VALUES ('张学友', '有歌神的称号', '出演过《东成西就》', '唱情歌', '音乐超人', '0');
INSERT INTO `specpro` VALUES ('张智霖', '经常和他的孩子住在一起', '和有名的人结婚了', '有个儿子', '姓张', '0');
INSERT INTO `specpro` VALUES ('成龙', '擅长中国功夫', '有一个很大的鼻子', '儿子有吸毒', '来自Another', '0');
INSERT INTO `specpro` VALUES ('朴信惠', '和朴信惠有关', '出演过《原来是美男》', '经常和男神合作', '《继承者》中的女主角', '0');
INSERT INTO `specpro` VALUES ('权志龙', '五人组中的一员', '某个团队的首领', 'bigbang中的一员', '胳膊上有纹身', '0');
INSERT INTO `specpro` VALUES ('李易峰', '使用巨剑', '小鲜肉，非常帅', '煞气疯子', '盗墓笔记话剧的演员', '6');
INSERT INTO `specpro` VALUES ('李晨', '在电视连续剧中结婚了', '《奔跑吧兄弟》中的大黑牛', '和冰有关', '赛车手', '0');
INSERT INTO `specpro` VALUES ('李钟硕', '姓李', '参演过电视剧《学习2013》', '出演过学生的角色', '出演过有超能力的角色', '0');
INSERT INTO `specpro` VALUES ('杨幂', '仙剑奇侠传中的人物', '在古劍奇譚中饰演某角色', '有怀孕，出演过穿越剧', '脚很臭', '0');
INSERT INTO `specpro` VALUES ('林俊杰', '名字中有J字', '来自新加坡', '小酒窝', '', '0');
INSERT INTO `specpro` VALUES ('柳岩', '因为她的胸而有名', '在电台做主持人', '有巨乳', '', '4');
INSERT INTO `specpro` VALUES ('王俊凯', '学生', '音乐团体组合的队长', '重庆人', '属于一个三人组合', '0');
INSERT INTO `specpro` VALUES ('王宝强', '很会傻笑', '当过农民工', '非常倒霉或者很2', '《奔跑吧兄弟》第一季中的一员', '0');
INSERT INTO `specpro` VALUES ('王菲', '放课后茶会（HTT）的成员之一', '曾经有过姐弟恋', '曾经是流行天后', '姓王', '5');
INSERT INTO `specpro` VALUES ('田馥甄', '三人组合的一员', '有单张唱片', '姓田', '', '0');
INSERT INTO `specpro` VALUES ('胡歌', '喂养了很多猫咪', '出过车祸', '多次电视剧', '游戏《轩辕剑》中出现的人物', '0');
INSERT INTO `specpro` VALUES ('范玮琪', '有双胞胎孩子', '腿长', '丈夫是篮球运动员', 'Love life公益创始人', '0');
INSERT INTO `specpro` VALUES ('蔡依林', '跳舞跳得很好', '经常染发', '是或曾经是流行天后', '舞娘相关', '0');
INSERT INTO `specpro` VALUES ('赵丽颖', '出演过于正的剧', '出演过陆贞传奇', '与张翰合作过', '和冯小刚合作过', '0');
INSERT INTO `specpro` VALUES ('邓紫棋', '母亲还在世', '会弹钢琴', '铁肺小天后', '开过大型演唱会', '0');
INSERT INTO `specpro` VALUES ('郑恺', '会贴身保护小公主', '喜欢搭讪', '《奔跑吧兄弟》中跑的很快', '阑尾兄弟中的一个', '5');
INSERT INTO `specpro` VALUES ('金秀贤', '能让时间停止', '眼里有星星', '外星人', '姓金', '0');
INSERT INTO `specpro` VALUES ('金钟国', 'Running Man的其中一员', '姓金', '擅长使用摔跤或角力的技巧', '肌肉很发达', '4');
INSERT INTO `specpro` VALUES ('陈奕迅', '唱功很厉害', '经常穿着怪异的衣服', '发型多变而且夸张', '是卷发', '4');
INSERT INTO `specpro` VALUES ('陈赫', '口头禅是“你是猪吗？”', '《奔跑吧兄弟》中的一员', '帅哥一枚', '笑声十分猥琐', '4');
INSERT INTO `specpro` VALUES ('高圆圆', '出演过金庸武侠剧', '出生在北京', '来自一天屠龙记', '最近两年结婚的', '3');
INSERT INTO `specpro` VALUES ('鹿晗', '退出了Sm公司的艺人', '鹿晗', '头上有角', '在EXO中', '0');
