package fun.fengwk.guard.core.dao;

import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.convention4j.api.page.Pages;
import fun.fengwk.guard.core.mapper.UserNamespaceMapper;
import fun.fengwk.guard.core.model.UserNamespaceDO;

import java.util.List;

/**
 * @author fengwk
 */
public class UserNamespaceDAOImpl implements UserNamespaceDAO {

    private final UserNamespaceMapper userNamespaceMapper;

    public UserNamespaceDAOImpl(UserNamespaceMapper userNamespaceMapper) {
        this.userNamespaceMapper = userNamespaceMapper;
    }

    @Override
    public void insert(UserNamespaceDO userNamespaceDO) {
        userNamespaceMapper.insert(userNamespaceDO);
    }

    @Override
    public boolean deleteByUserNamespace(String userNamespace) {
        return userNamespaceMapper.deleteByUserNamespace(userNamespace) > 0;
    }

    @Override
    public boolean updateByUserNamespace(UserNamespaceDO userNamespaceDO) {
        return userNamespaceMapper.updateByUserNamespace(userNamespaceDO) > 0;
    }

    @Override
    public UserNamespaceDO findByUserNamespace(String userNamespace) {
        return userNamespaceMapper.findByUserNamespace(userNamespace);
    }

    @Override
    public Page<UserNamespaceDO> page(PageQuery pageQuery, String namePrefix) {
        List<UserNamespaceDO> results = userNamespaceMapper.page(pageQuery.getOffset(), pageQuery.getLimit(), namePrefix);
        long totalCount = userNamespaceMapper.count(namePrefix);
        return Pages.page(pageQuery, results, totalCount);
    }

}
