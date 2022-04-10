package fun.fengwk.guard.core.dao;

import fun.fengwk.guard.core.mapper.TokenMapper;
import fun.fengwk.guard.core.model.TokenDO;

/**
 * @author fengwk
 */
public class TokenDAOImpl extends BaseNamespaceCapabilityDAO implements TokenDAO {

    private final TokenMapper tokenMapper;

    public TokenDAOImpl(String namespace, TokenMapper tokenMapper) {
        super(namespace);
        this.tokenMapper = tokenMapper;
    }

    @Override
    public void createIfNotExists() {
        tokenMapper.createIfNotExists(getNamespace());
    }

    @Override
    public void dropIfExists() {
        tokenMapper.dropIfExists(getNamespace());
    }

    @Override
    public void insert(TokenDO tokenDO) {
        tokenMapper.insert(getNamespace(), tokenDO);
    }

    @Override
    public boolean updateRevoked(String accessToken) {
        return tokenMapper.updateRevoked(getNamespace(), accessToken) > 0;
    }

    @Override
    public TokenDO findByAccessToken(String accessToken) {
        return tokenMapper.findByAccessToken(getNamespace(), accessToken);
    }

    @Override
    public TokenDO findLastByRefreshToken(String refreshToken) {
        return tokenMapper.findLastByRefreshToken(getNamespace(), refreshToken);
    }

}
