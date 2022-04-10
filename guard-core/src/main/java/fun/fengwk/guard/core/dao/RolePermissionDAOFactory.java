package fun.fengwk.guard.core.dao;

import fun.fengwk.guard.core.mapper.RolePermissionMapper;
import org.springframework.stereotype.Component;

/**
 * @author fengwk
 */
@Component
public class RolePermissionDAOFactory extends BaseNamespaceCapabilityDAOFactory<RolePermissionDAO> {

    private final RolePermissionMapper rolePermissionMapper;

    public RolePermissionDAOFactory(RolePermissionMapper rolePermissionMapper) {
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    protected RolePermissionDAO newDAO(String namespace) {
        return new RolePermissionDAOImpl(namespace, rolePermissionMapper);
    }

}
