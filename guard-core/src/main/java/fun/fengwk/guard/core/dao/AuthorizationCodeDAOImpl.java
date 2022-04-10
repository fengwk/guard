package fun.fengwk.guard.core.dao;

import fun.fengwk.guard.core.mapper.AuthorizationCodeMapper;
import fun.fengwk.guard.core.model.AuthorizationCodeDO;

/**
 * @author fengwk
 */
public class AuthorizationCodeDAOImpl extends BaseNamespaceCapabilityDAO implements AuthorizationCodeDAO {

    private final AuthorizationCodeMapper authorizationCodeMapper;

    public AuthorizationCodeDAOImpl(String namespace, AuthorizationCodeMapper authorizationCodeMapper) {
        super(namespace);
        this.authorizationCodeMapper = authorizationCodeMapper;
    }

    @Override
    public void createIfNotExists() {
        authorizationCodeMapper.createIfNotExists(getNamespace());
    }

    @Override
    public void dropIfExists() {
        authorizationCodeMapper.dropIfExists(getNamespace());
    }

    @Override
    public void insert(AuthorizationCodeDO authorizationCodeDO) {
        authorizationCodeMapper.insert(getNamespace(), authorizationCodeDO);
    }

    @Override
    public boolean updateUsed(String code) {
        return authorizationCodeMapper.updateUsed(getNamespace(), code) > 0;
    }

    @Override
    public AuthorizationCodeDO findByCode(String code) {
        return authorizationCodeMapper.findByCode(getNamespace(), code);
    }

}
