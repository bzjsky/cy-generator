-- 菜单SQL
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
-- 菜单SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES ('0', '商品管理', NULL, NULL, '0', NULL, '1');

-- 按钮父菜单ID
SET @parentId = @@identity;

INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    VALUES (@parentId, '${table.remarks}管理', 'modules/${namespace}/${classNameLower}.html', NULL, '1', 'fa fa-file-code-o', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '查看', null, '${classNameLower}:queryListPage,${classNameLower}:queryList,${classNameLower}:getById', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '新增', null, '${classNameLower}:save,${classNameLower}:saveBatch', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '修改', null, '${classNameLower}:modify', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '删除', null, '${classNameLower}:remove,${classNameLower}:removeBatch', '2', null, '6';
