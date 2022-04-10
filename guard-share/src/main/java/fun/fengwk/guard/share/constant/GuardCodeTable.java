package fun.fengwk.guard.share.constant;

import fun.fengwk.convention4j.api.code.CodeTable;
import fun.fengwk.convention4j.api.code.ErrorCode;

/**
 * @author fengwk
 */
public enum GuardCodeTable implements CodeTable {

    /* 客户端类异常 */

    /**
     * 找不到指定的客户端。
     */
    CLIENT_NOT_FOUND(encodeCode("0100")),

    /**
     * 客户端已关闭。
     */
    CLIENT_DISABLED(encodeCode("0100")),

    /* 用户命名空间类异常 */

    /**
     * 找不到指定的用户命名空间。
     */
    USER_NAMESPACE_NOT_FOUND(encodeCode("0200")),

    /**
     * 用户命名空间已关闭。
     */
    USER_NAMESPACE_DISABLED(encodeCode("0201")),

    /* 用户系统类异常 */

    /**
     * 找不到指定的用户系统。
     */
    USER_SYSTEM_NOT_FOUND(encodeCode("0300")),


    /* 认证异常 */

    /**
     * 找不到指定的用户。
     */
    USER_NOT_FOUND(encodeCode("0400")),

    /**
     * 密码错误。
     */
    PASSWORD_ERROR(encodeCode("0401")),

    ;

    /**
     * guard域。
     */
    public static final String GUARD = "guard";

    private final String code;

    GuardCodeTable(String code) {
        this.code = code;
    }

    static String encodeCode(String num) {
        return ErrorCode.encodeCode(GUARD, num);
    }

    @Override
    public String getCode() {
        return code;
    }

}
