package fun.fengwk.guard.core.dao;

import fun.fengwk.guard.core.mapper.RoleMapper;
import org.springframework.stereotype.Component;

/**
 * @author fengwk
 */
@Component
public class RoleDAOFactory extends BaseNamespaceCapabilityDAOFactory<RoleDAO> {

    private final RoleMapper roleMapper;

    public RoleDAOFactory(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    protected RoleDAO newDAO(String namespace) {
        return new RoleDAOImpl(namespace, roleMapper);
    }

}
