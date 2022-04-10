package fun.fengwk.guard.core.dao;

import fun.fengwk.guard.core.mapper.UserRoleMapper;
import fun.fengwk.guard.core.model.UserRoleDO;

import java.util.Collection;
import java.util.List;

/**
 * @author fengwk
 */
public class UserRoleDAOImpl extends BaseNamespaceCapabilityDAO implements UserRoleDAO {

    private final UserRoleMapper userRoleMapper;

    public UserRoleDAOImpl(String namespace, UserRoleMapper userRoleMapper) {
        super(namespace);
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public void createIfNotExists() {
        userRoleMapper.createIfNotExists(getNamespace());
    }

    @Override
    public void dropIfExists() {
        userRoleMapper.dropIfExists(getNamespace());
    }

    @Override
    public int insertAll(Collection<UserRoleDO> userRoleDOs) {
        return userRoleMapper.insertAll(getNamespace(), userRoleDOs);
    }

    @Override
    public int deleteByIds(Collection<Long> ids) {
        return userRoleMapper.deleteByIds(getNamespace(), ids);
    }

    @Override
    public List<UserRoleDO> limitByUserId(long limit, long userId) {
        return userRoleMapper.limitByUserId(getNamespace(), limit, userId);
    }

    @Override
    public List<UserRoleDO> limitByRoleId(long limit, long roleId) {
        return userRoleMapper.limitByRoleId(getNamespace(), limit, roleId);
    }

}
