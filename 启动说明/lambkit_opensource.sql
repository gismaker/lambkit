/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50711
Source Host           : 127.0.0.1:3306
Source Database       : lambkit_opensource

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2019-02-18 16:04:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for meta_api
-- ----------------------------
DROP TABLE IF EXISTS `meta_api`;
CREATE TABLE `meta_api` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sid` int(11) unsigned DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  `format` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `view_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_api
-- ----------------------------

-- ----------------------------
-- Table structure for meta_app
-- ----------------------------
DROP TABLE IF EXISTS `meta_app`;
CREATE TABLE `meta_app` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_app
-- ----------------------------
INSERT INTO `meta_app` VALUES ('1', 'default', 'localhost:8080', '1');
INSERT INTO `meta_app` VALUES ('2', 'dataserver', 'localhost:10080', '1');
INSERT INTO `meta_app` VALUES ('3', 'docserver', 'localhost:10081', '1');
INSERT INTO `meta_app` VALUES ('4', 'mapserver', 'localhost:10082', '1');

-- ----------------------------
-- Table structure for meta_field
-- ----------------------------
DROP TABLE IF EXISTS `meta_field`;
CREATE TABLE `meta_field` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tbid` int(10) unsigned NOT NULL,
  `name` varchar(45) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `datatype` varchar(45) DEFAULT NULL,
  `classtype` varchar(45) DEFAULT NULL,
  `iskey` varchar(1) DEFAULT 'N',
  `isunsigned` varchar(1) DEFAULT 'N',
  `isnullable` varchar(1) DEFAULT 'N',
  `isai` varchar(1) DEFAULT 'N',
  `flddefault` varchar(45) DEFAULT NULL,
  `descript` text,
  `isfk` varchar(1) DEFAULT 'N',
  `fktbid` int(10) unsigned DEFAULT NULL,
  `isview` varchar(32) DEFAULT NULL,
  `isselect` varchar(32) DEFAULT NULL,
  `isedit` varchar(32) DEFAULT NULL,
  `ismustfld` varchar(32) DEFAULT NULL,
  `ismap` varchar(32) DEFAULT NULL,
  `olap` varchar(16) DEFAULT NULL COMMENT 'dimession or measure',
  `orders` int(10) unsigned DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fldtbid` (`tbid`),
  CONSTRAINT `meta_field_ibfk_1` FOREIGN KEY (`tbid`) REFERENCES `meta_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=604 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_field
-- ----------------------------
INSERT INTO `meta_field` VALUES ('337', '36', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('338', '36', 'sid', 'Sid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('339', '36', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('340', '36', 'url', 'Url', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('341', '36', 'action', 'Action', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('342', '36', 'format', 'Format', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('343', '36', 'status', 'Status', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('344', '36', 'view_count', 'View Count', 'int', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('345', '37', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('346', '37', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('347', '37', 'url', 'Url', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('348', '37', 'status', 'Status', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('349', '38', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('350', '38', 'tbid', 'Tbid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('351', '38', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('352', '38', 'title', 'Title', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('353', '38', 'datatype', 'Datatype', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('354', '38', 'classtype', 'Classtype', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('355', '38', 'iskey', 'Iskey', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('356', '38', 'isunsigned', 'Isunsigned', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('357', '38', 'isnullable', 'Isnullable', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('358', '38', 'isai', 'Isai', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('359', '38', 'flddefault', 'Flddefault', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('360', '38', 'descript', 'Descript', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('361', '38', 'isfk', 'Isfk', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('362', '38', 'fktbid', 'Fktbid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('363', '38', 'isview', 'Isview', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('364', '38', 'isselect', 'Isselect', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('365', '38', 'isedit', 'Isedit', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('366', '38', 'ismustfld', 'Ismustfld', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('367', '38', 'ismap', 'Ismap', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('368', '38', 'olap', 'Olap', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('369', '38', 'orders', 'Orders', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('370', '38', 'permission', 'Permission', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('371', '39', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('372', '39', 'tbid', 'Tbid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('373', '39', 'fldid', 'Fldid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('374', '39', 'rtbid', 'Rtbid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('375', '39', 'rtype', 'Rtype', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('376', '39', 'rfldid', 'Rfldid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('377', '39', 'dtype', 'Dtype', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('378', '39', 'level_type', 'Level Type', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('379', '39', 'parentfldid', 'Parentfldid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('380', '39', 'childfldid', 'Childfldid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('381', '40', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('382', '40', 'fldid', 'Fldid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('383', '40', 'tmid', 'Tmid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('384', '40', 'checktype', 'Checktype', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('385', '40', 'edittype', 'Edittype', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('386', '40', 'editid', 'Editid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('387', '40', 'editminlen', 'Editminlen', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('388', '40', 'editmaxlen', 'Editmaxlen', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('389', '40', 'editorder', 'Editorder', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('390', '41', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('391', '41', 'fldid', 'Fldid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('392', '41', 'tmid', 'Tmid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('393', '41', 'viewname', 'Viewname', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('394', '41', 'isview', 'Isview', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('395', '41', 'isorder', 'Isorder', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('396', '41', 'viewmaxlen', 'Viewmaxlen', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('397', '41', 'viewtype', 'Viewtype', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('398', '41', 'vieworder', 'Vieworder', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('399', '41', 'issearch', 'Issearch', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('400', '41', 'searchtype', 'Searchtype', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('401', '41', 'searchinfo', 'Searchinfo', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('402', '42', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('403', '42', 'fldid', 'Fldid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('404', '42', 'mtype', 'Mtype', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('405', '42', 'srid', 'Srid', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('406', '42', 'geotype', 'Geotype', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('407', '43', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('408', '43', 'fldid', 'Fldid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('409', '43', 'name', 'Name', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('410', '43', 'type', 'Type', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('411', '43', 'agg', 'Agg', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('412', '43', 'formula', 'Formula', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('413', '44', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('414', '44', 'tbid', 'Tbid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('415', '44', 'fldid', 'Fldid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('416', '44', 'relation', 'Relation', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('417', '44', 'rtbid', 'Rtbid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('418', '44', 'rfldid', 'Rfldid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('419', '45', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('420', '45', 'sid', 'Sid', 'int', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('421', '45', 'user_id', 'User Id', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('422', '45', 'title', 'Title', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('423', '45', 'description', 'Description', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('424', '45', 'path', 'Path', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('425', '45', 'mime_type', 'Mime Type', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('426', '45', 'suffix', 'Suffix', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('427', '45', 'filesize', 'Filesize', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('428', '45', 'type', 'Type', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('429', '45', 'flag', 'Flag', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('430', '45', 'orders', 'Orders', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('431', '45', 'status', 'Status', 'tinyint', 'java.lang.Boolean', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('432', '45', 'created', 'Created', 'datetime', 'java.util.Date', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('433', '45', 'modified', 'Modified', 'datetime', 'java.util.Date', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('434', '45', 'permission', 'Permission', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('435', '46', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('436', '46', 'sid', 'Sid', 'int', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('437', '46', 'user_id', 'User Id', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('438', '46', 'pid', 'Pid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('439', '46', 'title', 'Title', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('440', '46', 'filesize', 'Filesize', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('441', '46', 'type', 'Type', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('442', '46', 'flag', 'Flag', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('443', '46', 'orders', 'Orders', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('444', '46', 'status', 'Status', 'tinyint', 'java.lang.Boolean', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('445', '46', 'created', 'Created', 'datetime', 'java.util.Date', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('446', '46', 'modified', 'Modified', 'datetime', 'java.util.Date', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('447', '46', 'permission', 'Permission', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('448', '47', 'catalog_id', 'Catalog Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'N', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('449', '47', 'file_id', 'File Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'N', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('450', '48', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('451', '48', 'sid', 'Sid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('452', '48', 'user_id', 'User Id', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('453', '48', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('454', '48', 'description', 'Description', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('455', '48', 'path', 'Path', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('456', '48', 'mime_type', 'Mime Type', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('457', '48', 'suffix', 'Suffix', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('458', '48', 'filesize', 'Filesize', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('459', '48', 'width', 'Width', 'int', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('460', '48', 'height', 'Height', 'int', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('461', '48', 'orders', 'Orders', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('462', '48', 'status', 'Status', 'tinyint', 'java.lang.Boolean', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('463', '48', 'created', 'Created', 'datetime', 'java.util.Date', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('464', '49', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('465', '49', 'sid', 'Sid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('466', '49', 'user_id', 'User Id', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('467', '49', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('468', '49', 'description', 'Description', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('469', '49', 'path', 'Path', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('470', '49', 'type', 'Type', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('471', '49', 'flag', 'Flag', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('472', '49', 'num', 'Num', 'int', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('473', '49', 'orders', 'Orders', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('474', '49', 'created', 'Created', 'datetime', 'java.util.Date', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('475', '49', 'modified', 'Modified', 'datetime', 'java.util.Date', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('476', '49', 'status', 'Status', 'tinyint', 'java.lang.Boolean', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('477', '50', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('478', '50', 'appid', 'Appid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('479', '50', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('480', '50', 'type', 'Type', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('481', '51', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('482', '51', 'sid', 'Sid', 'int', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('483', '51', 'dbname', 'Dbname', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('484', '51', 'dbtype', 'Dbtype', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('485', '51', 'dburl', 'Dburl', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('486', '51', 'user', 'User', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('487', '51', 'password', 'Password', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('488', '52', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('489', '52', 'sid', 'Sid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('490', '52', 'pid', 'Pid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('491', '52', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('492', '52', 'url', 'Url', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('493', '52', 'summary', 'Summary', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('494', '52', 'status', 'Status', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('495', '53', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('496', '53', 'sid', 'Sid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('497', '53', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('498', '53', 'url', 'Url', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('499', '53', 'summary', 'Summary', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('500', '53', 'status', 'Status', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('501', '54', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('502', '54', 'sid', 'Sid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('503', '54', 'user_id', 'User Id', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('504', '54', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('505', '54', 'title', 'Title', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('506', '54', 'keyname', 'Keyname', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('507', '54', 'namefld', 'Namefld', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('508', '54', 'olap_type', 'Olap Type', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('509', '54', 'type', 'Type', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('510', '54', 'created', 'Created', 'datetime', 'java.util.Date', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('511', '54', 'modified', 'Modified', 'datetime', 'java.util.Date', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('512', '54', 'status', 'Status', 'int', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('513', '54', 'orders', 'Orders', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('514', '54', 'permission', 'Permission', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('515', '55', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('516', '55', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('517', '55', 'userid', 'Userid', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('518', '55', 'tmtype', 'Tmtype', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('519', '55', 'active', 'Active', 'int', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('520', '56', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('521', '56', 'user_id', 'User Id', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('522', '56', 'title', 'Title', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('523', '56', 'url', 'Url', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('524', '56', 'description', 'Description', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('525', '56', 'tags', 'Tags', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('526', '56', 'ctime', 'Ctime', 'bigint unsigned', 'java.math.BigInteger', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('527', '57', 'log_id', 'Log Id', 'int', 'java.lang.Integer', 'Y', 'N', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('528', '57', 'description', 'Description', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('529', '57', 'username', 'Username', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('530', '57', 'start_time', 'Start Time', 'bigint', 'java.lang.Long', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('531', '57', 'spend_time', 'Spend Time', 'int', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('532', '57', 'base_path', 'Base Path', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('533', '57', 'uri', 'Uri', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('534', '57', 'url', 'Url', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('535', '57', 'method', 'Method', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('536', '57', 'parameter', 'Parameter', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('537', '57', 'user_agent', 'User Agent', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('538', '57', 'ip', 'Ip', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('539', '57', 'result', 'Result', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('540', '57', 'permissions', 'Permissions', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('541', '58', 'organization_id', 'Organization Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('542', '58', 'pid', 'Pid', 'int', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('543', '58', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('544', '58', 'description', 'Description', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('545', '58', 'ctime', 'Ctime', 'bigint', 'java.lang.Long', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('546', '59', 'permission_id', 'Permission Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('547', '59', 'system_id', 'System Id', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('548', '59', 'pid', 'Pid', 'int', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('549', '59', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('550', '59', 'type', 'Type', 'tinyint', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('551', '59', 'permission_value', 'Permission Value', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('552', '59', 'uri', 'Uri', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('553', '59', 'icon', 'Icon', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('554', '59', 'status', 'Status', 'tinyint', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('555', '59', 'ctime', 'Ctime', 'bigint', 'java.lang.Long', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('556', '59', 'orders', 'Orders', 'bigint', 'java.lang.Long', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('557', '60', 'role_id', 'Role Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('558', '60', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('559', '60', 'title', 'Title', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('560', '60', 'description', 'Description', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('561', '60', 'ctime', 'Ctime', 'bigint', 'java.lang.Long', 'N', 'N', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('562', '60', 'orders', 'Orders', 'bigint', 'java.lang.Long', 'N', 'N', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('563', '61', 'role_permission_id', 'Role Permission Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('564', '61', 'role_id', 'Role Id', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('565', '61', 'permission_id', 'Permission Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'N', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('566', '62', 'system_id', 'System Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('567', '62', 'icon', 'Icon', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('568', '62', 'banner', 'Banner', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('569', '62', 'theme', 'Theme', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('570', '62', 'basepath', 'Basepath', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('571', '62', 'status', 'Status', 'tinyint', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('572', '62', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('573', '62', 'title', 'Title', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('574', '62', 'description', 'Description', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('575', '62', 'ctime', 'Ctime', 'bigint', 'java.lang.Long', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('576', '62', 'orders', 'Orders', 'bigint', 'java.lang.Long', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('577', '63', 'id', 'Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('578', '63', 'user_id', 'User Id', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('579', '63', 'name', 'Name', 'varchar', 'java.lang.String', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('580', '63', 'description', 'Description', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('581', '63', 'orders', 'Orders', 'bigint unsigned', 'java.math.BigInteger', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('582', '63', 'ctime', 'Ctime', 'bigint unsigned', 'java.math.BigInteger', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('583', '64', 'user_id', 'User Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('584', '64', 'username', 'Username', 'varchar', 'java.lang.String', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('585', '64', 'password', 'Password', 'varchar', 'java.lang.String', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('586', '64', 'salt', 'Salt', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('587', '64', 'realname', 'Realname', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('588', '64', 'avatar', 'Avatar', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('589', '64', 'phone', 'Phone', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('590', '64', 'email', 'Email', 'varchar', 'java.lang.String', 'N', 'Y', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('591', '64', 'sex', 'Sex', 'tinyint', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('592', '64', 'locked', 'Locked', 'tinyint', 'java.lang.Integer', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('593', '64', 'ctime', 'Ctime', 'bigint', 'java.lang.Long', 'N', 'N', 'Y', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('594', '65', 'user_organization_id', 'User Organization Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('595', '65', 'user_id', 'User Id', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('596', '65', 'organization_id', 'Organization Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'N', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('597', '66', 'user_permission_id', 'User Permission Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('598', '66', 'user_id', 'User Id', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('599', '66', 'permission_id', 'Permission Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'N', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('600', '66', 'type', 'Type', 'tinyint', 'java.lang.Integer', 'N', 'N', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('601', '67', 'user_role_id', 'User Role Id', 'int unsigned', 'java.lang.Long', 'Y', 'Y', 'N', 'Y', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('602', '67', 'user_id', 'User Id', 'int unsigned', 'java.lang.Long', 'N', 'Y', 'N', 'N', null, null, 'N', null, 'Y', 'Y', 'Y', 'Y', 'N', null, null, null);
INSERT INTO `meta_field` VALUES ('603', '67', 'role_id', 'Role Id', 'int', 'java.lang.Integer', 'Y', 'N', 'Y', 'N', null, null, 'N', null, 'N', 'N', 'N', 'N', 'N', null, null, null);

-- ----------------------------
-- Table structure for meta_field_dimession
-- ----------------------------
DROP TABLE IF EXISTS `meta_field_dimession`;
CREATE TABLE `meta_field_dimession` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tbid` int(11) unsigned DEFAULT NULL,
  `fldid` int(11) unsigned DEFAULT NULL,
  `rtbid` int(11) unsigned DEFAULT NULL,
  `rtype` varchar(255) DEFAULT NULL COMMENT '关联类型，直连direct，字典dictionary',
  `rfldid` int(11) unsigned DEFAULT NULL COMMENT '直连模式的时候，对应的字段',
  `dtype` varchar(255) DEFAULT NULL COMMENT '维度类型，编码code or 名称name',
  `level_type` varchar(255) DEFAULT NULL COMMENT '级别类型（单表级别id为1类，字典级别关系为2类，单表上级字段3类）',
  `parentfldid` int(10) unsigned DEFAULT NULL COMMENT '上级字段',
  `childfldid` int(10) unsigned DEFAULT NULL COMMENT '下级字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_field_dimession
