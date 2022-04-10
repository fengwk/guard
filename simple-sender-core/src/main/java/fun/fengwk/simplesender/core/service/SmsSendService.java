package fun.fengwk.simplesender.core.service;

import fun.fengwk.simplesender.share.model.SmsDTO;

/**
 * 短信发送服务。
 *
 * @author fengwk
 */
public interface SmsSendService {

    /**
     * 发送短信。
     *
     * @param smsDTO
     */
    void send(SmsDTO smsDTO);

}
