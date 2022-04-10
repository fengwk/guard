package fun.fengwk.guard.aus.util;

/**
 * NoopPasswordDigester实现不会进行任何摘要动作。
 *
 * @author fengwk
 */
public class NoopPasswordDigester implements PasswordDigester {

    @Override
    public String digest(String password) {
        return password;
    }

}
