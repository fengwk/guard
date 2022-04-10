package fun.fengwk.guard.core.dao;

import fun.fengwk.guard.core.model.TokenDO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 令牌。
 *
 * @author fengwk
 */
public interface TokenDAO extends NamespaceCapabilityDAO {

    /**
     * 尝试创建指定命名空间的令牌表，成功返回true，否则返回false。
     */
    void createIfNotExists();

    /**
     * 尝试删除指定命名空间的令牌表。
     */
    void dropIfExists();

    /**
     * 新增令牌。
     *
     * @param tokenDO
     */
    void insert(@NotNull TokenDO tokenDO);

    /**
     * 更新指定accessToken的令牌为已回收。
     *
     * @param accessToken
     * @return
     */
    boolean updateRevoked(@NotEmpty String accessToken);

    /**
     * 通过访问令牌获取令牌。
     *
     * @param accessToken
     * @return
     */
    TokenDO findByAccessToken(@NotEmpty String accessToken);

    /**
     * 通过刷新令牌获取修改时间最后的令牌。
     *
     * @param refreshToken
     * @return
     */
    TokenDO findLastByRefreshToken(@NotEmpty String refreshToken);

}
