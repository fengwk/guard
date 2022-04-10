package fun.fengwk.guard.share.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author fengwk
 */
@Data
public abstract class BaseAuthorizeReqDTO {

    /**
     * 响应类型。
     */
    @NotEmpty
    private String responseType;

    /**
     * 请求的客户端id。
     */
    @NotEmpty
    private String clientId;

    /**
     * 重定向地址。
     */
    @NotEmpty
    private String redirectUri;

    /**
     * 申请的权限范围。
     */
    @NotEmpty
    private String scope;

    /**
     * 状态标识。
     */
    private String state;

}
