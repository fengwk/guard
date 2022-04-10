package fun.fengwk.guard.sus.share.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author fengwk
 */
@Data
public class UserPropertiesDTO {

    /**
     * 创建时间。
     */
    private LocalDateTime createdTime;

    /**
     * 修改时间。
     */
    private LocalDateTime modifiedTime;

    /**
     * 数据版本号。
     */
    private Long version;

    /**
     * 用户id。
     */
    private Long userId;

    /**
     * 属性表。
     */
    private Map<String, String> properties;

}
