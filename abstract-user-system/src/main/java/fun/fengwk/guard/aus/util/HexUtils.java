package fun.fengwk.guard.aus.util;

/**
 * 十六进制转换工具。
 *
 * @author fengwk
 */
public class HexUtils {

    private static final char[] HEX = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'A', 'B', 'C', 'D', 'E', 'F'
    };

    private HexUtils() {}

    /**
     * 二进制字节数组转十六进制字符串。
     *
     * @param bytes not null.
     * @return
     */
    public static String bytes2hexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(HEX[(b >>> 4) & 0xf]);
            sb.append(HEX[b & 0xf]);
        }
        return sb.toString();
    }

    /**
     * 十六进制字符串转二进制字节数组。
     *
     * @param hexStr not null.
     * @return
     */
    public static byte[] hexStr2bytes(String hexStr) {
        byte[] bytes = new byte[hexStr.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            int hi = Character.digit(hexStr.charAt(2*i), 16);
            int lo = Character.digit(hexStr.charAt(2*i+1), 16);
            bytes[i] = (byte) ((hi << 4) | lo);
        }
        return bytes;
    }

}
