package fun.fengwk.guard.sus.share.model;

import fun.fengwk.guard.sus.share.constant.ValidationConstraints;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * 用户创建器。
 *
 * @author fengwk
 */
@Data
public class UserCreatorDTO {

    /**
     * 用户名。
     */
    @Size(max = 32)
    private String username;

    /**
     * 密码摘要。
     */
    @Size(max = 64)
    private String passwordDigest;

    /**
     * 手机号。
     */
    @Pattern(regexp = ValidationConstraints.REGEX_MOBILE)
    private String mobile;

    /**
     * 邮箱。
     */
    @Size(max = 128)
    @Pattern(regexp = ValidationConstraints.REGEX_EMAIL)
    private String email;

    /**
     * 用户属性集。
     */
    @NotNull
    private Map<String, String> properties;

}
