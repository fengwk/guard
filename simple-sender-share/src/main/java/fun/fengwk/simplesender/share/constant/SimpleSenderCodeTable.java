package fun.fengwk.simplesender.share.constant;

import fun.fengwk.convention4j.api.code.CodeTable;
import fun.fengwk.convention4j.api.code.ErrorCode;

/**
 * @author fengwk
 */
public enum SimpleSenderCodeTable implements CodeTable {

    /**
     * 邮件发送失败。
     */
    EMAIL_SEND_FAILED("0100"),

    /**
     * 短信发送失败。
     */
    SMS_SEND_FAILED("0200"),

    ;

    /**
     * guard域。
     */
    public static final String SIMPLE_SENDER = "SimpleSender";

    private final String code;

    SimpleSenderCodeTable(String code) {
        this.code = code;
    }

    static String encodeCode(String num) {
        return ErrorCode.encodeCode(SIMPLE_SENDER, num);
    }

    @Override
    public String getCode() {
        return code;
    }

}
