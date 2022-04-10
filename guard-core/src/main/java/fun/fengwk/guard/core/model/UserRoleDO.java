package fun.fengwk.guard.core.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户角色关联关系。
 *
 * @author fengwk
 */
@Data
public class UserRoleDO {

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
     * 用户id。
     */
    private String userId;

    /**
     * 角色id。
     */
    private String roleId;

}
