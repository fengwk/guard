package fun.fengwk.guard.sus.share.model;

import lombok.Data;

/**
 * 用户查询器。
 *
 * @author fengwk
 */
@Data
public class UserQuery {

    /**
     * 用户名前缀。
     */
    private String usernamePrefix;

    /**
     * 手机号前缀。
     */
    private String mobilePrefix;

    /**
     * 邮箱前缀。
     */
    private String emailPredix;

}
