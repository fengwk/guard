package fun.fengwk.guard.share.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 授权请求：手机+密码
 *
 * @author fengwk
 */
@Data
public class MobilePasswordAuthorizeReqDTO extends BaseAuthorizeReqDTO {

    /**
     * 手机
     */
    @NotEmpty
    private String mobile;

    /**
     * 密码
     */
    @NotEmpty
    private String password;

}
