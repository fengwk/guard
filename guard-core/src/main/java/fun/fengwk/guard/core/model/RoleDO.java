package fun.fengwk.guard.core.model;

import fun.fengwk.automapper.annotation.UseGeneratedKeys;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author fengwk
 */
@Data
public class RoleDO {

    /**
     * 主键。
     */
    @UseGeneratedKeys
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
    private Long roleId;

    /**
     * 角色名称。
     */
    private String name;

    /**
     * 角色描述。
     */
    private String description;

    /**
     * 角色是否启用，0-禁用，1-启用。
     */
    private Integer isEnabled;

}
