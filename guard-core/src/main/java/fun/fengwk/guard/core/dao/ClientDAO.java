package fun.fengwk.guard.core.dao;

import fun.fengwk.convention4j.common.page.Page;
import fun.fengwk.convention4j.common.page.PageQuery;
import fun.fengwk.guard.core.model.ClientDO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 客户端。
 *
 * @author fengwk
 */
public interface ClientDAO {

    /**
     * 新增客户端。
     *
     * @param clientDO
     */
    void insert(@NotNull ClientDO clientDO);

    /**
     * 通过客户端id删除客户端。
     *
     * @param clientId
     * @return
     */
    boolean deleteByClientId(@NotEmpty String clientId);

    /**
     * 通过客户端id更新客户端。
     *
     * @param clientDO
     * @return
     */
    boolean updateByClientId(@NotNull ClientDO clientDO);

    /**
     * 通过客户端id查找客户端。
     *
     * @param clientId
     * @return
     */
    ClientDO findByClientId(@NotEmpty String clientId);

    /**
     * 分页查询客户端。
     *
     * @param pageQuery
     * @param namePrefix
     * @return
     */
    Page<ClientDO> page(@NotNull PageQuery pageQuery, String namePrefix);

}
