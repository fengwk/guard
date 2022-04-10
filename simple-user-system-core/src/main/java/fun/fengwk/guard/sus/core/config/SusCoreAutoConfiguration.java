package fun.fengwk.guard.sus.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengwk
 */
@MapperScan("fun.fengwk.guard.sus.core.mapper")
@ComponentScan("fun.fengwk.guard.sus.core")
@Configuration
public class SusCoreAutoConfiguration {

}
