package fun.fengwk.guard.core.dao;

import fun.fengwk.guard.core.mapper.PermissionMapper;
import org.springframework.stereotype.Component;

/**
 * @author fengwk
 */
@Component
public class PermissionDAOFactory extends BaseNamespaceCapabilityDAOFactory<PermissionDAO> {

    private final PermissionMapper permissionMapper;

    public PermissionDAOFactory(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    protected PermissionDAO newDAO(String namespace) {
        return new PermissionDAOImpl(namespace, permissionMapper);
    }

}
