/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : shiro-study

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-01-20 12:23:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
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
-- Records of log
-- ----------------------------

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `end` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知表';

-- ----------------------------
-- Records of notification
-- ----------------------------

-- ----------------------------
-- Table structure for oauth2_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_client`;
CREATE TABLE `oauth2_client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_name` varchar(100) DEFAULT NULL COMMENT '客户端名称',
  `client_id` varchar(100) DEFAULT NULL COMMENT '客户端ID',
  `client_secret` varchar(100) DEFAULT NULL COMMENT '客户端安全key',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth2_client
-- ----------------------------
INSERT INTO `oauth2_client` VALUES ('1', 'oauth2-client', 'c1ebe466-1cdc-4bd3-ab69-77c3561b9dee', 'd8346ea2-6017-43ed-ad68-19c0f971738b');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
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
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COMMENT='权限';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '1', '0', '系统组织管理', '1', '', '', 'zmdi zmdi-accounts-list', '1', '1', '1');
INSERT INTO `permission` VALUES ('2', '1', '1', '系统管理', '2', 'upms:system:read', '/manage/system/index', '', '1', '2', '2');
INSERT INTO `permission` VALUES ('3', '1', '1', '组织管理', '2', 'upms:organization:read', '/manage/organization/index', '', '1', '3', '3');
INSERT INTO `permission` VALUES ('4', '1', '0', '角色用户管理', '1', '', '', 'zmdi zmdi-accounts', '1', '4', '4');
INSERT INTO `permission` VALUES ('5', '1', '4', '角色管理', '2', 'upms:role:read', '/manage/role/index', '', '1', '6', '6');
INSERT INTO `permission` VALUES ('6', '1', '4', '用户管理', '2', 'upms:user:read', '/manage/user/index', '', '1', '5', '5');
INSERT INTO `permission` VALUES ('7', '1', '0', '权限资源管理', '1', '', '', 'zmdi zmdi-lock-outline', '1', '7', '7');
INSERT INTO `permission` VALUES ('12', '1', '0', '其他数据管理', '1', '', '', 'zmdi zmdi-more', '1', '12', '12');
INSERT INTO `permission` VALUES ('14', '1', '12', '会话管理', '2', 'upms:session:read', '/manage/session/index', '', '1', '14', '14');
INSERT INTO `permission` VALUES ('15', '1', '12', '日志记录', '2', 'upms:log:read', '/manage/log/index', '', '1', '15', '15');
INSERT INTO `permission` VALUES ('17', '2', '0', '标签类目管理', '1', null, null, 'zmdi zmdi-menu', '1', '17', '17');
INSERT INTO `permission` VALUES ('18', '2', '17', '标签管理', '2', 'cms:tag:read', '/manage/tag/index', null, '1', '18', '18');
INSERT INTO `permission` VALUES ('19', '2', '17', '类目管理', '2', 'cms:category:read', '/manage/category/index', null, '1', '19', '19');
INSERT INTO `permission` VALUES ('20', '2', '0', '文章评论管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '20', '20');
INSERT INTO `permission` VALUES ('21', '2', '20', '文章管理', '2', 'cms:article:read', '/manage/article/index', null, '1', '21', '21');
INSERT INTO `permission` VALUES ('22', '2', '20', '回收管理', '2', 'cms:article:read', '/manage/article/recycle', null, '1', '22', '22');
INSERT INTO `permission` VALUES ('24', '1', '2', '新增系统', '3', 'upms:system:create', '/manage/system/create', 'zmdi zmdi-plus', '1', '24', '24');
INSERT INTO `permission` VALUES ('25', '1', '2', '编辑系统', '3', 'upms:system:update', '/manage/system/update', 'zmdi zmdi-edit', '1', '25', '25');
INSERT INTO `permission` VALUES ('26', '1', '2', '删除系统', '3', 'upms:system:delete', '/manage/system/delete', 'zmdi zmdi-close', '1', '26', '26');
INSERT INTO `permission` VALUES ('27', '1', '3', '新增组织', '3', 'upms:organization:create', '/manage/organization/create', 'zmdi zmdi-plus', '1', '27', '27');
INSERT INTO `permission` VALUES ('28', '1', '3', '编辑组织', '3', 'upms:organization:update', '/manage/organization/update', 'zmdi zmdi-edit', '1', '28', '28');
INSERT INTO `permission` VALUES ('29', '1', '3', '删除组织', '3', 'upms:organization:delete', '/manage/organization/delete', 'zmdi zmdi-close', '1', '29', '29');
INSERT INTO `permission` VALUES ('30', '1', '6', '新增用户', '3', 'upms:user:create', '/manage/user/create', 'zmdi zmdi-plus', '1', '30', '30');
INSERT INTO `permission` VALUES ('31', '1', '6', '编辑用户', '3', 'upms:user:update', '/manage/user/update', 'zmdi zmdi-edit', '1', '31', '31');
INSERT INTO `permission` VALUES ('32', '1', '6', '删除用户', '3', 'upms:user:delete', '/manage/user/delete', 'zmdi zmdi-close', '1', '32', '32');
INSERT INTO `permission` VALUES ('33', '1', '5', '新增角色', '3', 'upms:role:create', '/manage/role/create', 'zmdi zmdi-plus', '1', '33', '33');
INSERT INTO `permission` VALUES ('34', '1', '5', '编辑角色', '3', 'upms:role:update', '/manage/role/update', 'zmdi zmdi-edit', '1', '34', '34');
INSERT INTO `permission` VALUES ('35', '1', '5', '删除角色', '3', 'upms:role:delete', '/manage/role/delete', 'zmdi zmdi-close', '1', '35', '35');
INSERT INTO `permission` VALUES ('36', '1', '39', '新增权限', '3', 'upms:permission:create', '/manage/permission/create', 'zmdi zmdi-plus', '1', '36', '36');
INSERT INTO `permission` VALUES ('37', '1', '39', '编辑权限', '3', 'upms:permission:update', '/manage/permission/update', 'zmdi zmdi-edit', '1', '37', '37');
INSERT INTO `permission` VALUES ('38', '1', '39', '删除权限', '3', 'upms:permission:delete', '/manage/permission/delete', 'zmdi zmdi-close', '1', '38', '38');
INSERT INTO `permission` VALUES ('39', '1', '7', '权限管理', '2', 'upms:permission:read', '/manage/permission/index', null, '1', '39', '39');
INSERT INTO `permission` VALUES ('46', '1', '5', '角色权限', '3', 'upms:role:permission', '/manage/role/permission', 'zmdi zmdi-key', '1', '1488091928257', '1488091928257');
INSERT INTO `permission` VALUES ('48', '1', '6', '用户组织', '3', 'upms:user:organization', '/manage/user/organization', 'zmdi zmdi-accounts-list', '1', '1488120011165', '1488120011165');
INSERT INTO `permission` VALUES ('50', '1', '6', '用户角色', '3', 'upms:user:role', '/manage/user/role', 'zmdi zmdi-accounts', '1', '1488120554175', '1488120554175');
INSERT INTO `permission` VALUES ('51', '1', '6', '用户权限', '3', 'upms:user:permission', '/manage/user/permission', 'zmdi zmdi-key', '1', '1488092013302', '1488092013302');
INSERT INTO `permission` VALUES ('53', '1', '14', '强制退出', '3', 'upms:session:forceout', '/manage/session/forceout', 'zmdi zmdi-run', '1', '1488379514715', '1488379514715');
INSERT INTO `permission` VALUES ('54', '2', '18', '新增标签', '3', 'cms:tag:create', '/manage/tag/create', 'zmdi zmdi-plus', '1', '1489417315159', '1489417315159');
INSERT INTO `permission` VALUES ('55', '2', '18', '编辑标签', '3', 'cms:tag:update', 'zmdi zmdi-edit', 'zmdi zmdi-widgets', '1', '1489417344931', '1489417344931');
INSERT INTO `permission` VALUES ('56', '2', '18', '删除标签', '3', 'cms:tag:delete', '/manage/tag/delete', 'zmdi zmdi-close', '1', '1489417372114', '1489417372114');
INSERT INTO `permission` VALUES ('57', '1', '15', '删除权限', '3', 'upms:log:delete', '/manage/log/delete', 'zmdi zmdi-close', '1', '1489503867909', '1489503867909');
INSERT INTO `permission` VALUES ('58', '2', '19', '编辑类目', '3', 'cms:category:update', '/manage/category/update', 'zmdi zmdi-edit', '1', '1489586600462', '1489586600462');
INSERT INTO `permission` VALUES ('59', '2', '19', '删除类目', '3', 'cms:category:delete', '/manage/category/delete', 'zmdi zmdi-close', '1', '1489586633059', '1489586633059');
INSERT INTO `permission` VALUES ('60', '2', '19', '新增类目', '3', 'cms:category:create', '/manage/category/create', 'zmdi zmdi-plus', '1', '1489590342089', '1489590342089');
INSERT INTO `permission` VALUES ('61', '2', '0', '其他数据管理', '1', '', '', 'zmdi zmdi-more', '1', '1489835455359', '1489835455359');
INSERT INTO `permission` VALUES ('62', '2', '20', '评论管理', '2', 'cms:comment:read', '/manage/comment/index', '', '1', '1489591408224', '1489591408224');
INSERT INTO `permission` VALUES ('63', '2', '62', '删除评论', '3', 'cms:comment:delete', '/manage/comment/delete', 'zmdi zmdi-close', '1', '1489591449614', '1489591449614');
INSERT INTO `permission` VALUES ('64', '2', '79', '单页管理', '2', 'cms:page:read', '/manage/page/index', '', '1', '1489591332779', '1489591332779');
INSERT INTO `permission` VALUES ('65', '2', '64', '新增单页', '3', 'cms:page:create', '/manage/page/create', 'zmdi zmdi-plus', '1', '1489591614473', '1489591614473');
INSERT INTO `permission` VALUES ('66', '2', '64', '编辑单页', '3', 'cms:page:update', '/manage/page/update', 'zmdi zmdi-edit', '1', '1489591653000', '1489591653000');
INSERT INTO `permission` VALUES ('67', '2', '64', '删除单页', '3', 'cms:page:delete', '/manage/page/delete', 'zmdi zmdi-close', '1', '1489591683552', '1489591683552');
INSERT INTO `permission` VALUES ('68', '2', '61', '菜单管理', '2', 'cms:menu:read', '/manage/menu/index', 'zmdi zmdi-widgets', '1', '1489591746846', '1489591746846');
INSERT INTO `permission` VALUES ('69', '2', '68', '新增菜单', '3', 'cms:menu:create', '/manage/menu/create', 'zmdi zmdi-plus', '1', '1489591791747', '1489591791747');
INSERT INTO `permission` VALUES ('70', '2', '68', '编辑菜单', '3', 'cms:menu:update', '/manage/menu/update', 'zmdi zmdi-edit', '1', '1489591831878', '1489591831878');
INSERT INTO `permission` VALUES ('71', '2', '68', '删除菜单', '3', 'cms:menu:delete', '/manage/menu/delete', 'zmdi zmdi-close', '1', '1489591865454', '1489591865454');
INSERT INTO `permission` VALUES ('72', '2', '61', '系统设置', '2', 'cms:setting:read', '/manage/setting/index', 'zmdi zmdi-widgets', '1', '1489591981165', '1489591981165');
INSERT INTO `permission` VALUES ('73', '2', '72', '新增设置', '3', 'cms:setting:create', '/manage/setting/create', 'zmdi zmdi-plus', '1', '1489592024762', '1489592024762');
INSERT INTO `permission` VALUES ('74', '2', '72', '编辑设置', '3', 'cms:setting:update', '/manage/setting/update', 'zmdi zmdi-edit', '1', '1489592052582', '1489592052582');
INSERT INTO `permission` VALUES ('75', '2', '72', '删除设置', '3', 'cms:setting:delete', '/manage/setting/delete', 'zmdi zmdi-close', '1', '1489592081426', '1489592081426');
INSERT INTO `permission` VALUES ('76', '2', '21', '新增文章', '3', 'cms:article:create', '/manage/article/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `permission` VALUES ('77', '2', '21', '编辑文章', '3', 'cms:article:update', '/manage/article/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `permission` VALUES ('78', '2', '21', '删除文章', '3', 'cms:article:delete', '/manage/article/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');
INSERT INTO `permission` VALUES ('79', '2', '0', '单页专题管理', '1', '', '', 'zmdi zmdi-view-web', '1', '1489835320327', '1489835320327');
INSERT INTO `permission` VALUES ('80', '2', '79', '专题管理', '2', 'cms:topic:read', '/manage/topic/index', 'zmdi zmdi-widgets', '1', '1489591507566', '1489591507566');
INSERT INTO `permission` VALUES ('81', '2', '80', '新增专题', '3', 'cms:topic:create', '/manage/topic/create', 'zmdi zmdi-plus', '1', '1489843327028', '1489843327028');
INSERT INTO `permission` VALUES ('82', '2', '80', '编辑专题', '3', 'cms:topic:update', '/manage/topic/update', 'zmdi zmdi-edit', '1', '1489843351513', '1489843351513');
INSERT INTO `permission` VALUES ('83', '2', '80', '删除专题', '3', 'cms:topic:delete', '/manage/topic/delete', 'zmdi zmdi-close', '1', '1489843379953', '1489843379953');
INSERT INTO `permission` VALUES ('84', '2', '68', '上移菜单', '3', 'cms:menu:up', '/manage/menu/up', 'zmdi zmdi-long-arrow-up', '1', '1489846486548', '1489846486548');
INSERT INTO `permission` VALUES ('85', '2', '68', '下移菜单', '3', 'cms:menu:down', '/manage/menu/down', 'zmdi zmdi-long-arrow-down', '1', '1489846578051', '1489846578051');
INSERT INTO `permission` VALUES ('86', '2', '0', '书籍内容管理', '1', '', '', 'zmdi zmdi-widgets', '1', '1503988973237', '1503988973237');
INSERT INTO `permission` VALUES ('87', '2', '86', '书籍管理', '2', 'cms:book:read', '/manage/book/index', 'zmdi zmdi-widgets', '1', '1503989070127', '1503989070127');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `title` varchar(20) DEFAULT NULL COMMENT '角色标题',
  `description` varchar(1000) DEFAULT NULL COMMENT '角色描述',
  `ctime` bigint(20) NOT NULL COMMENT '创建时间',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'super', '超级管理员', 'sdfwefwefwe', '1', '1');
INSERT INTO `role` VALUES ('2', 'admin', '管理员', '拥有除权限管理系统外的所有权限', '1487471013117', '1487471013117');
INSERT INTO `role` VALUES ('11', 'test', '测试人员', '拥有所有权限', '1506051191477', null);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色编号',
  `permission_id` int(10) unsigned NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`role_permission_id`),
  KEY `FK_Reference_23` (`role_id`),
  CONSTRAINT `FK_Reference_23` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('2', '1', '2');
INSERT INTO `role_permission` VALUES ('3', '1', '3');
INSERT INTO `role_permission` VALUES ('4', '1', '4');
INSERT INTO `role_permission` VALUES ('5', '1', '5');
INSERT INTO `role_permission` VALUES ('6', '1', '6');
INSERT INTO `role_permission` VALUES ('7', '1', '7');
INSERT INTO `role_permission` VALUES ('8', '1', '39');
INSERT INTO `role_permission` VALUES ('12', '1', '12');
INSERT INTO `role_permission` VALUES ('14', '1', '14');
INSERT INTO `role_permission` VALUES ('15', '1', '15');
INSERT INTO `role_permission` VALUES ('17', '1', '17');
INSERT INTO `role_permission` VALUES ('19', '1', '19');
INSERT INTO `role_permission` VALUES ('20', '1', '20');
INSERT INTO `role_permission` VALUES ('21', '1', '21');
INSERT INTO `role_permission` VALUES ('24', '1', '24');
INSERT INTO `role_permission` VALUES ('27', '1', '27');
INSERT INTO `role_permission` VALUES ('28', '1', '28');
INSERT INTO `role_permission` VALUES ('29', '1', '29');
INSERT INTO `role_permission` VALUES ('30', '1', '30');
INSERT INTO `role_permission` VALUES ('31', '1', '31');
INSERT INTO `role_permission` VALUES ('32', '1', '32');
INSERT INTO `role_permission` VALUES ('33', '1', '33');
INSERT INTO `role_permission` VALUES ('34', '1', '34');
INSERT INTO `role_permission` VALUES ('35', '1', '35');
INSERT INTO `role_permission` VALUES ('36', '1', '36');
INSERT INTO `role_permission` VALUES ('37', '1', '37');
INSERT INTO `role_permission` VALUES ('38', '1', '38');
INSERT INTO `role_permission` VALUES ('39', '1', '46');
INSERT INTO `role_permission` VALUES ('40', '1', '51');
INSERT INTO `role_permission` VALUES ('44', '1', '48');
INSERT INTO `role_permission` VALUES ('45', '1', '50');
INSERT INTO `role_permission` VALUES ('47', '1', '53');
INSERT INTO `role_permission` VALUES ('48', '1', '18');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) NOT NULL COMMENT '帐号',
  `password` varchar(32) NOT NULL COMMENT '密码MD5(密码+盐)',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `realname` varchar(20) DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(150) DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `locked` tinyint(4) DEFAULT '0' COMMENT '状态(0:正常,1:锁定)',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '3038D9CB63B3152A79B8153FB06C02F7', '66f1b370c660445a8657bf8bf1794486', '系统管理原', '/resources/ancient-admin/images/avatar.jpg', '', '469741414@qq.com', '1', '0', '1');
INSERT INTO `user` VALUES ('2', 'xjp', '285C9762F5F9046F5893F752DFAF3476', 'd2d0d03310444ad388a8b290b0fe8564', 'xjp', '/resources/ancient-admin/images/avatar.jpg', '', '469741414@qq.com', '1', '0', '1493394720495');
INSERT INTO `user` VALUES ('7', 'test', 'bebdf02c257b3a4be0024196f36b3424', '36d27187f1d206c28472adf6b1265817', 'test', null, '111', '1@1.com', '1', '0', '1516183097570');
INSERT INTO `user` VALUES ('10', 'liu', 'be320beca57748ab9632c4121ccac0db', '0072273a5d87322163795118fdd7c45e', '', null, '', '', null, '0', '1516187068679');
INSERT INTO `user` VALUES ('11', 'wang', 'a43c86305a332bf5e5d0b605c07d71c5', '708b3cf46c2f0d20341832e5b9c8d4d2', '', null, '', '', null, '0', '1516192417073');

-- ----------------------------
-- Table structure for user_permission
-- ----------------------------
DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission` (
  `user_permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `permission_id` int(10) unsigned NOT NULL COMMENT '权限编号',
  `type` tinyint(4) NOT NULL COMMENT '权限类型(-1:减权限,1:增权限)',
  PRIMARY KEY (`user_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权限关联表';

-- ----------------------------
-- Records of user_permission
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_role_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `role_id` int(10) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('4', '1', '1');
INSERT INTO `user_role` VALUES ('5', '1', '2');
INSERT INTO `user_role` VALUES ('6', '2', '1');
INSERT INTO `user_role` VALUES ('7', '2', '2');
