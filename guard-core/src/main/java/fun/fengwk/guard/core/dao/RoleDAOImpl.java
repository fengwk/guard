package fun.fengwk.guard.core.dao;

import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.convention4j.api.page.Pages;
import fun.fengwk.guard.core.mapper.RoleMapper;
import fun.fengwk.guard.core.model.RoleDO;

import java.util.List;

/**
 * @author fengwk
 */
public class RoleDAOImpl extends BaseNamespaceCapabilityDAO implements RoleDAO {

    private final RoleMapper roleMapper;

    public RoleDAOImpl(String namespace, RoleMapper roleMapper) {
        super(namespace);
        this.roleMapper = roleMapper;
    }

    @Override
    public void createIfNotExists() {
        roleMapper.createIfNotExists(getNamespace());
    }

    @Override
    public void dropIfExists() {
        roleMapper.dropIfExists(getNamespace());
    }

    @Override
    public void insert(RoleDO roleDO) {
        roleMapper.insert(getNamespace(), roleDO);
    }

    @Override
    public boolean deleteByRoleId(long roleId) {
        return roleMapper.deleteByRoleId(getNamespace(), roleId) > 0;
    }

    @Override
    public boolean updateByRoleId(RoleDO roleDO) {
        return roleMapper.updateByRoleId(getNamespace(), roleDO) > 0;
    }

    @Override
    public RoleDO findByRoleId(long roleId) {
        return roleMapper.findByRoleId(getNamespace(), roleId);
    }

    @Override
    public Page<RoleDO> page(PageQuery pageQuery, String namePrefix) {
        List<RoleDO> results = roleMapper.page(getNamespace(), pageQuery.getOffset(), pageQuery.getLimit(), namePrefix);
        long totalCount = roleMapper.count(getNamespace(), namePrefix);
        return Pages.page(pageQuery, results, totalCount);
    }

}
