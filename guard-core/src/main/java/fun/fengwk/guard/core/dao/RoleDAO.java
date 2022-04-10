package fun.fengwk.guard.core.dao;

import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.guard.core.model.RoleDO;

import javax.validation.constraints.NotNull;

/**
 * 角色。
 *
 * @author fengwk
 */
public interface RoleDAO extends NamespaceCapabilityDAO {

    /**
     * 尝试创建指定命名空间的角色表。
     */
    void createIfNotExists();

    /**
     * 尝试删除指定命名空间的角色表。
     */
    void dropIfExists();

    /**
     * 新增角色。
     *
     * @param roleDO
     */
    void insert(@NotNull RoleDO roleDO);

    /**
     * 通过角色id删除角色。
     *
     * @param roleId
     * @return
     */
    boolean deleteByRoleId(long roleId);

    /**
     * 通过角色id更新角色。
     *
     * @param roleDO
     * @return
     */
    boolean updateByRoleId(@NotNull RoleDO roleDO);

    /**
     * 通过角色id查找角色。
     *
     * @param roleId
     * @return
     */
    RoleDO findByRoleId(long roleId);

    /**
     * 分页查询角色。
     *
     * @param pageQuery
     * @param namePrefix
     * @return
     */
    Page<RoleDO> page(@NotNull PageQuery pageQuery, String namePrefix);

}
