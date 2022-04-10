package fun.fengwk.guard.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengwk
 */
@MapperScan("fun.fengwk.guard.core.mapper")
@ComponentScan("fun.fengwk.guard.core")
@Configuration
public class GuardCoreAutoConfiguration {


}
