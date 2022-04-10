package fun.fengwk.guard.core.dao;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author fengwk
 */
public abstract class BaseNamespaceCapabilityDAOFactory<DAO extends NamespaceCapabilityDAO> {

    private final ConcurrentMap<String, DAO> cache = new ConcurrentHashMap<>();

    public DAO get(String namespace) {
        return cache.computeIfAbsent(namespace, k -> newDAO(namespace));
    }

    protected abstract DAO newDAO(String namespace);

}
