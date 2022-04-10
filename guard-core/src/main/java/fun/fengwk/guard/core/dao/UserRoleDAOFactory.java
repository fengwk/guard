package fun.fengwk.guard.core.dao;

import fun.fengwk.guard.core.mapper.UserRoleMapper;
import org.springframework.stereotype.Component;

/**
 * @author fengwk
 */
@Component
public class UserRoleDAOFactory extends BaseNamespaceCapabilityDAOFactory<UserRoleDAO> {

    private final UserRoleMapper userRoleMapper;

    public UserRoleDAOFactory(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    protected UserRoleDAO newDAO(String namespace) {
        return new UserRoleDAOImpl(namespace, userRoleMapper);
    }

}
