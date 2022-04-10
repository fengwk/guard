package fun.fengwk.simplesender.share.model;

import lombok.Data;

/**
 * 短信。
 *
 * @author fengwk
 */
@Data
public class SmsDTO {

    /**
     * 目标手机号。
     */
    private String targetMobile;

    /**
     * 短信内容。
     */
    private String content;

}
