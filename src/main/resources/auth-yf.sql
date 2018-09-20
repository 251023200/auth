-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `version` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(20) NOT NULL,
  `user_no` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `username` varchar(128) NOT NULL COMMENT '用户系统名',
  `password` varchar(128) DEFAULT NULL COMMENT '用户系统密码',
  `name` varchar(128) DEFAULT NULL COMMENT '用户姓名',
  `salt` varchar(128) DEFAULT NULL COMMENT '盐',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `telephone` varchar(16) DEFAULT NULL COMMENT '电话',
  `mobile_telephone` varchar(16) DEFAULT NULL COMMENT '手机',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(32) NOT NULL,
  `version` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(20) NOT NULL,
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` varchar(32) NOT NULL,
  `version` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(20) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` varchar(32) NOT NULL,
  `version` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(20) NOT NULL,
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组别表';

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(32) NOT NULL,
  `version` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(20) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `icon` varchar(32) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `url` varchar(128) DEFAULT NULL COMMENT '菜单定位符',
  `seq` int(4) DEFAULT NULL COMMENT '名称',
  `level` int(2) DEFAULT NULL COMMENT '层级',
  `active` int(4) NOT NULL,
  `is_leaf` varchar(128) DEFAULT NULL COMMENT '菜单定位符',
  `remark` varchar(128) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` varchar(32) NOT NULL,
  `version` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(20) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `type` varchar(8) DEFAULT NULL COMMENT '资源类型',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `uri` varchar(128) DEFAULT NULL COMMENT '资源定位符',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation` (
  `id` varchar(32) NOT NULL,
  `version` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(20) NOT NULL,
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `method` varchar(32) DEFAULT NULL COMMENT '名称',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作表';


-- ----------------------------
-- Table structure for permisson
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` varchar(32) NOT NULL,
  `version` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(20) NOT NULL,
  `resource_id` varchar(32) DEFAULT NULL COMMENT '资源编码',
  `operation_id` varchar(32) DEFAULT NULL COMMENT '操作编码',
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='许可表';


-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(32) NOT NULL,
  `version` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(20) NOT NULL,
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户编号',
  `role_id` varchar(128) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色表';


-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `user_dept`;
CREATE TABLE `user_dept` (
  `id` varchar(32) NOT NULL,
  `version` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(20) NOT NULL,
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户编号',
  `dept_id` varchar(128) DEFAULT NULL COMMENT '部门编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-部门表';

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` varchar(32) NOT NULL,
  `version` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(20) NOT NULL,
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户编号',
  `group_id` varchar(128) DEFAULT NULL COMMENT '部门编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-部门表';


-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` varchar(32) NOT NULL,
  `version` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(20) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单表';

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` varchar(32) NOT NULL,
  `version` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(20) NOT NULL,
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色编号',
  `permission_id` varchar(128) DEFAULT NULL COMMENT '许可编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-许可表';