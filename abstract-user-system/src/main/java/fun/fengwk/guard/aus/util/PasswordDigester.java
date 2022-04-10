package fun.fengwk.guard.aus.util;

/**
 * @author fengwk
 */
public interface PasswordDigester {

    /**
     * 对密码进行摘要。
     *
     * @param password
     * @return
     */
    String digest(String password);

}
