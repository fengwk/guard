package fun.fengwk.guard.core.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色权限关联关系。
 *
 * @author fengwk
 */
@Data
public class RolePermissionDO {

    /**
     * 主键。
     */
    private Long id;

    /**
     * 创建时间。
     */
    private LocalDateTime gmtCreated;

    /**
     * 修改时间。
     */
    private LocalDateTime gmtModified;

    /**
     * 角色id。
     */
    private String roleId;

    /**
     * 权限id。
     */
    private String permissionId;

}
