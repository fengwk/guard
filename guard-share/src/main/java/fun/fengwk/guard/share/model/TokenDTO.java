package fun.fengwk.guard.share.model;

import lombok.Data;

/**
 * 令牌。
 *
 * @author fengwk
 */
@Data
public class TokenDTO {

    /**
     * 访问令牌。
     */
    private String accessToken;

    /**
     * 令牌类型。
     */
    private String tokenType;

    /**
     * 过期时间，单位：秒。
     */
    private long expiresIn;

    /**
     * 刷新令牌。
     */
    private String refreshToken;

}
