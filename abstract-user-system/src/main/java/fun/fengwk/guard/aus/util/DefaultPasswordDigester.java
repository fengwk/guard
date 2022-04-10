package fun.fengwk.guard.aus.util;

/**
 * @author fengwk
 */
public class DefaultPasswordDigester implements PasswordDigester {

    private final BaseDigester digester;

    public DefaultPasswordDigester(BaseDigester digester) {
        this.digester = digester;
    }

    @Override
    public String digest(String password) {
        return digester.digest(password);
    }

}
