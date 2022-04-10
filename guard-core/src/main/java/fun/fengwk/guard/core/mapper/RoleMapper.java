package fun.fengwk.guard.core.mapper;

import fun.fengwk.guard.core.model.RoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fengwk
 */
public interface RoleMapper {

    void createIfNotExists(String namespace);

    void dropIfExists(String namespace);

    int insert(@Param("namespace") String namespace, @Param("roleDO") RoleDO roleDO);

    int deleteByRoleId(@Param("namespace") String namespace, @Param("roleId") long roleId);

    int updateByRoleId(@Param("namespace") String namespace, @Param("roleDO") RoleDO roleDO);

    RoleDO findByRoleId(@Param("namespace") String namespace, @Param("roleId") long roleId);

    List<RoleDO> page(@Param("namespace") String namespace, @Param("offset") long offset, @Param("limit") long limit, @Param("namePrefix") String namePrefix);

    long count(@Param("namespace") String namespace, @Param("namePrefix") String namePrefix);

}
