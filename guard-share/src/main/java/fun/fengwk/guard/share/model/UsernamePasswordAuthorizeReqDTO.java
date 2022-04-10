package fun.fengwk.guard.share.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 授权请求：用户名+密码
 *
 * @author fengwk
 */
@Data
public class UsernamePasswordAuthorizeReqDTO extends BaseAuthorizeReqDTO {

    /**
     * 用户名
     */
    @NotEmpty
    private String username;

    /**
     * 密码
     */
    @NotEmpty
    private String password;

}
