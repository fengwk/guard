package fun.fengwk.guard.sus.core.dao;

import com.google.common.base.Preconditions;
import fun.fengwk.guard.sus.core.mapper.UserPropertiesMapper;
import fun.fengwk.guard.sus.core.model.UserPropertiesBO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author fengwk
 */
@Component
public class UserPropertiesDAOImpl implements UserPropertiesDAO {

    private final UserPropertiesMapper userPropertiesMapper;

    public UserPropertiesDAOImpl(UserPropertiesMapper userPropertiesMapper) {
        this.userPropertiesMapper = userPropertiesMapper;
    }

    @Override
    public UserPropertiesBO getById(String namespace, long id) {
        Preconditions.checkArgument(!StringUtils.isEmpty(namespace), "namespace cannot be empty");

        return userPropertiesMapper.findByUserId(namespace, id);
    }

}
