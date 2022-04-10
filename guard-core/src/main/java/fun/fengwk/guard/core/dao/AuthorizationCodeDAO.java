package fun.fengwk.guard.core.dao;

import fun.fengwk.guard.core.model.AuthorizationCodeDO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 授权码。
 *
 * @author fengwk
 */
public interface AuthorizationCodeDAO extends NamespaceCapabilityDAO {

    /**
     * 尝试创建指定命名空间的授权码表。
     */
    void createIfNotExists();

    /**
     * 尝试删除指定命名空间的授权码表。
     */
    void dropIfExists();

    /**
     * 新增授权码。
     *
     * @param authorizationCodeDO
     */
    void insert(@NotNull AuthorizationCodeDO authorizationCodeDO);

    /**
     * 将指定id的授权码更新为已使用，如果更新成功返回true，否则返回false。
     *
     * @param code
     * @return
     */
    boolean updateUsed(@NotEmpty String code);

    /**
     * 通过授权码值查找授权码。
     *
     * @param code
     * @return
     */
    AuthorizationCodeDO findByCode(@NotEmpty String code);

}
