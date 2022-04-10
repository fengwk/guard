package fun.fengwk.guard.core.mapper;

import fun.fengwk.guard.core.model.RolePermissionDO;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author fengwk
 */
public interface RolePermissionMapper {

    void createIfNotExists(String namespace);

    void dropIfExists(String namespace);

    int insertAll(@Param("namespace") String namespace, @Param("rolePermissionDOs") Collection<RolePermissionDO> rolePermissionDOs);

    int deleteByIds(@Param("namespace") String namespace, @Param("ids") Collection<Long> ids);

    List<RolePermissionDO> limitByRoleId(@Param("namespace") String namespace, @Param("limit") long limit, @Param("roleId") long roleId);

    List<RolePermissionDO> limitByPermissionId(@Param("namespace") String namespace, @Param("limit") long limit, @Param("permissionId") long permissionId);

}
