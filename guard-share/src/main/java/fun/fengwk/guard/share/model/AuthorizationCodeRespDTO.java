package fun.fengwk.guard.share.model;

import lombok.Data;

/**
 * 授权码响应。
 *
 * @author fengwk
 */
@Data
public class AuthorizationCodeRespDTO {

    /**
     * 授权码。
     */
    private String code;

    /**
     * 状态标识。
     */
    private String state;

}
