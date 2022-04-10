package fun.fengwk.simplesender.client.injvm;

import fun.fengwk.convention4j.api.result.Result;
import fun.fengwk.convention4j.api.result.Results;
import fun.fengwk.convention4j.springboot.starter.result.AutoResultExceptionHandler;
import fun.fengwk.simplesender.client.SimpleSenderClient;
import fun.fengwk.simplesender.core.service.EmailSendService;
import fun.fengwk.simplesender.core.service.SmsSendService;
import fun.fengwk.simplesender.share.model.EmailDTO;
import fun.fengwk.simplesender.share.model.SmsDTO;
import org.springframework.stereotype.Component;

/**
 * @author fengwk
 */
@AutoResultExceptionHandler
@Component
public class SimpleSenderInJvmClient implements SimpleSenderClient {

    private final EmailSendService emailSendService;
    private final SmsSendService smsSendService;

    public SimpleSenderInJvmClient(EmailSendService emailSendService, SmsSendService smsSendService) {
        this.emailSendService = emailSendService;
        this.smsSendService = smsSendService;
    }

    @Override
    public Result<Void> sendEmail(EmailDTO emailDTO) {
        emailSendService.send(emailDTO);
        return Results.success();
    }

    @Override
    public Result<Void> sendSms(SmsDTO smsDTO) {
        smsSendService.send(smsDTO);
        return Results.success();
    }

}
