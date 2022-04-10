package fun.fengwk.simplesender.core.service;

import fun.fengwk.convention4j.api.code.CommonCodeTable;
import fun.fengwk.convention4j.api.code.ErrorCodeFactory;
import fun.fengwk.simplesender.share.model.SmsDTO;
import org.springframework.stereotype.Service;

/**
 * 短信发送服务。
 *
 * @author fengwk
 */
@Service
public class SmsSendServiceImpl implements SmsSendService {

    private final ErrorCodeFactory errorCodeFactory;

    public SmsSendServiceImpl(ErrorCodeFactory errorCodeFactory) {
        this.errorCodeFactory = errorCodeFactory;
    }

    @Override
    public void send(SmsDTO smsDTO) {
        // TODO 暂不支持短信发送
        throw errorCodeFactory.create(CommonCodeTable.UNSUPPORTED_OPERATION).asThrowable();
    }

}
