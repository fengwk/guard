package fun.fengwk.guard.core.mapper;

import fun.fengwk.automapper.annotation.AutoMapper;
import fun.fengwk.guard.core.model.UserNamespaceDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fengwk
 */
@AutoMapper
public interface UserNamespaceMapper {

    int insert(UserNamespaceDO userNamespaceDO);

    int deleteByUserNamespace(String userNamespace);

    int updateByUserNamespace(UserNamespaceDO userNamespaceDO);

    UserNamespaceDO findByUserNamespace(String userNamespace);

    List<UserNamespaceDO> page(@Param("offset") long offset, @Param("limit") long limit, @Param("namePrefix") String namePrefix);

    long count(String namePrefix);

}
