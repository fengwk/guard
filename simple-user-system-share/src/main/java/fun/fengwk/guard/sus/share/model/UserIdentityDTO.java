package fun.fengwk.guard.sus.share.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户身份信息视图。
 *
 * @author fengwk
 */
@Data
public class UserIdentityDTO {

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
