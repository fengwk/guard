package fun.fengwk.guard.core.dao;

import fun.fengwk.guard.core.mapper.AuthorizationCodeMapper;
import org.springframework.stereotype.Component;

/**
 * @author fengwk
 */
@Component
public class AuthorizationCodeDAOFactory extends BaseNamespaceCapabilityDAOFactory<AuthorizationCodeDAO> {

    private final AuthorizationCodeMapper authorizationCodeMapper;

    public AuthorizationCodeDAOFactory(AuthorizationCodeMapper authorizationCodeMapper) {
        this.authorizationCodeMapper = authorizationCodeMapper;
    }

    @Override
    protected AuthorizationCodeDAO newDAO(String namespace) {
        return new AuthorizationCodeDAOImpl(namespace, authorizationCodeMapper);
    }

}
