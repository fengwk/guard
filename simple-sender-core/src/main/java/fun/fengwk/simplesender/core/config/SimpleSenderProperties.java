package fun.fengwk.simplesender.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author fengwk
 */
@ConfigurationProperties("simple-sender")
@Data
public class SimpleSenderProperties {

    /**
     * 邮件发送者列表。
     */
    private List<String> fromMails;

}
