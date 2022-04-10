package fun.fengwk.guard.aus.util;

import org.junit.Test;

import javax.crypto.SecretKey;

/**
 * @author fengwk
 */
public class HmacSHA256DigesterTest {

    private static final String SERIALIZED_SECRET_KEY = "CC4F4229CC0D0E581ABA2ED6FFB9107635BFD83A3A8846509252E7A691DB0E63";

    @Test
    public void test1() {
        SecretKey secretKey = HmacSHA256Digester.deserializeSecretKey(SERIALIZED_SECRET_KEY);
        assert HmacSHA256Digester.serializeSecretKey(secretKey).equals(SERIALIZED_SECRET_KEY);
    }

    @Test
    public void test2() {
        HmacSHA256Digester digester = new HmacSHA256Digester(HmacSHA256Digester.deserializeSecretKey(SERIALIZED_SECRET_KEY));
        String digest = digester.digest("hello, fengwk.");
        assert digest.equals("FCF38096BF11562548ABAD430336F631B3A42EE4F9D1A819C06E28A334BB2EC8");
    }

    @Test
    public void test3() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb.append(i);
        }
        String largeText = sb.toString();

        HmacSHA256Digester digester = new HmacSHA256Digester(HmacSHA256Digester.deserializeSecretKey(SERIALIZED_SECRET_KEY));
        String digest = digester.digest(largeText);
        assert digest.equals("5C5B441CF8C554E5451433AFD00CD45F22CF647015E6E5091C8BC690B1B4AFD5");
    }

}
