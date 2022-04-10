package fun.fengwk.guard.share.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author fengwk
 */
@Data
public class TokenReqDTO {

    /**
     * 授权类型。
     */
    @NotEmpty
    private String grantType;

    /**
     * 授权码。
     */
    @NotEmpty
    private String code;

    /**
     * 重定向地址。
     */
    @NotEmpty
    private String redirectUri;

    /**
     * 客户端id。
     */
    @NotEmpty
    private String clientId;

    /**
     * 客户端密钥。
     */
    @NotEmpty
    private String clientSecretKey;

}
