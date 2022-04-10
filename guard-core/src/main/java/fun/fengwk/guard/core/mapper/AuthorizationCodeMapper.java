package fun.fengwk.guard.core.mapper;

import fun.fengwk.guard.core.model.AuthorizationCodeDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author fengwk
 */
public interface AuthorizationCodeMapper {

    void createIfNotExists(String namespace);

    void dropIfExists(String namespace);

    int insert(@Param("namespace") String namespace, @Param("authorizationCodeDO") AuthorizationCodeDO authorizationCodeDO);

    int updateUsed(@Param("namespace") String namespace, @Param("code") String code);

    AuthorizationCodeDO findByCode(@Param("namespace") String namespace, @Param("code") String code);

}
