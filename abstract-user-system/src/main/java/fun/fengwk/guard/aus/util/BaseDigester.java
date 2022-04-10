package fun.fengwk.guard.aus.util;

import java.nio.charset.StandardCharsets;

/**
 * 摘要器。
 *
 * @author fengwk
 */
public abstract class BaseDigester {

    /**
     * 对指定的text进行摘要。
     *
     * @param text not empty.
     * @return
     */
    public String digest(String text) {
        if (text == null || text.length() == 0) {
            throw new IllegalArgumentException("text cannot be empty");
        }

        byte[] data = text.getBytes(StandardCharsets.UTF_8);
        byte[] digested = doDigest(data);
        return HexUtils.bytes2hexStr(digested);
    }

    /**
     * 对输入的data进行摘要。
     *
     * @param data not null.
     * @return
     */
    protected abstract byte[] doDigest(byte[] data);

}
