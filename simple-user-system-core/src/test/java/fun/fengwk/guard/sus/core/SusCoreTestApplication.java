package fun.fengwk.guard.sus.core;

import fun.fengwk.convention4j.springboot.test.starter.snowflake.EnableTestSnowflakeId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fengwk
 */
@EnableTestSnowflakeId
@SpringBootApplication
public class SusCoreTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SusCoreTestApplication.class, args);
    }

}
