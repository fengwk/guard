package fun.fengwk.guard.core.mapper;

import fun.fengwk.guard.core.model.UserRoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author fengwk
 */
public interface UserRoleMapper {

    void createIfNotExists(String namespace);

    void dropIfExists(String namespace);

    int insertAll(@Param("namespace") String namespace, @Param("userRoleDOs") Collection<UserRoleDO> userRoleDOs);

    int deleteByIds(@Param("namespace") String namespace, @Param("ids") Collection<Long> ids);

    List<UserRoleDO> limitByUserId(@Param("namespace") String namespace, @Param("limit") long limit, @Param("userId") long userId);

    List<UserRoleDO> limitByRoleId(@Param("namespace") String namespace, @Param("limit") long limit, @Param("roleId") long roleId);

}
