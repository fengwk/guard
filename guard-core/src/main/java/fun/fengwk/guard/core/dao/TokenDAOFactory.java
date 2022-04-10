package fun.fengwk.guard.core.dao;

import fun.fengwk.guard.core.mapper.TokenMapper;
import org.springframework.stereotype.Component;

/**
 * @author fengwk
 */
@Component
public class TokenDAOFactory extends BaseNamespaceCapabilityDAOFactory<TokenDAO> {

    private final TokenMapper tokenMapper;

    public TokenDAOFactory(TokenMapper tokenMapper) {
        this.tokenMapper = tokenMapper;
    }

    @Override
    protected TokenDAO newDAO(String namespace) {
        return new TokenDAOImpl(namespace, tokenMapper);
    }

}
