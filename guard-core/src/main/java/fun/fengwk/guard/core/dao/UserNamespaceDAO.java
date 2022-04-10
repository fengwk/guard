package fun.fengwk.guard.core.dao;

import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.guard.core.model.UserNamespaceDO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 用户命名空间。
 *
 * @author fengwk
 */
public interface UserNamespaceDAO {

    /**
     * 新增用户命名空间。
     *
     * @param userNamespaceDO
     */
    void insert(@NotNull UserNamespaceDO userNamespaceDO);

    /**
     * 通过命名空间删除。
     *
     * @param userNamespace
     * @return
     */
    boolean deleteByUserNamespace(@NotEmpty String userNamespace);

    /**
     * 通过命名空间更新。
     *
     * @param userNamespaceDO
     * @return
     */
    boolean updateByUserNamespace(@NotNull UserNamespaceDO userNamespaceDO);

    /**
     * 通过命名空间查找。
     *
     * @param userNamespace
     * @return
     */
    UserNamespaceDO findByUserNamespace(@NotEmpty String userNamespace);

    /**
     * 分页查询用户命名空间。
     *
     * @param pageQuery
     * @param namePrefix
     * @return
     */
    Page<UserNamespaceDO> page(@NotNull PageQuery pageQuery, String namePrefix);

}
