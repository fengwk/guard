package fun.fengwk.sus.aus.injvm;

import fun.fengwk.guard.aus.UserSystemFactoryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengwk
 */
@Configuration
public class SusInjvmTestConfig {

    @Bean
    public UserSystemFactoryRegistry userSystemFactoryRegistry() {
        return new UserSystemFactoryRegistry();
    }

}
