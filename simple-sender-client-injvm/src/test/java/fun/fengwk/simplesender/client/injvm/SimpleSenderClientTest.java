package fun.fengwk.simplesender.client.injvm;

import fun.fengwk.convention4j.api.code.CommonCodeTable;
import fun.fengwk.convention4j.api.result.Result;
import fun.fengwk.simplesender.client.SimpleSenderClient;
import fun.fengwk.simplesender.share.model.EmailDTO;
import fun.fengwk.simplesender.share.model.SmsDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fengwk
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleSenderClientInJvmTestApplication.class)
public class SimpleSenderClientTest {

    @Autowired
    private SimpleSenderClient simpleSenderClient;

    @Test
    public void testEmail() {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setTargetEmail("759543714@qq.com");
        emailDTO.setSubject("test subject");
        emailDTO.setContent("test content");
        Result<Void> res = simpleSenderClient.sendEmail(emailDTO);
        assert res.isSuccess();
    }

    @Test
    public void testSms() {
        SmsDTO smsDTO = new SmsDTO();
        smsDTO.setTargetMobile("157068433333333");
        smsDTO.setContent("hello");
        Result<Void> res = simpleSenderClient.sendSms(smsDTO);
        assert !res.isSuccess();
        assert CommonCodeTable.UNSUPPORTED_OPERATION.equalsCode(res.getCode());
    }

}
