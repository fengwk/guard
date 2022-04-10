package fun.fengwk.guard.core.dao;

import com.google.common.base.Preconditions;
import org.springframework.util.StringUtils;

/**
 * @author fengwk
 */
public class BaseNamespaceCapabilityDAO implements NamespaceCapabilityDAO {

    private final String namespace;

    /**
     *
     * @param namespace not empty
     */
    public BaseNamespaceCapabilityDAO(String namespace) {
        Preconditions.checkArgument(!StringUtils.isEmpty(namespace), "namespace cannot be empty");

        this.namespace = namespace;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

}
