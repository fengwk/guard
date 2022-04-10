package fun.fengwk.guard.share.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 授权请求：邮箱+验证码
 *
 * @author fengwk
 */
@Data
public class EmailVerificationCodeAuthorizeReqDTO extends BaseAuthorizeReqDTO {

    /**
     * 邮箱
     */
    @NotEmpty
    private String email;

    /**
     * 验证码
     */
    @NotEmpty
    private String verificationCode;

}
