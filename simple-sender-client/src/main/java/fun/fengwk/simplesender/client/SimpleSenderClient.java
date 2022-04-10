package fun.fengwk.simplesender.client;

import fun.fengwk.convention4j.api.result.Result;
import fun.fengwk.simplesender.share.model.EmailDTO;
import fun.fengwk.simplesender.share.model.SmsDTO;

/**
 * @author fengwk
 */
public interface SimpleSenderClient {

    /**
     * 发送邮件。
     *
     * @param emailDTO
     * @return
     */
    Result<Void> sendEmail(EmailDTO emailDTO);

    /**
     * 发送短信。
     *
     * @param smsDTO
     * @return
     */
    Result<Void> sendSms(SmsDTO smsDTO);

}
