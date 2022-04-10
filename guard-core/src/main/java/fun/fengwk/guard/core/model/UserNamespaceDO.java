package fun.fengwk.guard.core.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户命名空间。
 *
 * @author fengwk
 */
@Data
public class UserNamespaceDO {

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
     * 用户命名空间。
     */
    private String userNamespace;

    /**
     * 用户命名空间的描述。
     */
    private String description;

    /**
     * 当前用户命名空间关联的用户系统类型。
     */
    private String userSystemType;

    /**
     * 当前用户命名空间关联的用户系统命名空间。
     */
    private String userSystemNamespace;

    /**
     * 用户命名空间是否启用：0-未启用，1-启用。
     */
    private Integer isEnabled;

}
