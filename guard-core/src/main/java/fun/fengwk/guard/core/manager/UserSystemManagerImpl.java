package fun.fengwk.guard.core.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import fun.fengwk.convention4j.api.code.CommonCodeTable;
import fun.fengwk.convention4j.api.code.ErrorCode;
import fun.fengwk.convention4j.api.code.ErrorCodeFactory;
import fun.fengwk.convention4j.common.ConvertUtils;
import fun.fengwk.guard.share.constant.GuardCodeTable;
import fun.fengwk.guard.aus.AbstractUserSystem;
import fun.fengwk.guard.aus.UserSystemFactoryRegistry;
import fun.fengwk.guard.core.dao.UserNamespaceDAO;
import fun.fengwk.guard.core.model.UserNamespaceDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author fengwk
 */
@Slf4j
@Component
public class UserSystemManagerImpl implements UserSystemManager {

    private final ErrorCodeFactory errorCodeFactory;
    private final UserSystemFactoryRegistry userSystemFactoryRegistry;
    private final UserNamespaceDAO userNamespaceDAO;

    /**
     * 使用缓存减少数据库查询次数，由于用户命名空间的变更应该是少数情况的，因此使用一定的缓存超时保持其最终一致性。
     * TODO 可以使用监听事件的方式增强一致性
     */
    private final Cache<String, AbstractUserSystem> cache = CacheBuilder.newBuilder()
        .maximumSize(1024)
        .expireAfterWrite(Duration.ofSeconds(30))
        .build();

    public UserSystemManagerImpl(ErrorCodeFactory errorCodeFactory,
                                 UserSystemFactoryRegistry userSystemFactoryRegistry,
                                 UserNamespaceDAO userNamespaceDAO) {
        this.errorCodeFactory = errorCodeFactory;
        this.userSystemFactoryRegistry = userSystemFactoryRegistry;
        this.userNamespaceDAO = userNamespaceDAO;
    }

    @Override
    public AbstractUserSystem getUserSystem(String userNamespace) {
        try {
            return cache.get(userNamespace, () -> doGetUserSystem(userNamespace));
        } catch (ExecutionException e) {
            // 输出异常，忽略错误码异常的日志输出，因为错误码异常的日志输出应该在抛出时被处理
            if (!(e.getCause() instanceof ErrorCode)) {
                log.error("doGetUserSystem execute error", e.getCause());
            }
            throw ErrorCode.asThrowable(
                e.getCause(), errorCodeFactory.create(CommonCodeTable.ILLEGAL_STATE)::asThrowable);
        }
    }

    private AbstractUserSystem doGetUserSystem(String userNamespace) {
        // 使用userNamespace获取UserNamespacePO
        UserNamespaceDO userNamespaceDO = userNamespaceDAO.findByUserNamespace(userNamespace);

        // 如果userNamespaceDO为null，则抛出错误码
        if (userNamespaceDO == null) {
            log.warn("user namespace not found, userNamespace={}", userNamespace);
            throw errorCodeFactory.create(GuardCodeTable.USER_NAMESPACE_NOT_FOUND).asThrowable();
        }

        // 如果userNamespaceDO未启用，则抛出错误码
        if (!Objects.equals(ConvertUtils.int2bool(userNamespaceDO.getIsEnabled()), true)) {
            log.info("user namespace disabled, userNamespace={}", userNamespace);
            throw errorCodeFactory.create(GuardCodeTable.USER_NAMESPACE_DISABLED).asThrowable();
        }

        // 使用userNamespaceDO中提供的userSystemType和userSystemNamespace信息获取相应的用户系统
        return userSystemFactoryRegistry.get(userNamespaceDO.getUserSystemType(),
            userSystemFactory -> userSystemFactory.getUserSystem(userNamespaceDO.getUserSystemNamespace()));
    }

}
