package fun.fengwk.sus.aus.injvm;

import fun.fengwk.guard.aus.AbstractUserSystem;
import fun.fengwk.guard.aus.UserSystemFactory;
import fun.fengwk.guard.aus.UserSystemFactoryRegistry;
import fun.fengwk.guard.sus.core.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author fengwk
 */
@Component
public class SimpleUserSystemFactory implements InitializingBean, UserSystemFactory {

    private final UserSystemFactoryRegistry userSystemFactoryRegistry;
    private final UserService userService;

    private final ConcurrentMap<String, SimpleUserSystem> cache = new ConcurrentHashMap<>();

    public SimpleUserSystemFactory(UserSystemFactoryRegistry userSystemFactoryRegistry, UserService userService) {
        this.userSystemFactoryRegistry = userSystemFactoryRegistry;
        this.userService = userService;
    }

    @Override
    public void afterPropertiesSet() {
        userSystemFactoryRegistry.register(this);
    }

    @Override
    public String getUserSystemType() {
        return SimpleUserSystem.class.getSimpleName();
    }

    @Override
    public List<String> getSupportedNamespaces() {
        return null;
    }

    @Override
    public AbstractUserSystem getUserSystem(String namespace) {
        return cache.computeIfAbsent(namespace, ns -> new SimpleUserSystem(ns, userService));
    }

    @Override
    public void close() throws Exception {
        // nothing to do
    }

}
