package fun.fengwk.guard.core.mapper;

import fun.fengwk.guard.core.model.PermissionDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fengwk
 */
public interface PermissionMapper {

    void createIfNotExists(String namespace);

    void dropIfExists(String namespace);

    int insert(@Param("namespace") String namespace, @Param("permissionDO") PermissionDO permissionDO);

    int deleteByPermissionId(@Param("namespace") String namespace, @Param("permissionId") long permissionId);

    int updateByPermissionId(@Param("namespace") String namespace, @Param("permissionDO") PermissionDO permissionDO);

    PermissionDO findByPermissionId(@Param("namespace") String namespace, @Param("permissionId") long permissionId);

    List<PermissionDO> page(@Param("namespace") String namespace, @Param("offset") long offset, @Param("limit") long limit, @Param("permissionPrefix") String permissionPrefix);

    long count(@Param("namespace") String namespace, @Param("permissionPrefix") String permissionPrefix);

}
