package fun.fengwk.guard.aus.util;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * HmacSHA256摘要器。
 *
 * @author fengwk
 */
public class HmacSHA256Digester extends BaseDigester {

    private static final String ALGO = "HmacSHA256";

    private final SecretKey secretKey;

    public HmacSHA256Digester(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    protected byte[] doDigest(byte[] data) {
        try {
            Mac mac = Mac.getInstance(ALGO);
            mac.init(secretKey);
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 生成HmacSHA256密钥。
     *
     * @return
     */
    public static SecretKey generateSecretKey() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(ALGO);
            SecretKey secretKey = kg.generateKey();
            return secretKey;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 序列化HmacSHA256密钥，序列化后的长度为64个字符。
     *
     * @param secretKey not null.
     * @return
     */
    public static String serializeSecretKey(SecretKey secretKey) {
        if (!ALGO.equals(secretKey.getAlgorithm())) {
            throw new IllegalArgumentException("secretKey's algo must be " + ALGO + ".");
        }

        return HexUtils.bytes2hexStr(secretKey.getEncoded());
    }

    /**
     * 反序列化HmacSHA256密钥。
     *
     * @param serializedSecretKey not null.
     * @return
     */
    public static SecretKey deserializeSecretKey(String serializedSecretKey) {
        return new SecretKeySpec(HexUtils.hexStr2bytes(serializedSecretKey), ALGO);
    }

}
