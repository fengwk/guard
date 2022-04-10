package fun.fengwk.guard.sus.core.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author fengwk
 */
@Data
public class UserPropertiesBO {

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
    private Long userId;

    /**
     * 数据版本号。
     */
    private Long version;

    /**
     * 属性表，json格式。
     */
    private String properties;

}
