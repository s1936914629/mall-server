-- 注意：管理员的测试数据中，插入的密码值应该是密文值
-- 以下插入的管理员数据的密码原文均是：123456

-- 管理员表：插入测试数据
insert into ams_admin (username, password, nickname, email, description, is_enable)
values ('root', '$2a$10$iSirF34uKfvbnlbX5A.7LusdYcSgJTuW0O4wxVba6uf4zlcb.84g6', 'root', 'root@tedu.cn', '最高管理员', 1),
       ('super_admin', '$2a$10$iSirF34uKfvbnlbX5A.7LusdYcSgJTuW0O4wxVba6uf4zlcb.84g6', 'administrator', 'admin@tedu.cn',
        '超级管理员', 1),
       ('nobody', '$2a$10$iSirF34uKfvbnlbX5A.7LusdYcSgJTuW0O4wxVba6uf4zlcb.84g6', '无名', 'liucs@tedu.cn', null, 0);

-- 权限表：插入测试数据
insert into ams_permission (name, value, description)
values ('商品-商品管理-读取', '/pms/product/read', '读取商品数据，含列表、详情、查询等'),
       ('商品-商品管理-编辑', '/pms/product/update', '修改商品数据'),
       ('商品-商品管理-删除', '/pms/product/delete', '删除商品数据'),
       ('后台管理-管理员-读取', '/ams/admin/read', '读取管理员数据，含列表、详情、查询等'),
       ('后台管理-管理员-编辑', '/ams/admin/update', '编辑管理员数据'),
       ('后台管理-管理员-删除', '/ams/admin/delete', '删除管理员数据');

-- 角色表：插入测试数据
insert into ams_role (name)
values ('超级管理员'),
       ('系统管理员'),
       ('商品管理员'),
       ('订单管理员');

-- 角色权限关联表：插入测试数据
insert into ams_role_permission (role_id, permission_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (3, 1),
       (3, 2),
       (3, 3);

-- 管理员角色关联表：插入测试数据
insert into ams_admin_role (admin_id, role_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (2, 3),
       (2, 4),
       (3, 3);
