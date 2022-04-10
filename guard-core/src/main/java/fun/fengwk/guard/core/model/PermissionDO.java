package fun.fengwk.guard.core.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 权限，由动作和资源描述符组成。
 *
 * @author fengwk
 */
@Data
public class PermissionDO {

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
     * 权限id。
     */
    private Long permissionId;

    /**
     * 权限名称。
     */
    private String name;

    /**
     * 权限描述。
     */
    private String description;

    /**
     * 动作，如read、write。
     */
    private String action;

    /**
     * 资源描述符。
     */
    private String resourceDescriptor;

    /**
     * 权限是否启用，0-禁用，1-启用。
     */
    private Integer isEnabled;

}
