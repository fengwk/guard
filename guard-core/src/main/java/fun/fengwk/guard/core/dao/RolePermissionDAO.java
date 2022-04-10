package fun.fengwk.guard.core.dao;

import fun.fengwk.guard.core.model.RolePermissionDO;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;

/**
 * 角色权限。
 *
 * @author fengwk
 */
public interface RolePermissionDAO extends NamespaceCapabilityDAO {

    /**
     * 尝试创建指定命名空间的角色权限关系表。
     */
    void createIfNotExists();

    /**
     * 尝试删除指定命名空间的角色权限关系表。
     */
    void dropIfExists();

    /**
     * 批量新增角色权限关系。
     *
     * @param rolePermissionDOs
     * @return
     */
    int insertAll(@NotEmpty Collection<RolePermissionDO> rolePermissionDOs);

    /**
     * 批量删除角色权限关系。
     *
     * @param ids
     * @return
     */
    int deleteByIds(@NotEmpty Collection<Long> ids);

    /**
     * 通过角色id获取指定数个记录。
     *
     * @param limit
     * @param roleId
     * @return
     */
    List<RolePermissionDO> limitByRoleId(long limit, long roleId);

    /**
     * 通过权限id获取指定数个记录。
     *
     * @param limit
     * @param permissionId
     * @return
     */
    List<RolePermissionDO> limitByPermissionId(long limit, long permissionId);

}
