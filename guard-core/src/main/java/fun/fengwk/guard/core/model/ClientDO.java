package fun.fengwk.guard.core.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客户端。
 *
 * @author fengwk
 */
@Data
public class ClientDO {

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
     * 客户端id。
     */
    private String clientId;

    /**
     * 客户端名称。
     */
    private String name;

    /**
     * 客户端描述。
     */
    private String description;

    /**
     * 客户端绑定的用户命名空间。
     */
    private String userNamespace;

    /**
     * 客户端密钥。
     */
    private String clientSecretKey;

    /**
     * 支持的重定向地址模式。
     */
    private String redirectUriPattern;

    /**
     * 授权码过期时间，单位：秒。
     */
    private Long authorizationCodeExpiresIn;

    /**
     * 访问令牌过期时间，单位：秒。
     */
    private Long accessTokenExpiresIn;

    /**
     * 刷新令牌过期时间，单位：秒。
     */
    private Long refreshTokenExpiresIn;

    /**
     * 客户端是否启用，0-禁用，1-启用。
     */
    private Integer isEnabled;

}
