package fun.fengwk.guard.core.dao;

import fun.fengwk.guard.core.model.UserRoleDO;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;

/**
 * 用户角色。
 *
 * @author fengwk
 */
public interface UserRoleDAO extends NamespaceCapabilityDAO {

    /**
     * 尝试创建指定命名空间的用户角色关系表。
     */
    void createIfNotExists();

    /**
     * 尝试删除指定命名空间的用户角色关系表。
     */
    void dropIfExists();

    /**
     * 批量新增用户角色关系。
     *
     * @param userRoleDOs
     * @return
     */
    int insertAll(@NotEmpty Collection<UserRoleDO> userRoleDOs);

    /**
     * 批量删除用户角色关系。
     *
     * @param ids
     * @return
     */
    int deleteByIds(@NotEmpty Collection<Long> ids);

    /**
     * 通过用户id获取指定数个记录。
     *
     * @param limit
     * @param userId
     * @return
     */
    List<UserRoleDO> limitByUserId(long limit, long userId);

    /**
     * 通过角色id获取指定数个记录。
     *
     * @param limit
     * @param roleId
     * @return
     */
    List<UserRoleDO> limitByRoleId(long limit, long roleId);

}
