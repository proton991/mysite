INSERT INTO `sys_role` (rid, role) VALUES (1, 'admin');
INSERT INTO `sys_role` (rid, role) VALUES (2, 'user');
INSERT INTO `sys_role` (rid, role) VALUES (3, 'guest');

INSERT INTO `sys_permission` (pid, permission) VALUES (1, '/admin/addUser');
INSERT INTO `sys_permission` (pid, permission) VALUES (2, '/admin/deleteUser');
INSERT INTO `sys_permission` (pid, permission) VALUES (3, '/admin/deleteArticles');

INSERT INTO `user_info` (uid, password, state, username) VALUES (1, '123456',  1, 'admin');

INSERT INTO `sys_user_role` (uid, rid) VALUES (1, 1);

INSERT INTO `sys_role_permission` (rid, pid) VALUES (1, 1);
INSERT INTO `sys_role_permission` (rid, pid) VALUES (1, 2);
INSERT INTO `sys_role_permission` (rid, pid) VALUES (1, 3);