package fun.fengwk.simplesender.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengwk
 */
@EnableConfigurationProperties(SimpleSenderProperties.class)
@ComponentScan("fun.fengwk.simplesender.core")
@Configuration
public class SimpleSenderAutoConfiguration {
}