-- ----------------------------

-- ----------------------------
-- Table structure for meta_field_edit
-- ----------------------------
DROP TABLE IF EXISTS `meta_field_edit`;
CREATE TABLE `meta_field_edit` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fldid` int(10) unsigned DEFAULT NULL,
  `tmid` int(10) unsigned DEFAULT NULL,
  `checktype` int(10) unsigned DEFAULT NULL,
  `edittype` int(10) unsigned DEFAULT NULL,
  `editid` int(10) unsigned DEFAULT NULL,
  `editminlen` int(10) unsigned DEFAULT NULL,
  `editmaxlen` int(10) unsigned DEFAULT NULL,
  `editorder` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `meta_field_edit_ibfk_1` (`fldid`),
  CONSTRAINT `meta_field_edit_ibfk_1` FOREIGN KEY (`fldid`) REFERENCES `meta_field` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_field_edit
-- ----------------------------

-- ----------------------------
-- Table structure for meta_field_list
-- ----------------------------
DROP TABLE IF EXISTS `meta_field_list`;
CREATE TABLE `meta_field_list` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fldid` int(10) unsigned NOT NULL,
  `tmid` int(10) unsigned DEFAULT NULL COMMENT '主题id',
  `viewname` varchar(255) DEFAULT NULL,
  `isview` varchar(1) DEFAULT NULL,
  `isorder` varchar(1) DEFAULT NULL,
  `viewmaxlen` int(10) unsigned DEFAULT NULL,
  `viewtype` varchar(45) DEFAULT NULL,
  `vieworder` int(10) unsigned DEFAULT NULL,
  `issearch` varchar(1) DEFAULT NULL,
  `searchtype` varchar(255) DEFAULT NULL,
  `searchinfo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `meta_field_list_ibfk_1` (`fldid`),
  CONSTRAINT `meta_field_list_ibfk_1` FOREIGN KEY (`fldid`) REFERENCES `meta_field` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_field_list
-- ----------------------------

-- ----------------------------
-- Table structure for meta_field_map
-- ----------------------------
DROP TABLE IF EXISTS `meta_field_map`;
CREATE TABLE `meta_field_map` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fldid` int(10) unsigned NOT NULL,
  `mtype` varchar(45) DEFAULT NULL COMMENT '点、线、面',
  `srid` varchar(45) DEFAULT NULL COMMENT '坐标类型,4326',
  `geotype` varchar(45) DEFAULT NULL COMMENT 'lon,lat,point,geom,wtk等',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_field_map
-- ----------------------------

-- ----------------------------
-- Table structure for meta_field_measure
-- ----------------------------
DROP TABLE IF EXISTS `meta_field_measure`;
CREATE TABLE `meta_field_measure` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fldid` int(10) unsigned NOT NULL,
  `name` int(10) unsigned DEFAULT NULL COMMENT '度量名称',
  `type` varchar(64) DEFAULT NULL COMMENT '单字段、多字段、多表格字段等类型',
  `agg` varchar(32) DEFAULT NULL COMMENT '度量方法',
  `formula` varchar(255) DEFAULT NULL COMMENT '公式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_field_measure
-- ----------------------------

-- ----------------------------
-- Table structure for meta_field_relation
-- ----------------------------
DROP TABLE IF EXISTS `meta_field_relation`;
CREATE TABLE `meta_field_relation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tbid` int(10) unsigned DEFAULT NULL,
  `fldid` int(10) unsigned DEFAULT NULL COMMENT '关联元素',
  `relation` varchar(255) DEFAULT NULL,
  `rtbid` int(10) unsigned DEFAULT NULL COMMENT '对应的关联表格',
  `rfldid` int(10) unsigned DEFAULT NULL COMMENT '对应的关联字段名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_field_relation
-- ----------------------------

-- ----------------------------
-- Table structure for meta_file
-- ----------------------------
DROP TABLE IF EXISTS `meta_file`;
CREATE TABLE `meta_file` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '上传附件的用户ID',
  `title` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '标题',
  `description` text CHARACTER SET utf8mb4 COMMENT '附件描述',
  `path` varchar(512) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '路径',
  `mime_type` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'mime',
  `suffix` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '附件的后缀',
  `filesize` int(11) unsigned DEFAULT NULL,
  `type` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '类型',
  `flag` varchar(256) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '标示',
  `orders` int(10) unsigned DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可以被访问',
  `created` datetime DEFAULT NULL COMMENT '上传时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `permission` varchar(255) DEFAULT NULL COMMENT '表格权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_file
-- ----------------------------

-- ----------------------------
-- Table structure for meta_file_catalog
-- ----------------------------
DROP TABLE IF EXISTS `meta_file_catalog`;
CREATE TABLE `meta_file_catalog` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `user_id` int(10) unsigned DEFAULT NULL,
  `pid` int(10) unsigned DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `filesize` int(11) unsigned DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `flag` varchar(256) DEFAULT NULL,
  `orders` int(255) unsigned DEFAULT '0',
  `status` tinyint(1) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_file_catalog
-- ----------------------------

-- ----------------------------
-- Table structure for meta_file_catalog_mapping
-- ----------------------------
DROP TABLE IF EXISTS `meta_file_catalog_mapping`;
CREATE TABLE `meta_file_catalog_mapping` (
  `catalog_id` int(10) unsigned NOT NULL,
  `file_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`catalog_id`,`file_id`),
  KEY `file_id` (`file_id`),
  CONSTRAINT `meta_file_catalog_mapping_ibfk_1` FOREIGN KEY (`catalog_id`) REFERENCES `meta_file_catalog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `meta_file_catalog_mapping_ibfk_2` FOREIGN KEY (`file_id`) REFERENCES `meta_file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_file_catalog_mapping
-- ----------------------------

-- ----------------------------
-- Table structure for meta_image
-- ----------------------------
DROP TABLE IF EXISTS `meta_image`;
CREATE TABLE `meta_image` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sid` int(11) unsigned DEFAULT NULL COMMENT 'meta_image_set的id',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '上传附件的用户ID',
  `name` varchar(45) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '标题',
  `path` varchar(512) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '路径',
  `mime_type` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'mime',
  `suffix` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '附件的后缀',
  `filesize` int(11) unsigned DEFAULT NULL,
  `width` int(11) DEFAULT NULL COMMENT '类型',
  `height` int(11) DEFAULT NULL COMMENT '标示',
  `orders` int(10) unsigned DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可以被访问',
  `created` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_image
-- ----------------------------

-- ----------------------------
-- Table structure for meta_image_set
-- ----------------------------
DROP TABLE IF EXISTS `meta_image_set`;
CREATE TABLE `meta_image_set` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sid` int(10) unsigned DEFAULT NULL COMMENT 'meta_store_resource的id',
  `user_id` int(10) unsigned DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `path` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT '0',
  `orders` int(10) unsigned DEFAULT '0',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_image_set
-- ----------------------------

-- ----------------------------
-- Table structure for meta_store
-- ----------------------------
DROP TABLE IF EXISTS `meta_store`;
CREATE TABLE `meta_store` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `appid` int(10) unsigned DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_store
-- ----------------------------
INSERT INTO `meta_store` VALUES ('1', '1', 'default', 'database');
INSERT INTO `meta_store` VALUES ('2', '2', 'dataserver', 'webapi');
INSERT INTO `meta_store` VALUES ('3', '3', 'docserver', 'folder');

