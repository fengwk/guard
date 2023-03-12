package fun.fengwk.guard.core.dao;

import fun.fengwk.convention4j.common.page.Page;
import fun.fengwk.convention4j.common.page.PageQuery;
import fun.fengwk.guard.core.model.PermissionDO;

import javax.validation.constraints.NotNull;

/**
 * 权限。
 *
 * @author fengwk
 */
public interface PermissionDAO extends NamespaceCapabilityDAO {

    /**
     * 尝试创建指定命名空间的权限表。
     */
    void createIfNotExists();

    /**
     * 尝试删除指定命名空间的权限表。
     */
    void dropIfExists();

    /**
     * 新增权限。
     *
     * @param permissionDO
     */
    void insert(@NotNull PermissionDO permissionDO);

    /**
     * 通过权限id删除权限。
     *
     * @param permissionId
     * @return
     */
    boolean deleteByPermissionId(long permissionId);

    /**
     * 通过权限id更新权限。
     *
     * @param permissionDO
     * @return
     */
    boolean updateByPermissionId(@NotNull PermissionDO permissionDO);

    /**
     * 通过权限id查找权限。
     *
     * @param permissionId
     * @return
     */
    PermissionDO findByPermissionId(long permissionId);

    /**
     * 分页查询权限。
     *
     * @param pageQuery
     * @param permissionPrefix
     * @return
     */
    Page<PermissionDO> page(@NotNull PageQuery pageQuery, String permissionPrefix);

}
