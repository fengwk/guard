package fun.fengwk.guard.core.dao;

import fun.fengwk.guard.core.mapper.RolePermissionMapper;
import fun.fengwk.guard.core.model.RolePermissionDO;

import java.util.Collection;
import java.util.List;

/**
 * @author fengwk
 */
public class RolePermissionDAOImpl extends BaseNamespaceCapabilityDAO implements RolePermissionDAO {

    private final RolePermissionMapper rolePermissionMapper;

    public RolePermissionDAOImpl(String namespace, RolePermissionMapper rolePermissionMapper) {
        super(namespace);
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public void createIfNotExists() {
        rolePermissionMapper.createIfNotExists(getNamespace());
    }

    @Override
    public void dropIfExists() {
        rolePermissionMapper.dropIfExists(getNamespace());
    }

    @Override
    public int insertAll(Collection<RolePermissionDO> rolePermissionDOs) {
        return rolePermissionMapper.insertAll(getNamespace(), rolePermissionDOs);
    }

    @Override
    public int deleteByIds(Collection<Long> ids) {
        return rolePermissionMapper.deleteByIds(getNamespace(), ids);
    }

    @Override
    public List<RolePermissionDO> limitByRoleId(long limit, long roleId) {
        return rolePermissionMapper.limitByRoleId(getNamespace(), limit, roleId);
    }

    @Override
    public List<RolePermissionDO> limitByPermissionId(long limit, long permissionId) {
        return rolePermissionMapper.limitByPermissionId(getNamespace(), limit, permissionId);
    }

}
