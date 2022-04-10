package fun.fengwk.guard.core.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * OAuth2授权码。
 *
 * @author fengwk
 */
@Data
public class AuthorizationCodeDO {

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
     * 授权码。
     */
    private String code;

    /**
     * 授权码关联的用户id。
     */
    private String userId;

    /**
     * 授权码关联的客户端id。
     */
    private String clientId;

    /**
     * 重定向地址。
     */
    private String redirectUri;

    /**
     * 申请的权限范围。
     */
    private String scope;

    /**
     * 过期时间。
     */
    private LocalDateTime gmtExpired;

    /**
     * 是否已使用，0-未使用，1-已使用。
     */
    private Integer isUsed;

}
