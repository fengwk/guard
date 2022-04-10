package fun.fengwk.guard.core.mapper;

import fun.fengwk.guard.core.model.TokenDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author fengwk
 */
public interface TokenMapper {

    void createIfNotExists(String namespace);

    void dropIfExists(String namespace);

    int insert(@Param("namespace") String namespace, @Param("tokenDO") TokenDO tokenDO);

    int updateRevoked(@Param("namespace") String namespace, @Param("accessToken") String accessToken);

    TokenDO findByAccessToken(@Param("namespace") String namespace, @Param("accessToken") String accessToken);

    TokenDO findLastByRefreshToken(@Param("namespace") String namespace, @Param("refreshToken") String refreshToken);

}
