package fun.fengwk.guard.sus.core.dao;

import fun.fengwk.convention4j.common.page.LitePage;
import fun.fengwk.convention4j.common.page.LitePageQuery;
import fun.fengwk.convention4j.common.page.Page;
import fun.fengwk.convention4j.common.page.PageQuery;
import fun.fengwk.convention4j.common.page.Pages;
import fun.fengwk.guard.sus.core.mapper.UserMapper;
import fun.fengwk.guard.sus.core.model.UserDO;
import fun.fengwk.guard.sus.share.model.UserQuery;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author fengwk
 */
@Component
public class UserDAOImpl implements UserDAO {

    private final UserMapper userMapper;

    public UserDAOImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void createIfNotExists(String namespace) {
        userMapper.createIfNotExists(namespace);
    }

    @Override
    public void insert(String namespace, UserDO userDO) {
        userMapper.insert(namespace, userDO);
    }

    @Override
    public boolean updateByIdWithVersionIfNecessary(String namespace, UserDO userDO) {
        return userMapper.updateByUserIdWithVersionIfNecessary(namespace, userDO) > 0;
    }

    @Override
    public boolean updateByIdSelectiveWithVersionIfNecessary(String namespace, UserDO userDO) {
        return userMapper.updateByUserIdSelectiveWithVersionIfNecessary(namespace, userDO) > 0;
    }

    @Override
    public boolean deleteByUserIdAndVersion(String namespace, long id, Long version) {
        return userMapper.deleteByUserIdAndVersion(namespace, id, version) > 0;
    }

    @Override
    public UserDO findByUserIdAndVersion(String namespace, long id, Long version) {
        return userMapper.findByUserIdAndVersion(namespace, id, version);
    }

    @Override
    public UserDO findByUsername(String namespace, String username) {
        return userMapper.findByUsername(namespace, username);
    }

    @Override
    public UserDO findByEmail(String namespace, String email) {
        return userMapper.findByEmail(namespace, email);
    }

    @Override
    public UserDO findByMobile(String namespace, String mobile) {
        return userMapper.findByMobile(namespace, mobile);
    }

    @Override
    public Page<UserDO> page(String namespace, PageQuery pageQuery, UserQuery userQuery) {
        List<UserDO> pageList = userMapper.page(namespace, pageQuery.getOffset(), pageQuery.getLimit(), userQuery);
        long totalCount = userMapper.count(namespace, userQuery);
        return Pages.page(pageQuery, pageList, totalCount);
    }

    @Override
    public LitePage<UserDO> litePage(String namespace, LitePageQuery litePageQuery, UserQuery userQuery) {
        List<UserDO> pageList = userMapper.page(namespace, litePageQuery.getOffset(), litePageQuery.getLimit(), userQuery);
        return Pages.litePage(litePageQuery, pageList);
    }

}
