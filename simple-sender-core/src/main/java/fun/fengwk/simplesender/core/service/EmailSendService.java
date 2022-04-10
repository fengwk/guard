package fun.fengwk.simplesender.core.service;

import fun.fengwk.simplesender.share.model.EmailDTO;

/**
 * 邮件发送服务。
 *
 * @author fengwk
 */
public interface EmailSendService {

    /**
     * 发送邮件。
     *
     * @param emailDTO
     */
    void send(EmailDTO emailDTO);

}
