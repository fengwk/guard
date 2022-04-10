package fun.fengwk.guard.core.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Test;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/**
 * @author fengwk
 */
public class CacheTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(1024)
            .expireAfterWrite(Duration.ofMillis(500))
            .build();
        assert cache.getIfPresent("key") == null;
        assert cache.get("key", () -> "val").equals("val");
        assert Objects.equals(cache.getIfPresent("key"), "val");
        Thread.sleep(500);
        assert cache.getIfPresent("key") == null;
    }

}
