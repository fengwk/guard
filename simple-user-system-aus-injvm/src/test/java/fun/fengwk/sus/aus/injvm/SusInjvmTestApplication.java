package fun.fengwk.sus.aus.injvm;

import fun.fengwk.convention4j.springboot.test.starter.snowflake.EnableTestSnowflakeId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fengwk
 */
@EnableTestSnowflakeId
@SpringBootApplication
public class SusInjvmTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SusInjvmTestApplication.class, args);
    }

}
