package fun.fengwk.simplesender.share.model;

import lombok.Data;

/**
 * 邮件。
 *
 * @author fengwk
 */
@Data
public class EmailDTO {

    /**
     * 目标邮箱地址。
     */
    private String targetEmail;

    /**
     * 邮件主题。
     */
    private String subject;

    /**
     * 邮件内容。
     */
    private String content;

}
