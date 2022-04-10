package fun.fengwk.guard.share.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 授权请求：手机+验证码
 *
 * @author fengwk
 */
@Data
public class MobileVerificationCodeAuthorizeReqDTO extends BaseAuthorizeReqDTO {

    /**
     * 手机
     */
    @NotEmpty
    private String email;

    /**
     * 验证码
     */
    @NotEmpty
    private String verificationCode;

}
