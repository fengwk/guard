package fun.fengwk.guard.sus.core.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户身份信息视图。
 *
 * @author fengwk
 */
@Data
public class UserIdentityBO {

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

}
