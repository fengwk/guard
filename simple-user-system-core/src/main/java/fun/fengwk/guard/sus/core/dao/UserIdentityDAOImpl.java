package fun.fengwk.guard.sus.core.dao;

import com.google.common.base.Preconditions;
import fun.fengwk.guard.sus.core.mapper.UserIdentityMapper;
import fun.fengwk.guard.sus.core.model.UserIdentityBO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author fengwk
 */
@Component
public class UserIdentityDAOImpl implements UserIdentityDAO {

    private final UserIdentityMapper userIdentityMapper;

    public UserIdentityDAOImpl(UserIdentityMapper userIdentityMapper) {
        this.userIdentityMapper = userIdentityMapper;
    }

    @Override
    public UserIdentityBO getById(String namespace, long id) {
        Preconditions.checkArgument(!StringUtils.isEmpty(namespace), "namespace cannot be empty");

        return userIdentityMapper.findByUserId(namespace, id);
    }

    @Override
    public UserIdentityBO getByUsername(String namespace, String username) {
        Preconditions.checkArgument(!StringUtils.isEmpty(namespace), "namespace cannot be empty");

        return userIdentityMapper.findByUsername(namespace, username);
    }

    @Override
    public UserIdentityBO getByEmail(String namespace, String email) {
        Preconditions.checkArgument(!StringUtils.isEmpty(namespace), "namespace cannot be empty");

        return userIdentityMapper.findByEmail(namespace, email);
    }

    @Override
    public UserIdentityBO getByMobile(String namespace, String mobile) {
        Preconditions.checkArgument(!StringUtils.isEmpty(namespace), "namespace cannot be empty");

        return userIdentityMapper.findByMobile(namespace, mobile);
    }

}
