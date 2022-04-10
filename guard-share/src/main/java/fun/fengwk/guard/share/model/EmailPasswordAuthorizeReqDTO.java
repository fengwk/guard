package fun.fengwk.guard.share.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 授权请求：邮箱+密码
 *
 * @author fengwk
 */
@Data
public class EmailPasswordAuthorizeReqDTO extends BaseAuthorizeReqDTO {

    /**
     * 邮箱
     */
    @NotEmpty
    private String email;

    /**
     * 密码
     */
    @NotEmpty
    private String password;

}
