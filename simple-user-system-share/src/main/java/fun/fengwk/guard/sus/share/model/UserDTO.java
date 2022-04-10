package fun.fengwk.guard.sus.share.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 用户数据表对象。
 *
 * @author fengwk
 */
@Data
public class UserDTO {

    /**
     * 创建时间。
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间。
     */
    private LocalDateTime gmtModified;

    /**
     * 数据版本号。
     */
    private Long version;

    /**
     * 用户id。
     */
    private Long userId;

    /**
     * 用户名。
     */
    private String username;

    /**
     * 密码摘要。
     */
    private String passwordDigest;

    /**
     * 手机号。
     */
    private String mobile;

    /**
     * 邮箱号。
     */
    private String email;

    /**
     * 属性表，json格式。
     */
    private Map<String, String> properties;

}
