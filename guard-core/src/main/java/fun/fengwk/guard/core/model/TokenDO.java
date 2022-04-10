package fun.fengwk.guard.core.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 令牌。
 *
 * @author fengwk
 */
@Data
public class TokenDO {

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
     * 访问令牌。
     */
    private String accessToken;

    /**
     * 访问令牌过期时间。
     */
    private LocalDateTime gmtAccessTokenExpired;

    /**
     * 刷新令牌。
     */
    private String refreshToken;

    /**
     * 刷新令牌过期时间。
     */
    private LocalDateTime gmtRefreshTokenExpired;

    /**
     * 令牌关联的客户端id。
     */
    private Long clientId;

    /**
     * 令牌关联的用户id。
     */
    private String userId;

    /**
     * 是否已回收，0-未回收，1-已回收。
     */
    private Integer isRevoke;

}