-- ----------------------------
-- Table structure for meta_store_db
-- ----------------------------
DROP TABLE IF EXISTS `meta_store_db`;
CREATE TABLE `meta_store_db` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sid` int(10) unsigned DEFAULT NULL,
  `dbname` varchar(255) DEFAULT NULL,
  `dbtype` varchar(255) DEFAULT NULL,
  `dburl` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_store_db
-- ----------------------------
INSERT INTO `meta_store_db` VALUES ('1', '1', 'lambkit', 'mysql', 'localhost', 'root', '');

-- ----------------------------
-- Table structure for meta_store_resource
-- ----------------------------
DROP TABLE IF EXISTS `meta_store_resource`;
CREATE TABLE `meta_store_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sid` int(10) unsigned DEFAULT NULL,
  `pid` int(10) unsigned DEFAULT NULL COMMENT '上级id',
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `summary` text,
  `status` varchar(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_store_resource
-- ----------------------------
INSERT INTO `meta_store_resource` VALUES ('1', '2', null, '附件', '/attachment', null, '1');
INSERT INTO `meta_store_resource` VALUES ('2', '2', null, '图集', '/attachment/imageset', null, '1');
INSERT INTO `meta_store_resource` VALUES ('3', '2', null, '文档', '/attachment/file', null, '1');
INSERT INTO `meta_store_resource` VALUES ('4', '2', null, '地图', '/attachment/maps', null, '1');

-- ----------------------------
-- Table structure for meta_store_route
-- ----------------------------
DROP TABLE IF EXISTS `meta_store_route`;
CREATE TABLE `meta_store_route` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sid` int(10) unsigned DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `summary` text,
  `status` varchar(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_store_route
-- ----------------------------
INSERT INTO `meta_store_route` VALUES ('1', '2', 'index', '/index', null, '1');

-- ----------------------------
-- Table structure for meta_table
-- ----------------------------
DROP TABLE IF EXISTS `meta_table`;
CREATE TABLE `meta_table` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sid` int(11) unsigned DEFAULT NULL,
  `user_id` int(11) unsigned DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `keyname` varchar(255) DEFAULT NULL COMMENT '主键名称。id,tid',
  `namefld` varchar(255) DEFAULT NULL,
  `olap_type` varchar(255) DEFAULT NULL COMMENT 'olap类型（字典1类，主表2类，映射3类，多主键字典4类）',
  `type` varchar(255) DEFAULT NULL,
  `created` datetime(6) DEFAULT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `status` int(10) DEFAULT NULL COMMENT '表格状态',
  `orders` int(10) unsigned DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL COMMENT '表格权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_table
-- ----------------------------
INSERT INTO `meta_table` VALUES ('36', '1', null, 'meta_api', 'Meta Api', 'id', null, null, null, '2019-01-07 14:28:36.000000', '2019-01-07 14:28:36.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('37', '1', null, 'meta_app', 'Meta App', 'id', null, null, null, '2019-01-07 14:28:36.000000', '2019-01-07 14:28:36.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('38', '1', null, 'meta_field', 'Meta Field', 'id', null, null, null, '2019-01-07 14:28:36.000000', '2019-01-07 14:28:36.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('39', '1', null, 'meta_field_dimession', 'Meta Field Dimession', 'id', null, null, null, '2019-01-07 14:28:37.000000', '2019-01-07 14:28:37.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('40', '1', null, 'meta_field_edit', 'Meta Field Edit', 'id', null, null, null, '2019-01-07 14:28:37.000000', '2019-01-07 14:28:37.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('41', '1', null, 'meta_field_list', 'Meta Field List', 'id', null, null, null, '2019-01-07 14:28:38.000000', '2019-01-07 14:28:38.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('42', '1', null, 'meta_field_map', 'Meta Field Map', 'id', null, null, null, '2019-01-07 14:28:38.000000', '2019-01-07 14:28:38.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('43', '1', null, 'meta_field_measure', 'Meta Field Measure', 'id', null, null, null, '2019-01-07 14:28:38.000000', '2019-01-07 14:28:38.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('44', '1', null, 'meta_field_relation', 'Meta Field Relation', 'id', null, null, null, '2019-01-07 14:28:38.000000', '2019-01-07 14:28:38.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('45', '1', null, 'meta_file', 'Meta File', 'id', null, null, null, '2019-01-07 14:28:39.000000', '2019-01-07 14:28:39.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('46', '1', null, 'meta_file_catalog', 'Meta File Catalog', 'id', null, null, null, '2019-01-07 14:28:39.000000', '2019-01-07 14:28:39.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('47', '1', null, 'meta_file_catalog_mapping', 'Meta File Catalog Mapping', 'catalog_id,file_id', null, null, null, '2019-01-07 14:28:39.000000', '2019-01-07 14:28:39.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('48', '1', null, 'meta_image', 'Meta Image', 'id', null, null, null, '2019-01-07 14:28:39.000000', '2019-01-07 14:28:39.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('49', '1', null, 'meta_image_set', 'Meta Image Set', 'id', null, null, null, '2019-01-07 14:28:40.000000', '2019-01-07 14:28:40.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('50', '1', null, 'meta_store', 'Meta Store', 'id', null, null, null, '2019-01-07 14:28:40.000000', '2019-01-07 14:28:40.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('51', '1', null, 'meta_store_db', 'Meta Store Db', 'id', null, null, null, '2019-01-07 14:28:40.000000', '2019-01-07 14:28:40.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('52', '1', null, 'meta_store_resource', 'Meta Store Resource', 'id', null, null, null, '2019-01-07 14:28:41.000000', '2019-01-07 14:28:41.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('53', '1', null, 'meta_store_route', 'Meta Store Route', 'id', null, null, null, '2019-01-07 14:28:41.000000', '2019-01-07 14:28:41.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('54', '1', null, 'meta_table', 'Meta Table', 'id', null, null, null, '2019-01-07 14:28:41.000000', '2019-01-07 14:28:41.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('55', '1', null, 'meta_theme', 'Meta Theme', 'id', null, null, null, '2019-01-07 14:28:41.000000', '2019-01-07 14:28:41.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('56', '1', null, 'upms_favorites', 'Upms Favorites', 'id', null, null, null, '2019-01-07 14:28:42.000000', '2019-01-07 14:28:42.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('57', '1', null, 'upms_log', 'Upms Log', 'log_id', null, null, null, '2019-01-07 14:28:42.000000', '2019-01-07 14:28:42.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('58', '1', null, 'upms_organization', 'Upms Organization', 'organization_id', null, null, null, '2019-01-07 14:28:42.000000', '2019-01-07 14:28:42.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('59', '1', null, 'upms_permission', 'Upms Permission', 'permission_id', null, null, null, '2019-01-07 14:28:42.000000', '2019-01-07 14:28:42.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('60', '1', null, 'upms_role', 'Upms Role', 'role_id', null, null, null, '2019-01-07 14:28:43.000000', '2019-01-07 14:28:43.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('61', '1', null, 'upms_role_permission', 'Upms Role Permission', 'role_permission_id', null, null, null, '2019-01-07 14:28:43.000000', '2019-01-07 14:28:43.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('62', '1', null, 'upms_system', 'Upms System', 'system_id', null, null, null, '2019-01-07 14:28:43.000000', '2019-01-07 14:28:43.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('63', '1', null, 'upms_tag', 'Upms Tag', 'id', null, null, null, '2019-01-07 14:28:43.000000', '2019-01-07 14:28:43.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('64', '1', null, 'upms_user', 'Upms User', 'user_id', null, null, null, '2019-01-07 14:28:44.000000', '2019-01-07 14:28:44.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('65', '1', null, 'upms_user_organization', 'Upms User Organization', 'user_organization_id', null, null, null, '2019-01-07 14:28:44.000000', '2019-01-07 14:28:44.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('66', '1', null, 'upms_user_permission', 'Upms User Permission', 'user_permission_id', null, null, null, '2019-01-07 14:28:44.000000', '2019-01-07 14:28:44.000000', '0', '1', null);
INSERT INTO `meta_table` VALUES ('67', '1', null, 'upms_user_role', 'Upms User Role', 'user_role_id', null, null, null, '2019-01-07 14:28:44.000000', '2019-01-07 14:28:44.000000', '0', '1', null);

-- ----------------------------
-- Table structure for meta_theme
-- ----------------------------
DROP TABLE IF EXISTS `meta_theme`;
CREATE TABLE `meta_theme` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '主题名称',
  `userid` int(10) unsigned DEFAULT NULL,
  `tmtype` varchar(30) DEFAULT NULL,
  `active` int(10) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_theme
-- ----------------------------
INSERT INTO `meta_theme` VALUES ('1', 'default', '1', 'lambkit', '1');

-- ----------------------------
-- Table structure for sys_fieldconfig
-- ----------------------------
DROP TABLE IF EXISTS `sys_fieldconfig`;
CREATE TABLE `sys_fieldconfig` (
  `fldid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `fldtbid` bigint(20) unsigned NOT NULL,
  `fldname` varchar(45) NOT NULL,
  `fldcnn` varchar(255) DEFAULT NULL,
  `datatype` varchar(45) DEFAULT NULL,
  `iskey` varchar(1) DEFAULT NULL,
  `isunsigned` varchar(1) DEFAULT NULL,
  `isnullable` varchar(1) DEFAULT NULL,
  `isai` varchar(1) DEFAULT NULL,
  `flddefault` varchar(45) DEFAULT NULL,
  `descript` text,
  `isfk` varchar(1) DEFAULT NULL,
  `fktbname` varchar(45) DEFAULT NULL,
  `fktbkey` varchar(45) DEFAULT NULL,
  `fldlinkfk` varchar(45) DEFAULT NULL,
  `checktype` int(11) DEFAULT NULL,
  `edittype` int(11) DEFAULT NULL,
  `fldmetaid` int(11) DEFAULT NULL,
  `isselect` varchar(1) DEFAULT NULL,
  `isview` varchar(1) DEFAULT NULL,
  `maxlenview` int(11) DEFAULT NULL,
  `viewfldorder` int(11) DEFAULT NULL,
  `isedit` varchar(1) DEFAULT NULL,
  `editorder` varchar(45) DEFAULT NULL,
  `editminlen` int(11) DEFAULT NULL,
  `editmaxlen` int(11) DEFAULT NULL,
  `ismustfld` varchar(1) DEFAULT NULL,
  `fldorder` int(11) DEFAULT NULL,
  `fldrole` int(11) DEFAULT NULL,
  `meta` varchar(255) DEFAULT NULL,
  `ext01` varchar(16) DEFAULT NULL,
  `ext02` varchar(32) DEFAULT NULL,
  `ext03` varchar(64) DEFAULT NULL,
  `ext04` varchar(128) DEFAULT NULL,
  `ext05` varchar(255) DEFAULT NULL,
  `update` longtext,
  `searchtype` varchar(255) DEFAULT NULL,
  `searchinfo` varchar(255) DEFAULT NULL,
  `tag` varchar(64) DEFAULT NULL,
  `heji` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`fldid`),
  KEY `fldtbid` (`fldtbid`),
  CONSTRAINT `sys_fieldconfig_ibfk_1` FOREIGN KEY (`fldtbid`) REFERENCES `sys_tableconfig` (`tbid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=662 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_fieldconfig
-- ----------------------------
INSERT INTO `sys_fieldconfig` VALUES ('1', '1', 'tbid', '表编号', 'int(10)', 'Y', 'Y', 'N', 'Y', '', '表编号', 'N', '', '', '', '0', '1', '0', 'Y', 'Y', '20', '1', 'N', '1', '0', '0', 'Y', '1', '1', '', 'N', 'Y', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('2', '1', 'tbname', '表英文名', 'varchar(45)', 'N', 'N', 'Y', 'N', '', '表英文名', 'N', '', '', '', '0', '1', '0', 'Y', 'Y', '20', '2', 'N', '2', '0', '0', 'N', '2', '1', '', 'N', 'Y', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('3', '1', 'tbcnn', '表中文名', 'varchar(255)', 'N', 'N', 'Y', 'N', '', '表中文名', 'N', '', '', '', '0', '1', '0', 'Y', 'Y', '20', '3', 'Y', '3', '0', '0', 'N', '3', '1', '', 'N', 'Y', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('4', '1', 'isdelete', '是否删除', 'varchar(1)', 'N', 'N', 'N', 'N', 'N', '是否删除', 'N', '', '', '', '0', '5', '1', 'N', 'N', '20', '4', 'N', '4', '0', '0', 'N', '4', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('5', '1', 'isedit', '是否编辑', 'varchar(1)', 'N', 'N', 'N', 'N', 'Y', '是否编辑', 'N', '', '', '', '0', '5', '1', 'N', 'N', '20', '5', 'Y', '5', '0', '0', 'N', '5', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('6', '1', 'ispages', '是否分页', 'varchar(1)', 'N', 'N', 'N', 'N', 'Y', '是否分页', 'N', '', '', '', '0', '5', '1', 'N', 'N', '20', '6', 'Y', '6', '0', '0', 'N', '6', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('7', '1', 'isallsel', '是否全选', 'varchar(1)', 'N', 'N', 'N', 'N', 'Y', '是否全选', 'N', '', '', '', '0', '5', '1', 'N', 'N', '20', '7', 'Y', '7', '0', '0', 'N', '7', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('8', '1', 'isorder', '是否排序', 'varchar(1)', 'N', 'N', 'N', 'N', 'Y', '是否排序', 'N', '', '', '', '0', '5', '1', 'N', 'N', '20', '8', 'Y', '8', '0', '0', 'N', '8', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('9', '1', 'isdiycol', '是否自定义列', 'varchar(1)', 'N', 'N', 'N', 'N', 'N', '是否自定义列', 'N', '', '', '', '0', '5', '1', 'N', 'N', '20', '9', 'Y', '9', '0', '0', 'N', '9', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('10', '1', 'diycolname', '自定义列名称', 'varchar(45)', 'N', 'N', 'Y', 'N', '', '自定义列名称', 'N', '', '', '', '0', '1', '0', 'N', 'N', '20', '10', 'Y', '10', '0', '0', 'N', '10', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('11', '1', 'tborder', '序号', 'int(10)', 'N', 'Y', 'N', 'N', '0', '序号', 'N', '', '', '', '0', '1', '0', 'N', 'N', '20', '11', 'Y', '11', '0', '0', 'N', '11', '1', '', 'N', 'N', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('12', '1', 'tbrole', '权限', 'int(11)', 'N', 'N', 'N', 'N', '1', '权限', 'N', '', '', '', '0', '1', '0', 'N', 'N', '20', '12', 'Y', '12', '0', '0', 'N', '12', '1', '', 'N', 'N', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('13', '1', 'tbcreatetime', '创建时间', 'datetime', 'N', 'N', 'N', 'N', '0000-00-00 00:00:00', '创建时间', 'N', '', '', '', '0', '15', '0', 'Y', 'Y', '20', '13', 'N', '13', '0', '0', 'N', '13', '1', '', 'N', 'Y', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('14', '1', 'tbmodifytime', '修改时间', 'datetime', 'N', 'N', 'N', 'N', '0000-00-00 00:00:00', '修改时间', 'N', '', '', '', '0', '15', '0', 'Y', 'Y', '20', '14', 'Y', '14', '0', '0', 'N', '14', '1', '', 'N', 'Y', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('15', '1', 'tbnavtypeid', '导航类型编号', 'int(10)', 'N', 'Y', 'N', 'N', '', '导航类型编号', 'Y', 'tb_mgr_tabletype', 'ttid', 'tbnavtypeid', '0', '1', '0', 'Y', 'Y', '20', '15', 'Y', '15', '0', '0', 'Y', '15', '1', '', 'N', 'Y', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('16', '1', 'delname', '是否删除字段名', 'varchar(45)', 'N', 'N', 'Y', 'N', '', '是否删除字段名', 'N', '', '', '', '0', '1', '0', 'N', 'N', '20', '16', 'Y', '16', '0', '0', 'N', '16', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('17', '1', 'iscreate', '是否创建', 'varchar(1)', 'N', 'N', 'N', 'N', 'N', '是否在数据库中创建该表', 'N', '', '', '', '0', '1', '0', 'N', 'N', '20', '17', 'Y', '17', '0', '0', 'N', '17', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('18', '1', 'meta', '额外', 'varchar(255)', 'N', 'N', 'Y', 'N', '', '额外', 'N', '', '', '', '0', '1', '0', 'N', 'N', '20', '18', 'Y', '18', '0', '0', 'N', '18', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('19', '1', 'ext01', '扩展01', 'varchar(16)', 'N', 'N', 'Y', 'N', '', '扩展01', 'N', '', '', '', '0', '1', '0', 'N', 'N', '20', '19', 'Y', '19', '0', '0', 'N', '19', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('20', '1', 'ext02', '扩展02', 'varchar(32)', 'N', 'N', 'Y', 'N', '', '扩展02', 'N', '', '', '', '0', '1', '0', 'N', 'N', '20', '20', 'Y', '20', '0', '0', 'N', '20', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('21', '1', 'ext03', '扩展03', 'varchar(64)', 'N', 'N', 'Y', 'N', '', '扩展03', 'N', '', '', '', '0', '1', '0', 'N', 'N', '20', '21', 'Y', '21', '0', '0', 'N', '21', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('22', '1', 'ext04', '扩展04', 'varchar(128)', 'N', 'N', 'Y', 'N', '', '扩展04', 'N', '', '', '', '0', '1', '0', 'N', 'N', '20', '22', 'Y', '22', '0', '0', 'N', '22', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('23', '1', 'ext05', '扩展05', 'varchar(255)', 'N', 'N', 'Y', 'N', '', '扩展05', 'N', '', '', '', '0', '1', '0', 'N', 'N', '20', '23', 'Y', '23', '0', '0', 'N', '23', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('24', '2', 'fldid', '字段表编号', 'int(10)', 'Y', 'Y', 'N', 'Y', '', '字段表编号', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '1', 'N', '1', '0', '0', 'Y', '1', '1', '', 'N', 'N', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('25', '2', 'fldtbid', '表编号', 'int(10)', 'N', 'Y', 'N', 'N', '', '表编号', 'Y', 'tb_tableconfig', 'tbid', 'fldtbid', '0', '1', '0', 'Y', 'Y', '20', '2', 'N', '2', '0', '0', 'Y', '2', '1', '', 'N', 'Y', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('26', '2', 'fldname', '字段名称', 'varchar(45)', 'N', 'N', 'Y', 'N', '', '字段名称', 'N', '', '', '', '0', '1', '0', 'Y', 'Y', '20', '3', 'N', '3', '0', '0', 'N', '3', '1', '', 'N', 'Y', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('27', '2', 'fldcnn', '字段中文名', 'varchar(255)', 'N', 'N', 'Y', 'N', '', '字段中文名', 'N', '', '', '', '0', '1', '0', 'Y', 'Y', '20', '4', 'Y', '4', '0', '0', 'N', '4', '1', '', 'N', 'Y', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('28', '2', 'datatype', '字段类型', 'varchar(45)', 'N', 'N', 'Y', 'N', '', '字段类型', 'N', '', '', '', '0', '1', '0', 'Y', 'Y', '20', '5', 'Y', '5', '0', '0', 'N', '5', '1', '', 'N', 'Y', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('29', '2', 'maxlenview', '最大显示长度', 'int(10)', 'N', 'N', 'N', 'N', '0', '最大显示长度', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '6', 'Y', '6', '0', '0', 'N', '6', '1', '', 'N', 'N', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('30', '2', 'viewfldorder', '显示字段排序', 'int(10)', 'N', 'Y', 'N', 'N', '0', '显示字段排序', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '7', 'Y', '7', '0', '0', 'N', '7', '1', '', 'N', 'N', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('31', '2', 'iskey', '是否主键', 'varchar(1)', 'N', 'N', 'N', 'N', 'N', '是否主键', 'N', '', '', '', '0', '5', '1', 'Y', 'Y', '20', '8', 'N', '8', '0', '0', 'N', '8', '1', '', 'N', 'Y', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('32', '2', 'isunsigned', '是否非负', 'varchar(45)', 'N', 'N', 'N', 'N', 'N', '是否非负', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '9', 'Y', '9', '0', '0', 'N', '9', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('33', '2', 'isnullable', '是否为空', 'varchar(45)', 'N', 'N', 'N', 'N', 'Y', '是否为空', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '10', 'Y', '10', '0', '0', 'N', '10', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('34', '2', 'isview', '是否显示', 'varchar(1)', 'N', 'N', 'N', 'N', 'Y', '是否显示', 'N', '', '', '', '0', '5', '1', 'Y', 'Y', '20', '11', 'Y', '11', '0', '0', 'N', '11', '1', '', 'N', 'Y', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('35', '2', 'isselect', '是否查找', 'varchar(1)', 'N', 'N', 'N', 'N', 'Y', '是否查找', 'N', '', '', '', '0', '5', '1', 'Y', 'Y', '20', '12', 'Y', '12', '0', '0', 'N', '12', '1', '', 'N', 'Y', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('36', '2', 'isai', '是否自增', 'varchar(1)', 'N', 'N', 'N', 'N', 'N', '是否自增', 'N', '', '', '', '0', '5', '1', 'Y', 'N', '20', '13', 'Y', '13', '0', '0', 'N', '13', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('37', '2', 'isfk', '是否外键', 'varchar(1)', 'N', 'N', 'N', 'N', 'N', '是否外键', 'N', '', '', '', '0', '5', '1', 'Y', 'N', '20', '14', 'Y', '14', '0', '0', 'N', '14', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('38', '2', 'fktbname', '外键链接的表名', 'varchar(45)', 'N', 'N', 'Y', 'N', '', '外键链接的表名', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '15', 'Y', '15', '0', '0', 'N', '15', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('39', '2', 'fktbkey', '外键表的主键', 'varchar(45)', 'N', 'N', 'Y', 'N', '', '外键表的主键', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '16', 'Y', '16', '0', '0', 'N', '16', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('40', '2', 'fldlinkfk', '链接外键表的字段', 'varchar(45)', 'N', 'N', 'Y', 'N', '', '链接外键表的字段', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '17', 'Y', '17', '0', '0', 'N', '17', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('41', '2', 'checktype', '验证类型', 'int(10)', 'N', 'Y', 'N', 'N', '0', '验证类型', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '18', 'Y', '18', '0', '0', 'N', '18', '1', '', 'N', 'N', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('42', '2', 'edittype', '编辑类型', 'int(10)', 'N', 'Y', 'N', 'N', '1', '编辑类型', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '19', 'Y', '19', '0', '0', 'N', '19', '1', '', 'N', 'N', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('43', '2', 'flddefault', '默认值', 'varchar(45)', 'N', 'N', 'Y', 'N', '', '默认值', 'N', '', '', '', '0', '1', '0', 'Y', 'Y', '20', '20', 'Y', '20', '0', '0', 'N', '20', '1', '', 'N', 'Y', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('44', '2', 'descript', '描述信息', 'varchar(512)', 'N', 'N', 'Y', 'N', '', '描述信息', 'N', '', '', '', '0', '1', '0', 'Y', 'Y', '20', '21', 'Y', '21', '0', '0', 'N', '21', '1', '', 'N', 'Y', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('45', '2', 'fldmetaid', '字典表编号', 'int(10)', 'N', 'Y', 'N', 'N', '0', '字典表编号', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '22', 'Y', '22', '0', '0', 'N', '22', '1', '', 'N', 'N', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('46', '2', 'isedit', '是否编辑', 'varchar(1)', 'N', 'N', 'N', 'N', 'Y', '是否编辑', 'N', '', '', '', '0', '5', '1', 'Y', 'Y', '20', '23', 'Y', '23', '0', '0', 'N', '23', '1', '', 'N', 'Y', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('47', '2', 'editorder', '编辑排序顺序', 'varchar(45)', 'N', 'N', 'N', 'N', '0', '编辑排序顺序', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '24', 'Y', '24', '0', '0', 'N', '24', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('48', '2', 'editminlen', '编辑最小长度', 'int(10)', 'N', 'N', 'N', 'N', '0', '编辑最小长度', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '25', 'Y', '25', '0', '0', 'N', '25', '1', '', 'N', 'N', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('49', '2', 'editmaxlen', '编辑最大长度', 'int(10)', 'N', 'N', 'N', 'N', '0', '编辑最大长度', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '26', 'Y', '26', '0', '0', 'N', '26', '1', '', 'N', 'N', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('50', '2', 'ismustfld', '是否必要字段', 'varchar(1)', 'N', 'N', 'N', 'N', 'Y', '是否必要字段', 'N', '', '', '', '0', '5', '1', 'Y', 'Y', '20', '27', 'Y', '27', '0', '0', 'N', '27', '1', '', 'N', 'Y', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('51', '2', 'fldorder', '字段序号', 'int(10)', 'N', 'Y', 'N', 'N', '0', '字段序号', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '28', 'Y', '28', '0', '0', 'N', '28', '1', '', 'N', 'N', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('52', '2', 'fldrole', '权限', 'int(10)', 'N', 'Y', 'Y', 'N', '1', '权限', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '29', 'Y', '29', '0', '0', 'N', '29', '1', '', 'N', 'N', '', '', '', '', '', '', '', '');
INSERT INTO `sys_fieldconfig` VALUES ('53', '2', 'meta', '额外', 'varchar(255)', 'N', 'N', 'Y', 'N', '', '额外', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '30', 'Y', '30', '0', '0', 'N', '30', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('54', '2', 'ext01', '扩展01', 'varchar(16)', 'N', 'N', 'Y', 'N', '', '扩展01', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '31', 'Y', '31', '0', '0', 'N', '31', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('55', '2', 'ext02', '扩展02', 'varchar(32)', 'N', 'N', 'Y', 'N', '', '扩展02', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '32', 'Y', '32', '0', '0', 'N', '32', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('56', '2', 'ext03', '扩展03', 'varchar(64)', 'N', 'N', 'Y', 'N', '', '扩展03', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '33', 'Y', '33', '0', '0', 'N', '33', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('57', '2', 'ext04', '扩展04', 'varchar(128)', 'N', 'N', 'Y', 'N', '', '扩展04', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '34', 'Y', '34', '0', '0', 'N', '34', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('58', '2', 'ext05', '扩展05', 'varchar(255)', 'N', 'N', 'Y', 'N', '', '扩展05', 'N', '', '', '', '0', '1', '0', 'Y', 'N', '20', '35', 'Y', '35', '0', '0', 'N', '35', '1', '', 'N', 'N', '', '', '', '', '', '', '', 'count');
INSERT INTO `sys_fieldconfig` VALUES ('395', '3', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('396', '3', 'sid', 'Sid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('397', '3', 'name', 'Name', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('398', '3', 'url', 'Url', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('399', '3', 'action', 'Action', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('400', '3', 'format', 'Format', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('401', '3', 'status', 'Status', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('402', '3', 'view_count', 'View Count', 'int', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('403', '4', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('404', '4', 'name', 'Name', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('405', '4', 'url', 'Url', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('406', '4', 'status', 'Status', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('407', '5', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('408', '5', 'tbid', 'Tbid', 'int unsigned', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('409', '5', 'name', 'Name', 'varchar', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('410', '5', 'title', 'Title', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('411', '5', 'datatype', 'Datatype', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('412', '5', 'classtype', 'Classtype', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('413', '5', 'iskey', 'Iskey', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('414', '5', 'isunsigned', 'Isunsigned', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('415', '5', 'isnullable', 'Isnullable', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('416', '5', 'isai', 'Isai', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('417', '5', 'flddefault', 'Flddefault', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('418', '5', 'descript', 'Descript', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('419', '5', 'isfk', 'Isfk', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('420', '5', 'fktbid', 'Fktbid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('421', '5', 'isview', 'Isview', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('422', '5', 'isselect', 'Isselect', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('423', '5', 'isedit', 'Isedit', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('424', '5', 'ismustfld', 'Ismustfld', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('425', '5', 'ismap', 'Ismap', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('426', '5', 'olap', 'Olap', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('427', '5', 'orders', 'Orders', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('428', '5', 'permission', 'Permission', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('429', '6', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('430', '6', 'tbid', 'Tbid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('431', '6', 'fldid', 'Fldid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('432', '6', 'rtbid', 'Rtbid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('433', '6', 'rtype', 'Rtype', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('434', '6', 'rfldid', 'Rfldid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('435', '6', 'dtype', 'Dtype', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('436', '6', 'level_type', 'Level Type', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('437', '6', 'parentfldid', 'Parentfldid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('438', '6', 'childfldid', 'Childfldid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('439', '7', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('440', '7', 'fldid', 'Fldid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('441', '7', 'tmid', 'Tmid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('442', '7', 'checktype', 'Checktype', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('443', '7', 'edittype', 'Edittype', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('444', '7', 'editid', 'Editid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('445', '7', 'editminlen', 'Editminlen', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('446', '7', 'editmaxlen', 'Editmaxlen', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('447', '7', 'editorder', 'Editorder', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('448', '8', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('449', '8', 'fldid', 'Fldid', 'int unsigned', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('450', '8', 'tmid', 'Tmid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('451', '8', 'viewname', 'Viewname', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('452', '8', 'isview', 'Isview', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('453', '8', 'isorder', 'Isorder', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('454', '8', 'viewmaxlen', 'Viewmaxlen', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('455', '8', 'viewtype', 'Viewtype', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('456', '8', 'vieworder', 'Vieworder', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('457', '8', 'issearch', 'Issearch', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('458', '8', 'searchtype', 'Searchtype', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('459', '8', 'searchinfo', 'Searchinfo', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('460', '9', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('461', '9', 'fldid', 'Fldid', 'int unsigned', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('462', '9', 'mtype', 'Mtype', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('463', '9', 'srid', 'Srid', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('464', '9', 'geotype', 'Geotype', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('465', '10', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('466', '10', 'fldid', 'Fldid', 'int unsigned', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('467', '10', 'name', 'Name', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('468', '10', 'type', 'Type', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('469', '10', 'agg', 'Agg', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('470', '10', 'formula', 'Formula', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('471', '11', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('472', '11', 'tbid', 'Tbid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('473', '11', 'fldid', 'Fldid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('474', '11', 'relation', 'Relation', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('475', '11', 'rtbid', 'Rtbid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('476', '11', 'rfldid', 'Rfldid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('477', '12', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('478', '12', 'sid', 'Sid', 'int', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('479', '12', 'user_id', 'User Id', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('480', '12', 'title', 'Title', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('481', '12', 'description', 'Description', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('482', '12', 'path', 'Path', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('483', '12', 'mime_type', 'Mime Type', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('484', '12', 'suffix', 'Suffix', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('485', '12', 'filesize', 'Filesize', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('486', '12', 'type', 'Type', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('487', '12', 'flag', 'Flag', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('488', '12', 'orders', 'Orders', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('489', '12', 'status', 'Status', 'tinyint', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('490', '12', 'created', 'Created', 'datetime', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('491', '12', 'modified', 'Modified', 'datetime', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('492', '12', 'permission', 'Permission', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('493', '13', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('494', '13', 'sid', 'Sid', 'int', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('495', '13', 'user_id', 'User Id', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('496', '13', 'pid', 'Pid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('497', '13', 'title', 'Title', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('498', '13', 'filesize', 'Filesize', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('499', '13', 'type', 'Type', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('500', '13', 'flag', 'Flag', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('501', '13', 'orders', 'Orders', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('502', '13', 'status', 'Status', 'tinyint', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('503', '13', 'created', 'Created', 'datetime', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('504', '13', 'modified', 'Modified', 'datetime', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('505', '13', 'permission', 'Permission', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('506', '14', 'catalog_id', 'Catalog Id', 'int unsigned', 'Y', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('507', '14', 'file_id', 'File Id', 'int unsigned', 'Y', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('508', '15', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('509', '15', 'sid', 'Sid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('510', '15', 'user_id', 'User Id', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('511', '15', 'name', 'Name', 'varchar', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('512', '15', 'description', 'Description', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('513', '15', 'path', 'Path', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('514', '15', 'mime_type', 'Mime Type', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('515', '15', 'suffix', 'Suffix', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('516', '15', 'filesize', 'Filesize', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('517', '15', 'width', 'Width', 'int', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('518', '15', 'height', 'Height', 'int', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('519', '15', 'orders', 'Orders', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('520', '15', 'status', 'Status', 'tinyint', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('521', '15', 'created', 'Created', 'datetime', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('522', '16', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('523', '16', 'sid', 'Sid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('524', '16', 'user_id', 'User Id', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('525', '16', 'name', 'Name', 'varchar', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('526', '16', 'description', 'Description', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('527', '16', 'path', 'Path', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('528', '16', 'type', 'Type', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('529', '16', 'flag', 'Flag', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('530', '16', 'num', 'Num', 'int', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('531', '16', 'orders', 'Orders', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('532', '16', 'created', 'Created', 'datetime', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('533', '16', 'modified', 'Modified', 'datetime', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('534', '16', 'status', 'Status', 'tinyint', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('535', '17', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('536', '17', 'appid', 'Appid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('537', '17', 'name', 'Name', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('538', '17', 'type', 'Type', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('539', '18', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('540', '18', 'sid', 'Sid', 'int', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('541', '18', 'dbname', 'Dbname', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('542', '18', 'dbtype', 'Dbtype', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('543', '18', 'dburl', 'Dburl', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('544', '18', 'user', 'User', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('545', '18', 'password', 'Password', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('546', '19', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('547', '19', 'sid', 'Sid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('548', '19', 'pid', 'Pid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('549', '19', 'name', 'Name', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('550', '19', 'url', 'Url', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('551', '19', 'summary', 'Summary', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('552', '19', 'status', 'Status', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('553', '20', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('554', '20', 'sid', 'Sid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('555', '20', 'name', 'Name', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('556', '20', 'url', 'Url', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('557', '20', 'summary', 'Summary', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('558', '20', 'status', 'Status', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('559', '21', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('560', '21', 'sid', 'Sid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('561', '21', 'user_id', 'User Id', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('562', '21', 'name', 'Name', 'varchar', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('563', '21', 'title', 'Title', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('564', '21', 'keyname', 'Keyname', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('565', '21', 'namefld', 'Namefld', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('566', '21', 'olap_type', 'Olap Type', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('567', '21', 'type', 'Type', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('568', '21', 'created', 'Created', 'datetime', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('569', '21', 'modified', 'Modified', 'datetime', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('570', '21', 'status', 'Status', 'int', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('571', '21', 'orders', 'Orders', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('572', '21', 'permission', 'Permission', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('573', '22', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('574', '22', 'name', 'Name', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('575', '22', 'userid', 'Userid', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('576', '22', 'tmtype', 'Tmtype', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('577', '22', 'active', 'Active', 'int', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('578', '23', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('579', '23', 'user_id', 'User Id', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('580', '23', 'title', 'Title', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('581', '23', 'url', 'Url', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('582', '23', 'description', 'Description', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('583', '23', 'tags', 'Tags', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('584', '23', 'ctime', 'Ctime', 'bigint unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('585', '24', 'log_id', 'Log Id', 'int', 'Y', 'N', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('586', '24', 'description', 'Description', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('587', '24', 'username', 'Username', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('588', '24', 'start_time', 'Start Time', 'bigint', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('589', '24', 'spend_time', 'Spend Time', 'int', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('590', '24', 'base_path', 'Base Path', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('591', '24', 'uri', 'Uri', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('592', '24', 'url', 'Url', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('593', '24', 'method', 'Method', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('594', '24', 'parameter', 'Parameter', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('595', '24', 'user_agent', 'User Agent', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('596', '24', 'ip', 'Ip', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('597', '24', 'result', 'Result', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('598', '24', 'permissions', 'Permissions', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('599', '25', 'organization_id', 'Organization Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('600', '25', 'pid', 'Pid', 'int', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('601', '25', 'name', 'Name', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('602', '25', 'description', 'Description', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('603', '25', 'ctime', 'Ctime', 'bigint', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('604', '26', 'permission_id', 'Permission Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('605', '26', 'system_id', 'System Id', 'int unsigned', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('606', '26', 'pid', 'Pid', 'int', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('607', '26', 'name', 'Name', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('608', '26', 'type', 'Type', 'tinyint', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('609', '26', 'permission_value', 'Permission Value', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('610', '26', 'uri', 'Uri', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('611', '26', 'icon', 'Icon', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('612', '26', 'status', 'Status', 'tinyint', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('613', '26', 'ctime', 'Ctime', 'bigint', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('614', '26', 'orders', 'Orders', 'bigint', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('615', '27', 'role_id', 'Role Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('616', '27', 'name', 'Name', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('617', '27', 'title', 'Title', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('618', '27', 'description', 'Description', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('619', '27', 'ctime', 'Ctime', 'bigint', 'N', 'N', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('620', '27', 'orders', 'Orders', 'bigint', 'N', 'N', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('621', '28', 'role_permission_id', 'Role Permission Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('622', '28', 'role_id', 'Role Id', 'int unsigned', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('623', '28', 'permission_id', 'Permission Id', 'int unsigned', 'Y', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('624', '29', 'system_id', 'System Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('625', '29', 'icon', 'Icon', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('626', '29', 'banner', 'Banner', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('627', '29', 'theme', 'Theme', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('628', '29', 'basepath', 'Basepath', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('629', '29', 'status', 'Status', 'tinyint', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('630', '29', 'name', 'Name', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('631', '29', 'title', 'Title', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('632', '29', 'description', 'Description', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('633', '29', 'ctime', 'Ctime', 'bigint', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('634', '29', 'orders', 'Orders', 'bigint', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('635', '30', 'id', 'Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('636', '30', 'user_id', 'User Id', 'int unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('637', '30', 'name', 'Name', 'varchar', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('638', '30', 'description', 'Description', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('639', '30', 'orders', 'Orders', 'bigint unsigned', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('640', '30', 'ctime', 'Ctime', 'bigint unsigned', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('641', '31', 'user_id', 'User Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('642', '31', 'username', 'Username', 'varchar', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('643', '31', 'password', 'Password', 'varchar', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('644', '31', 'salt', 'Salt', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('645', '31', 'realname', 'Realname', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('646', '31', 'avatar', 'Avatar', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('647', '31', 'phone', 'Phone', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('648', '31', 'email', 'Email', 'varchar', 'N', 'Y', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('649', '31', 'sex', 'Sex', 'tinyint', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('650', '31', 'locked', 'Locked', 'tinyint', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('651', '31', 'ctime', 'Ctime', 'bigint', 'N', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('652', '32', 'user_organization_id', 'User Organization Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('653', '32', 'user_id', 'User Id', 'int unsigned', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('654', '32', 'organization_id', 'Organization Id', 'int unsigned', 'Y', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('655', '33', 'user_permission_id', 'User Permission Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('656', '33', 'user_id', 'User Id', 'int unsigned', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('657', '33', 'permission_id', 'Permission Id', 'int unsigned', 'Y', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('658', '33', 'type', 'Type', 'tinyint', 'N', 'N', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('659', '34', 'user_role_id', 'User Role Id', 'int unsigned', 'Y', 'Y', 'N', 'Y', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('660', '34', 'user_id', 'User Id', 'int unsigned', 'N', 'Y', 'N', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_fieldconfig` VALUES ('661', '34', 'role_id', 'Role Id', 'int', 'Y', 'N', 'Y', 'N', null, null, null, null, null, null, '0', '1', '0', 'Y', 'Y', '0', '0', 'N', '0', '0', '0', 'Y', '0', '1', '', 'N', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_tableconfig
-- ----------------------------
DROP TABLE IF EXISTS `sys_tableconfig`;
CREATE TABLE `sys_tableconfig` (
  `tbid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tbname` varchar(45) NOT NULL,
  `tbcnn` varchar(255) DEFAULT NULL,
  `isdelete` varchar(1) DEFAULT 'N',
  `isedit` varchar(1) DEFAULT 'Y',
  `ispages` varchar(1) DEFAULT 'Y',
  `isallsel` varchar(1) DEFAULT 'Y',
  `isorder` varchar(1) DEFAULT 'Y',
  `isdiycol` varchar(1) DEFAULT 'N',
  `diycolname` varchar(45) DEFAULT NULL,
  `tborder` int(11) DEFAULT NULL,
  `tbrole` int(11) DEFAULT NULL,
  `tbcreatetime` datetime(6) DEFAULT NULL,
  `tbmodifytime` datetime(6) DEFAULT NULL,
  `tbnavtypeid` int(11) DEFAULT '1',
  `delname` varchar(45) DEFAULT NULL,
  `iscreate` varchar(1) DEFAULT NULL,
  `meta` varchar(255) DEFAULT NULL,
  `ext01` varchar(16) DEFAULT NULL,
  `ext02` varchar(32) DEFAULT NULL,
  `ext03` varchar(64) DEFAULT NULL,
  `ext04` varchar(128) DEFAULT NULL,
  `ext05` varchar(255) DEFAULT NULL,
  `orglevel` varchar(32) DEFAULT NULL,
  `tbtype` varchar(32) DEFAULT NULL,
  `haslonlat` varchar(2) DEFAULT 'N',
  `hasorglevel` varchar(2) DEFAULT 'N',
  PRIMARY KEY (`tbid`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_tableconfig
-- ----------------------------
INSERT INTO `sys_tableconfig` VALUES ('1', 'sys_tableconfig', '表配置信息表', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, '1', '1', '2016-07-04 16:42:53.000000', '2016-07-04 16:42:53.000000', '1', 'isdelete', 'Y', '', '', '', '', '', '', '', '', 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('2', 'sys_fieldconfig', '字段配置信息表', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, '2', '1', '2016-07-04 16:42:53.000000', '2016-07-04 16:42:53.000000', '1', '', 'Y', '', '', '', '', '', '', '', '', 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('3', 'meta_api', 'Meta Api', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:16.000000', '2019-01-07 14:33:16.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('4', 'meta_app', 'Meta App', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:17.000000', '2019-01-07 14:33:17.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('5', 'meta_field', 'Meta Field', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:17.000000', '2019-01-07 14:33:17.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('6', 'meta_field_dimession', 'Meta Field Dimession', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:17.000000', '2019-01-07 14:33:17.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('7', 'meta_field_edit', 'Meta Field Edit', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:18.000000', '2019-01-07 14:33:18.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('8', 'meta_field_list', 'Meta Field List', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:18.000000', '2019-01-07 14:33:18.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('9', 'meta_field_map', 'Meta Field Map', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:18.000000', '2019-01-07 14:33:18.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('10', 'meta_field_measure', 'Meta Field Measure', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:18.000000', '2019-01-07 14:33:18.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('11', 'meta_field_relation', 'Meta Field Relation', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:18.000000', '2019-01-07 14:33:18.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('12', 'meta_file', 'Meta File', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:19.000000', '2019-01-07 14:33:19.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('13', 'meta_file_catalog', 'Meta File Catalog', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:19.000000', '2019-01-07 14:33:19.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('14', 'meta_file_catalog_mapping', 'Meta File Catalog Mapping', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:19.000000', '2019-01-07 14:33:19.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('15', 'meta_image', 'Meta Image', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:19.000000', '2019-01-07 14:33:19.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('16', 'meta_image_set', 'Meta Image Set', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:20.000000', '2019-01-07 14:33:20.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('17', 'meta_store', 'Meta Store', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:20.000000', '2019-01-07 14:33:20.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('18', 'meta_store_db', 'Meta Store Db', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:20.000000', '2019-01-07 14:33:20.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('19', 'meta_store_resource', 'Meta Store Resource', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:20.000000', '2019-01-07 14:33:20.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('20', 'meta_store_route', 'Meta Store Route', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:21.000000', '2019-01-07 14:33:21.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('21', 'meta_table', 'Meta Table', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:21.000000', '2019-01-07 14:33:21.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('22', 'meta_theme', 'Meta Theme', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:22.000000', '2019-01-07 14:33:22.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('23', 'upms_favorites', 'Upms Favorites', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:22.000000', '2019-01-07 14:33:22.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('24', 'upms_log', 'Upms Log', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:22.000000', '2019-01-07 14:33:22.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('25', 'upms_organization', 'Upms Organization', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:22.000000', '2019-01-07 14:33:22.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('26', 'upms_permission', 'Upms Permission', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:22.000000', '2019-01-07 14:33:22.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('27', 'upms_role', 'Upms Role', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:23.000000', '2019-01-07 14:33:23.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('28', 'upms_role_permission', 'Upms Role Permission', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:23.000000', '2019-01-07 14:33:23.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('29', 'upms_system', 'Upms System', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:23.000000', '2019-01-07 14:33:23.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('30', 'upms_tag', 'Upms Tag', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:23.000000', '2019-01-07 14:33:23.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('31', 'upms_user', 'Upms User', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:23.000000', '2019-01-07 14:33:23.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('32', 'upms_user_organization', 'Upms User Organization', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:24.000000', '2019-01-07 14:33:24.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('33', 'upms_user_permission', 'Upms User Permission', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:24.000000', '2019-01-07 14:33:24.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');
INSERT INTO `sys_tableconfig` VALUES ('34', 'upms_user_role', 'Upms User Role', 'N', 'Y', 'Y', 'Y', 'Y', 'N', null, null, null, '2019-01-07 14:33:24.000000', '2019-01-07 14:33:24.000000', '1', null, 'N', null, null, null, null, null, null, null, null, 'N', 'N');

-- ----------------------------
-- Table structure for upms_favorites
-- ----------------------------
DROP TABLE IF EXISTS `upms_favorites`;
CREATE TABLE `upms_favorites` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '收藏内容的标题',
  `url` varchar(255) DEFAULT NULL COMMENT '收藏内容的原文地址，不带域名',
  `description` text COMMENT '收藏内容的描述',
  `tags` varchar(255) DEFAULT NULL,
  `ctime` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of upms_favorites
-- ----------------------------
INSERT INTO `upms_favorites` VALUES ('1', '1', '节点管理', '/lambkit/node', null, '', '1545883440');

-- ----------------------------
-- Table structure for upms_log
-- ----------------------------
DROP TABLE IF EXISTS `upms_log`;
CREATE TABLE `upms_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `description` varchar(100) DEFAULT NULL COMMENT '操作描述',
  `username` varchar(20) DEFAULT NULL COMMENT '操作用户',
  `start_time` bigint(20) DEFAULT NULL COMMENT '操作时间',
  `spend_time` int(11) DEFAULT NULL COMMENT '消耗时间',
  `base_path` varchar(500) DEFAULT NULL COMMENT '根路径',
  `uri` varchar(500) DEFAULT NULL COMMENT 'URI',
  `url` varchar(500) DEFAULT NULL COMMENT 'URL',
  `method` varchar(10) DEFAULT NULL COMMENT '请求类型',
  `parameter` mediumtext,
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户标识',
  `ip` varchar(30) DEFAULT NULL COMMENT 'IP地址',
  `result` mediumtext,
  `permissions` varchar(100) DEFAULT NULL COMMENT '权限值',
  PRIMARY KEY (`log_id`),
  KEY `log_id` (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志';

-- ----------------------------
-- Records of upms_log
-- ----------------------------

-- ----------------------------
-- Table structure for upms_organization
-- ----------------------------
DROP TABLE IF EXISTS `upms_organization`;
CREATE TABLE `upms_organization` (
  `organization_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pid` int(10) DEFAULT NULL COMMENT '所属上级',
  `name` varchar(20) DEFAULT NULL COMMENT '组织名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '组织描述',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='组织';

-- ----------------------------
-- Records of upms_organization
-- ----------------------------
INSERT INTO `upms_organization` VALUES ('1', null, '总部', '北京总部', '1');
INSERT INTO `upms_organization` VALUES ('4', null, '河北分部', '河北石家庄', '1488122466236');
INSERT INTO `upms_organization` VALUES ('5', null, '河南分部', '河南郑州', '1488122480265');
INSERT INTO `upms_organization` VALUES ('6', null, '湖北分部', '湖北武汉', '1488122493265');
INSERT INTO `upms_organization` VALUES ('7', null, '湖南分部', '湖南长沙', '1488122502752');

-- ----------------------------
-- Table structure for upms_permission
-- ----------------------------
DROP TABLE IF EXISTS `upms_permission`;
CREATE TABLE `upms_permission` (
  `permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `system_id` int(10) unsigned NOT NULL COMMENT '所属系统',
  `pid` int(10) DEFAULT NULL COMMENT '所属上级',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型(1:目录,2:菜单,3:按钮)',
  `permission_value` varchar(50) DEFAULT NULL COMMENT '权限值',
  `uri` varchar(100) DEFAULT NULL COMMENT '路径',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态(0:禁止,1:正常)',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COMMENT='权限';

-- ----------------------------
-- Records of upms_permission
-- ----------------------------
INSERT INTO `upms_permission` VALUES ('1', '1', '0', '系统组织管理', '1', '', '', 'zmdi zmdi-accounts-list', '1', '1', '1');
INSERT INTO `upms_permission` VALUES ('2', '1', '1', '系统管理', '2', 'upms:system:read', '/upms/system/index', '', '1', '2', '2');
INSERT INTO `upms_permission` VALUES ('3', '1', '1', '组织管理', '2', 'upms:organization:read', '/upms/organization/index', '', '1', '3', '3');
INSERT INTO `upms_permission` VALUES ('4', '1', '0', '角色用户管理', '1', '', '', 'zmdi zmdi-accounts', '1', '4', '4');
INSERT INTO `upms_permission` VALUES ('5', '1', '4', '角色管理', '2', 'upms:role:read', '/upms/role/index', '', '1', '6', '6');
INSERT INTO `upms_permission` VALUES ('6', '1', '4', '用户管理', '2', 'upms:user:read', '/upms/user/index', '', '1', '5', '5');
INSERT INTO `upms_permission` VALUES ('7', '1', '0', '权限资源管理', '1', '', '', 'zmdi zmdi-lock-outline', '1', '7', '7');
INSERT INTO `upms_permission` VALUES ('12', '1', '0', '其他数据管理', '1', '', '', 'zmdi zmdi-more', '1', '12', '12');
INSERT INTO `upms_permission` VALUES ('14', '1', '12', '会话管理', '2', 'upms:session:read', '/upms/session/index', '', '1', '14', '14');
INSERT INTO `upms_permission` VALUES ('15', '1', '12', '日志记录', '2', 'upms:log:read', '/upms/log/index', '', '1', '15', '15');
INSERT INTO `upms_permission` VALUES ('17', '2', '0', '标签类目管理', '1', null, null, 'zmdi zmdi-menu', '1', '17', '17');
INSERT INTO `upms_permission` VALUES ('18', '2', '17', '标签管理', '2', 'cms:tag:read', '/upms/tag/index', null, '1', '18', '18');
INSERT INTO `upms_permission` VALUES ('19', '2', '17', '类目管理', '2', 'cms:category:read', '/upms/category/index', null, '1', '19', '19');
INSERT INTO `upms_permission` VALUES ('20', '2', '0', '文章评论管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '20', '20');
INSERT INTO `upms_permission` VALUES ('21', '2', '20', '文章管理', '2', 'cms:article:read', '/upms/article/index', null, '1', '21', '21');
INSERT INTO `upms_permission` VALUES ('22', '2', '20', '回收管理', '2', 'cms:article:read', '/upms/article/recycle', null, '1', '22', '22');
INSERT INTO `upms_permission` VALUES ('24', '1', '2', '新增系统', '3', 'upms:system:create', '/upms/system/create', 'zmdi zmdi-plus', '1', '24', '24');
INSERT INTO `upms_permission` VALUES ('25', '1', '2', '编辑系统', '3', 'upms:system:update', '/upms/system/update', 'zmdi zmdi-edit', '1', '25', '25');
INSERT INTO `upms_permission` VALUES ('26', '1', '2', '删除系统', '3', 'upms:system:delete', '/upms/system/delete', 'zmdi zmdi-close', '1', '26', '26');
INSERT INTO `upms_permission` VALUES ('27', '1', '3', '新增组织', '3', 'upms:organization:create', '/upms/organization/create', 'zmdi zmdi-plus', '1', '27', '27');
INSERT INTO `upms_permission` VALUES ('28', '1', '3', '编辑组织', '3', 'upms:organization:update', '/upms/organization/update', 'zmdi zmdi-edit', '1', '28', '28');
INSERT INTO `upms_permission` VALUES ('29', '1', '3', '删除组织', '3', 'upms:organization:delete', '/upms/organization/delete', 'zmdi zmdi-close', '1', '29', '29');
INSERT INTO `upms_permission` VALUES ('30', '1', '6', '新增用户', '3', 'upms:user:create', '/upms/user/create', 'zmdi zmdi-plus', '1', '30', '30');
INSERT INTO `upms_permission` VALUES ('31', '1', '6', '编辑用户', '3', 'upms:user:update', '/upms/user/update', 'zmdi zmdi-edit', '1', '31', '31');
INSERT INTO `upms_permission` VALUES ('32', '1', '6', '删除用户', '3', 'upms:user:delete', '/upms/user/delete', 'zmdi zmdi-close', '1', '32', '32');
INSERT INTO `upms_permission` VALUES ('33', '1', '5', '新增角色', '3', 'upms:role:create', '/upms/role/create', 'zmdi zmdi-plus', '1', '33', '33');
INSERT INTO `upms_permission` VALUES ('34', '1', '5', '编辑角色', '3', 'upms:role:update', '/upms/role/update', 'zmdi zmdi-edit', '1', '34', '34');
INSERT INTO `upms_permission` VALUES ('35', '1', '5', '删除角色', '3', 'upms:role:delete', '/upms/role/delete', 'zmdi zmdi-close', '1', '35', '35');
INSERT INTO `upms_permission` VALUES ('36', '1', '39', '新增权限', '3', 'upms:permission:create', '/upms/permission/create', 'zmdi zmdi-plus', '1', '36', '36');
INSERT INTO `upms_permission` VALUES ('37', '1', '39', '编辑权限', '3', 'upms:permission:update', '/upms/permission/update', 'zmdi zmdi-edit', '1', '37', '37');
INSERT INTO `upms_permission` VALUES ('38', '1', '39', '删除权限', '3', 'upms:permission:delete', '/upms/permission/delete', 'zmdi zmdi-close', '1', '38', '38');
INSERT INTO `upms_permission` VALUES ('39', '1', '7', '权限管理', '2', 'upms:permission:read', '/upms/permission/index', null, '1', '39', '39');
INSERT INTO `upms_permission` VALUES ('46', '1', '5', '角色权限', '3', 'upms:role:permission', '/upms/role/permission', 'zmdi zmdi-key', '1', '1488091928257', '1488091928257');
INSERT INTO `upms_permission` VALUES ('48', '1', '6', '用户组织', '3', 'upms:user:organization', '/upms/user/organization', 'zmdi zmdi-accounts-list', '1', '1488120011165', '1488120011165');
INSERT INTO `upms_permission` VALUES ('50', '1', '6', '用户角色', '3', 'upms:user:role', '/upms/user/role', 'zmdi zmdi-accounts', '1', '1488120554175', '1488120554175');
INSERT INTO `upms_permission` VALUES ('51', '1', '6', '用户权限', '3', 'upms:user:permission', '/upms/user/permission', 'zmdi zmdi-key', '1', '1488092013302', '1488092013302');
INSERT INTO `upms_permission` VALUES ('53', '1', '14', '强制退出', '3', 'upms:session:forceout', '/upms/session/forceout', 'zmdi zmdi-run', '1', '1488379514715', '1488379514715');
INSERT INTO `upms_permission` VALUES ('54', '2', '18', '新增标签', '3', 'cms:tag:create', '/upms/tag/create', 'zmdi zmdi-plus', '1', '1489417315159', '1489417315159');
INSERT INTO `upms_permission` VALUES ('55', '2', '18', '编辑标签', '3', 'cms:tag:update', 'zmdi zmdi-edit', 'zmdi zmdi-widgets', '1', '1489417344931', '1489417344931');
INSERT INTO `upms_permission` VALUES ('56', '2', '18', '删除标签', '3', 'cms:tag:delete', '/upms/tag/delete', 'zmdi zmdi-close', '1', '1489417372114', '1489417372114');
INSERT INTO `upms_permission` VALUES ('57', '1', '15', '删除权限', '3', 'upms:log:delete', '/upms/log/delete', 'zmdi zmdi-close', '1', '1489503867909', '1489503867909');
INSERT INTO `upms_permission` VALUES ('58', '2', '19', '编辑类目', '3', 'cms:category:update', '/upms/category/update', 'zmdi zmdi-edit', '1', '1489586600462', '1489586600462');
INSERT INTO `upms_permission` VALUES ('59', '2', '19', '删除类目', '3', 'cms:category:delete', '/upms/category/delete', 'zmdi zmdi-close', '1', '1489586633059', '1489586633059');
INSERT INTO `upms_permission` VALUES ('60', '2', '19', '新增类目', '3', 'cms:category:create', '/upms/category/create', 'zmdi zmdi-plus', '1', '1489590342089', '1489590342089');
INSERT INTO `upms_permission` VALUES ('61', '2', '0', '其他数据管理', '1', '', '', 'zmdi zmdi-more', '1', '1489835455359', '1489835455359');
INSERT INTO `upms_permission` VALUES ('62', '2', '20', '评论管理', '2', 'cms:comment:read', '/upms/comment/index', '', '1', '1489591408224', '1489591408224');
INSERT INTO `upms_permission` VALUES ('63', '2', '62', '删除评论', '3', 'cms:comment:delete', '/upms/comment/delete', 'zmdi zmdi-close', '1', '1489591449614', '1489591449614');
INSERT INTO `upms_permission` VALUES ('64', '2', '79', '单页管理', '2', 'cms:page:read', '/upms/page/index', '', '1', '1489591332779', '1489591332779');
INSERT INTO `upms_permission` VALUES ('65', '2', '64', '新增单页', '3', 'cms:page:create', '/upms/page/create', 'zmdi zmdi-plus', '1', '1489591614473', '1489591614473');
INSERT INTO `upms_permission` VALUES ('66', '2', '64', '编辑单页', '3', 'cms:page:update', '/upms/page/update', 'zmdi zmdi-edit', '1', '1489591653000', '1489591653000');
INSERT INTO `upms_permission` VALUES ('67', '2', '64', '删除单页', '3', 'cms:page:delete', '/upms/page/delete', 'zmdi zmdi-close', '1', '1489591683552', '1489591683552');
INSERT INTO `upms_permission` VALUES ('68', '2', '61', '菜单管理', '2', 'cms:menu:read', '/upms/menu/index', 'zmdi zmdi-widgets', '1', '1489591746846', '1489591746846');
INSERT INTO `upms_permission` VALUES ('69', '2', '68', '新增菜单', '3', 'cms:menu:create', '/upms/menu/create', 'zmdi zmdi-plus', '1', '1489591791747', '1489591791747');
INSERT INTO `upms_permission` VALUES ('70', '2', '68', '编辑菜单', '3', 'cms:menu:update', '/upms/menu/update', 'zmdi zmdi-edit', '1', '1489591831878', '1489591831878');
INSERT INTO `upms_permission` VALUES ('71', '2', '68', '删除菜单', '3', 'cms:menu:delete', '/upms/menu/delete', 'zmdi zmdi-close', '1', '1489591865454', '1489591865454');
INSERT INTO `upms_permission` VALUES ('72', '2', '61', '系统设置', '2', 'cms:setting:read', '/upms/setting/index', 'zmdi zmdi-widgets', '1', '1489591981165', '1489591981165');
INSERT INTO `upms_permission` VALUES ('73', '2', '72', '新增设置', '3', 'cms:setting:create', '/upms/setting/create', 'zmdi zmdi-plus', '1', '1489592024762', '1489592024762');
INSERT INTO `upms_permission` VALUES ('74', '2', '72', '编辑设置', '3', 'cms:setting:update', '/upms/setting/update', 'zmdi zmdi-edit', '1', '1489592052582', '1489592052582');
INSERT INTO `upms_permission` VALUES ('75', '2', '72', '删除设置', '3', 'cms:setting:delete', '/upms/setting/delete', 'zmdi zmdi-close', '1', '1489592081426', '1489592081426');
INSERT INTO `upms_permission` VALUES ('76', '2', '21', '新增文章', '3', 'cms:article:create', '/upms/article/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('77', '2', '21', '编辑文章', '3', 'cms:article:update', '/upms/article/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('78', '2', '21', '删除文章', '3', 'cms:article:delete', '/upms/article/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');
INSERT INTO `upms_permission` VALUES ('79', '2', '0', '单页专题管理', '1', '', '', 'zmdi zmdi-view-web', '1', '1489835320327', '1489835320327');
INSERT INTO `upms_permission` VALUES ('80', '2', '79', '专题管理', '2', 'cms:topic:read', '/upms/topic/index', 'zmdi zmdi-widgets', '1', '1489591507566', '1489591507566');
INSERT INTO `upms_permission` VALUES ('81', '2', '80', '新增专题', '3', 'cms:topic:create', '/upms/topic/create', 'zmdi zmdi-plus', '1', '1489843327028', '1489843327028');
INSERT INTO `upms_permission` VALUES ('82', '2', '80', '编辑专题', '3', 'cms:topic:update', '/upms/topic/update', 'zmdi zmdi-edit', '1', '1489843351513', '1489843351513');
INSERT INTO `upms_permission` VALUES ('83', '2', '80', '删除专题', '3', 'cms:topic:delete', '/upms/topic/delete', 'zmdi zmdi-close', '1', '1489843379953', '1489843379953');
INSERT INTO `upms_permission` VALUES ('84', '2', '68', '上移菜单', '3', 'cms:menu:up', '/upms/menu/up', 'zmdi zmdi-long-arrow-up', '1', '1489846486548', '1489846486548');
INSERT INTO `upms_permission` VALUES ('85', '2', '68', '下移菜单', '3', 'cms:menu:down', '/upms/menu/down', 'zmdi zmdi-long-arrow-down', '1', '1489846578051', '1489846578051');

-- ----------------------------
-- Table structure for upms_role
-- ----------------------------
DROP TABLE IF EXISTS `upms_role`;
CREATE TABLE `upms_role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `title` varchar(20) DEFAULT NULL COMMENT '角色标题',
  `description` varchar(1000) DEFAULT NULL COMMENT '角色描述',
  `ctime` bigint(20) NOT NULL COMMENT '创建时间',
  `orders` bigint(20) NOT NULL COMMENT '排序',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of upms_role
-- ----------------------------
INSERT INTO `upms_role` VALUES ('1', 'super', '超级管理员', '拥有所有权限', '1', '1');
INSERT INTO `upms_role` VALUES ('2', 'admin', '管理员', '拥有除权限管理系统外的所有权限', '1487471013117', '1487471013117');
INSERT INTO `upms_role` VALUES ('3', 'member', '会员', '拥有会员查看权限！', '1487471013117', '1487471013117');

-- ----------------------------
-- Table structure for upms_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `upms_role_permission`;
CREATE TABLE `upms_role_permission` (
  `role_permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色编号',
  `permission_id` int(10) unsigned NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`role_permission_id`),
  KEY `FK_Reference_23` (`role_id`),
  CONSTRAINT `upms_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `upms_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- ----------------------------
-- Records of upms_role_permission
-- ----------------------------
INSERT INTO `upms_role_permission` VALUES ('1', '1', '1');
INSERT INTO `upms_role_permission` VALUES ('2', '1', '2');
INSERT INTO `upms_role_permission` VALUES ('3', '1', '3');
INSERT INTO `upms_role_permission` VALUES ('4', '1', '4');
INSERT INTO `upms_role_permission` VALUES ('5', '1', '5');
INSERT INTO `upms_role_permission` VALUES ('6', '1', '6');
INSERT INTO `upms_role_permission` VALUES ('7', '1', '7');
INSERT INTO `upms_role_permission` VALUES ('8', '1', '39');
INSERT INTO `upms_role_permission` VALUES ('12', '1', '12');
INSERT INTO `upms_role_permission` VALUES ('14', '1', '14');
INSERT INTO `upms_role_permission` VALUES ('15', '1', '15');
INSERT INTO `upms_role_permission` VALUES ('17', '1', '17');
INSERT INTO `upms_role_permission` VALUES ('19', '1', '19');
INSERT INTO `upms_role_permission` VALUES ('20', '1', '20');
INSERT INTO `upms_role_permission` VALUES ('21', '1', '21');
INSERT INTO `upms_role_permission` VALUES ('24', '1', '24');
INSERT INTO `upms_role_permission` VALUES ('27', '1', '27');
INSERT INTO `upms_role_permission` VALUES ('28', '1', '28');
INSERT INTO `upms_role_permission` VALUES ('29', '1', '29');
INSERT INTO `upms_role_permission` VALUES ('30', '1', '30');
INSERT INTO `upms_role_permission` VALUES ('31', '1', '31');
INSERT INTO `upms_role_permission` VALUES ('32', '1', '32');
INSERT INTO `upms_role_permission` VALUES ('33', '1', '33');
INSERT INTO `upms_role_permission` VALUES ('34', '1', '34');
INSERT INTO `upms_role_permission` VALUES ('35', '1', '35');
INSERT INTO `upms_role_permission` VALUES ('36', '1', '36');
INSERT INTO `upms_role_permission` VALUES ('37', '1', '37');
INSERT INTO `upms_role_permission` VALUES ('38', '1', '38');
INSERT INTO `upms_role_permission` VALUES ('39', '1', '46');
INSERT INTO `upms_role_permission` VALUES ('40', '1', '51');
INSERT INTO `upms_role_permission` VALUES ('44', '1', '48');
INSERT INTO `upms_role_permission` VALUES ('45', '1', '50');
INSERT INTO `upms_role_permission` VALUES ('47', '1', '53');
INSERT INTO `upms_role_permission` VALUES ('48', '1', '18');
INSERT INTO `upms_role_permission` VALUES ('49', '1', '54');
INSERT INTO `upms_role_permission` VALUES ('50', '1', '54');
INSERT INTO `upms_role_permission` VALUES ('51', '1', '55');
INSERT INTO `upms_role_permission` VALUES ('52', '1', '54');
INSERT INTO `upms_role_permission` VALUES ('53', '1', '55');
INSERT INTO `upms_role_permission` VALUES ('54', '1', '56');
INSERT INTO `upms_role_permission` VALUES ('55', '1', '57');
INSERT INTO `upms_role_permission` VALUES ('56', '1', '58');
INSERT INTO `upms_role_permission` VALUES ('57', '1', '58');
INSERT INTO `upms_role_permission` VALUES ('58', '1', '59');
INSERT INTO `upms_role_permission` VALUES ('59', '1', '60');
INSERT INTO `upms_role_permission` VALUES ('60', '1', '61');
INSERT INTO `upms_role_permission` VALUES ('61', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('62', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('63', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('64', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('65', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('66', '1', '64');
INSERT INTO `upms_role_permission` VALUES ('67', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('68', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('69', '1', '64');
INSERT INTO `upms_role_permission` VALUES ('70', '1', '65');
INSERT INTO `upms_role_permission` VALUES ('71', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('72', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('73', '1', '64');
INSERT INTO `upms_role_permission` VALUES ('74', '1', '65');
INSERT INTO `upms_role_permission` VALUES ('75', '1', '66');
INSERT INTO `upms_role_permission` VALUES ('76', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('77', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('78', '1', '64');
INSERT INTO `upms_role_permission` VALUES ('79', '1', '65');
INSERT INTO `upms_role_permission` VALUES ('80', '1', '66');
INSERT INTO `upms_role_permission` VALUES ('81', '1', '67');
INSERT INTO `upms_role_permission` VALUES ('82', '1', '68');
INSERT INTO `upms_role_permission` VALUES ('83', '1', '69');
INSERT INTO `upms_role_permission` VALUES ('84', '1', '69');
INSERT INTO `upms_role_permission` VALUES ('85', '1', '70');
INSERT INTO `upms_role_permission` VALUES ('86', '1', '69');
INSERT INTO `upms_role_permission` VALUES ('87', '1', '70');
INSERT INTO `upms_role_permission` VALUES ('88', '1', '71');
INSERT INTO `upms_role_permission` VALUES ('89', '1', '72');
INSERT INTO `upms_role_permission` VALUES ('90', '1', '72');
INSERT INTO `upms_role_permission` VALUES ('91', '1', '73');
INSERT INTO `upms_role_permission` VALUES ('92', '1', '72');
INSERT INTO `upms_role_permission` VALUES ('93', '1', '73');
INSERT INTO `upms_role_permission` VALUES ('94', '1', '74');
INSERT INTO `upms_role_permission` VALUES ('95', '1', '72');
INSERT INTO `upms_role_permission` VALUES ('96', '1', '73');
INSERT INTO `upms_role_permission` VALUES ('97', '1', '74');
INSERT INTO `upms_role_permission` VALUES ('98', '1', '75');
INSERT INTO `upms_role_permission` VALUES ('99', '1', '76');
INSERT INTO `upms_role_permission` VALUES ('100', '1', '76');
INSERT INTO `upms_role_permission` VALUES ('101', '1', '77');
INSERT INTO `upms_role_permission` VALUES ('102', '1', '76');
INSERT INTO `upms_role_permission` VALUES ('103', '1', '77');
INSERT INTO `upms_role_permission` VALUES ('105', '1', '79');
INSERT INTO `upms_role_permission` VALUES ('106', '1', '80');
INSERT INTO `upms_role_permission` VALUES ('107', '1', '81');
INSERT INTO `upms_role_permission` VALUES ('108', '1', '81');
INSERT INTO `upms_role_permission` VALUES ('109', '1', '82');
INSERT INTO `upms_role_permission` VALUES ('110', '1', '81');
INSERT INTO `upms_role_permission` VALUES ('111', '1', '82');
INSERT INTO `upms_role_permission` VALUES ('112', '1', '83');
INSERT INTO `upms_role_permission` VALUES ('113', '1', '84');
INSERT INTO `upms_role_permission` VALUES ('114', '1', '84');
INSERT INTO `upms_role_permission` VALUES ('115', '1', '85');
INSERT INTO `upms_role_permission` VALUES ('121', '1', '78');
INSERT INTO `upms_role_permission` VALUES ('122', '1', '78');
INSERT INTO `upms_role_permission` VALUES ('124', '1', '25');
INSERT INTO `upms_role_permission` VALUES ('125', '1', '26');

-- ----------------------------
-- Table structure for upms_system
-- ----------------------------
DROP TABLE IF EXISTS `upms_system`;
CREATE TABLE `upms_system` (
  `system_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `banner` varchar(150) DEFAULT NULL COMMENT '背景',
  `theme` varchar(50) DEFAULT NULL COMMENT '主题',
  `basepath` varchar(100) DEFAULT NULL COMMENT '根目录',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态(-1:黑名单,1:正常)',
  `name` varchar(20) DEFAULT NULL COMMENT '系统名称',
  `title` varchar(20) DEFAULT NULL COMMENT '系统标题',
  `description` varchar(300) DEFAULT NULL COMMENT '系统描述',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`system_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='系统';

-- ----------------------------
-- Records of upms_system
-- ----------------------------
INSERT INTO `upms_system` VALUES ('1', 'zmdi zmdi-shield-security', '/lambkit/assets/upms/images/zheng-upms.png', '#29A176', 'http://127.0.0.1:8080', '1', 'zheng-upms-server', '权限管理系统', '用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）', '1', '1');
INSERT INTO `upms_system` VALUES ('2', 'zmdi zmdi-wikipedia', '/lambkit/assets/upms/images/zheng-cms.png', '#455EC5', 'http://cms.zhangshuzheng.cn:2222', '1', 'zheng-cms-admin', '内容管理系统', '内容管理系统（门户、博客、论坛、问答等）', '2', '2');
INSERT INTO `upms_system` VALUES ('3', 'zmdi zmdi-paypal-alt', '/lambkit/assets/upms/images/zheng-pay.png', '#F06292', 'http://pay.zhangshuzheng.cn:3331', '1', 'zheng-pay-admin', '支付管理系统', '支付管理系统', '3', '3');
INSERT INTO `upms_system` VALUES ('4', 'zmdi zmdi-account', '/lambkit/assets/upms/images/zheng-ucenter.png', '#6539B4', 'http://ucenter.zhangshuzheng.cn:4441', '1', 'zheng-ucenter-home', '用户管理系统', '用户管理系统', '4', '4');
INSERT INTO `upms_system` VALUES ('5', 'zmdi zmdi-cloud', '/lambkit/assets/upms/images/zheng-oss.png', '#0B8DE5', 'http://oss.zhangshuzheng.cn:7771', '1', 'zheng-oss-web', '存储管理系统', '存储管理系统', '5', '5');
INSERT INTO `upms_system` VALUES ('6', 'zmdi zmdi-cloud', '/lambkit/assets/upms/images/zheng-oss.png', '#6539B4', 'http://127.0.0.1:8070', '1', 'lambkit', '测试系统', '测试系统', '6', '6');
INSERT INTO `upms_system` VALUES ('7', 'zmdi zmdi-cloud', '/lambkit/assets/upms/images/zheng-oss.png', '#6539B4', 'http://127.0.0.1:9090', '1', 'csus', '测试系统', '测试系统', '7', '7');

-- ----------------------------
-- Table structure for upms_tag
-- ----------------------------
DROP TABLE IF EXISTS `upms_tag`;
CREATE TABLE `upms_tag` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '标签编号',
  `user_id` int(11) unsigned DEFAULT NULL,
  `name` varchar(20) NOT NULL COMMENT '名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `orders` bigint(20) unsigned NOT NULL COMMENT '排序',
  `ctime` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cms_tag_orders` (`orders`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

-- ----------------------------
-- Records of upms_tag
-- ----------------------------
INSERT INTO `upms_tag` VALUES ('1', '1', 'JAVA', 'java标签', '1489585694864', '1489585694864');
INSERT INTO `upms_tag` VALUES ('2', '1', 'Android', 'android标签', '1489585720382', '1489585720382');
INSERT INTO `upms_tag` VALUES ('3', '1', 'lambkit', 'lambkit标签', '1489585815042', '1489585815042');
INSERT INTO `upms_tag` VALUES ('4', '1', '谈恋爱', '谈恋爱标签', '1489585815043', '1489585815043');
INSERT INTO `upms_tag` VALUES ('5', '1', 'java', 'java标签', '1489585815044', '1489585815044');
INSERT INTO `upms_tag` VALUES ('6', '1', '日本地震专题', '日本经历灭国性地震，彻底沉入海底', '1489585815044', '1489585815044');

-- ----------------------------
-- Table structure for upms_user
-- ----------------------------
DROP TABLE IF EXISTS `upms_user`;
CREATE TABLE `upms_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) NOT NULL COMMENT '帐号',
  `password` varchar(32) NOT NULL COMMENT '密码MD5(密码+盐)',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `realname` varchar(20) DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(150) DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `locked` tinyint(4) DEFAULT NULL COMMENT '状态(0:正常,1:锁定)',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of upms_user
-- ----------------------------
INSERT INTO `upms_user` VALUES ('1', 'admin', '3038D9CB63B3152A79B8153FB06C02F7', '66f1b370c660445a8657bf8bf1794486', '管理员', '/lambkit/assets/upms/images/avatar.jpg', '', '276782534@qq.com', '1', '0', '1493394720495');
INSERT INTO `upms_user` VALUES ('2', 'test', '285C9762F5F9046F5893F752DFAF3476', 'd2d0d03310444ad388a8b290b0fe8564', '测试用户', '/lambkit/assets/upms/images/avatar.jpg', '', '276782534@qq.com', '1', '0', '1493394720495');
INSERT INTO `upms_user` VALUES ('3', 'demo', '939B3A88921D1FF21AFE2764C3D05D8D', '6f5bedd4255f45308d184de1e5e8da41', '演示', '/lambkit/assets/upms/images/avatar.jpg', null, '276782534@qq.com', '1', '0', '1493394720495');

-- ----------------------------
-- Table structure for upms_user_organization
-- ----------------------------
DROP TABLE IF EXISTS `upms_user_organization`;
CREATE TABLE `upms_user_organization` (
  `user_organization_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `organization_id` int(10) unsigned NOT NULL COMMENT '组织编号',
  PRIMARY KEY (`user_organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='用户组织关联表';

-- ----------------------------
-- Records of upms_user_organization
-- ----------------------------
INSERT INTO `upms_user_organization` VALUES ('19', '1', '1');
INSERT INTO `upms_user_organization` VALUES ('20', '1', '4');
INSERT INTO `upms_user_organization` VALUES ('21', '1', '5');
INSERT INTO `upms_user_organization` VALUES ('22', '1', '6');
INSERT INTO `upms_user_organization` VALUES ('23', '1', '7');

-- ----------------------------
-- Table structure for upms_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `upms_user_permission`;
CREATE TABLE `upms_user_permission` (
  `user_permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `permission_id` int(10) unsigned NOT NULL COMMENT '权限编号',
  `type` tinyint(4) NOT NULL COMMENT '权限类型(-1:减权限,1:增权限)',
  PRIMARY KEY (`user_permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COMMENT='用户权限关联表';

-- ----------------------------
-- Records of upms_user_permission
-- ----------------------------
INSERT INTO `upms_user_permission` VALUES ('3', '1', '22', '-1');
INSERT INTO `upms_user_permission` VALUES ('4', '1', '22', '1');
INSERT INTO `upms_user_permission` VALUES ('5', '2', '24', '-1');
INSERT INTO `upms_user_permission` VALUES ('6', '2', '26', '-1');
INSERT INTO `upms_user_permission` VALUES ('7', '2', '27', '-1');
INSERT INTO `upms_user_permission` VALUES ('8', '2', '29', '-1');
INSERT INTO `upms_user_permission` VALUES ('9', '2', '32', '-1');
INSERT INTO `upms_user_permission` VALUES ('10', '2', '51', '-1');
INSERT INTO `upms_user_permission` VALUES ('11', '2', '48', '-1');
INSERT INTO `upms_user_permission` VALUES ('12', '2', '50', '-1');
INSERT INTO `upms_user_permission` VALUES ('13', '2', '35', '-1');
INSERT INTO `upms_user_permission` VALUES ('14', '2', '46', '-1');
INSERT INTO `upms_user_permission` VALUES ('15', '2', '37', '-1');
INSERT INTO `upms_user_permission` VALUES ('16', '2', '38', '-1');
INSERT INTO `upms_user_permission` VALUES ('17', '2', '57', '-1');
INSERT INTO `upms_user_permission` VALUES ('18', '2', '56', '-1');
INSERT INTO `upms_user_permission` VALUES ('19', '2', '59', '-1');
INSERT INTO `upms_user_permission` VALUES ('20', '2', '78', '-1');
INSERT INTO `upms_user_permission` VALUES ('21', '2', '67', '-1');
INSERT INTO `upms_user_permission` VALUES ('22', '2', '83', '-1');
INSERT INTO `upms_user_permission` VALUES ('23', '2', '71', '-1');
INSERT INTO `upms_user_permission` VALUES ('24', '2', '75', '-1');

-- ----------------------------
-- Table structure for upms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `upms_user_role`;
CREATE TABLE `upms_user_role` (
  `user_role_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `role_id` int(10) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of upms_user_role
-- ----------------------------
INSERT INTO `upms_user_role` VALUES ('4', '1', '1');
INSERT INTO `upms_user_role` VALUES ('5', '1', '2');
INSERT INTO `upms_user_role` VALUES ('6', '2', '1');
INSERT INTO `upms_user_role` VALUES ('7', '2', '2');
INSERT INTO `upms_user_role` VALUES ('13', '4', '3');
INSERT INTO `upms_user_role` VALUES ('17', '3', '3');
