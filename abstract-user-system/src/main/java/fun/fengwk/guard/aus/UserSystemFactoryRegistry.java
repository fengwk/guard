package fun.fengwk.guard.aus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;

/**
 * 用户系统工厂注册表。
 *
 * @author fengwk
 */
public class UserSystemFactoryRegistry implements AutoCloseable, Iterable<UserSystemFactory> {

    private static final Logger LOG = LoggerFactory.getLogger(UserSystemFactoryRegistry.class);

    private final ConcurrentMap<String, UserSystemFactory> registry = new ConcurrentHashMap<>();
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private boolean isClosed = false;

    /**
     * 注册一个用户系统工厂。
     *
     * @param userSystemFactory not null
     * @throws IllegalStateException 如果当前注册表已经被关闭，将抛出该异常。
     */
    public void register(UserSystemFactory userSystemFactory) {
        Objects.requireNonNull(userSystemFactory, "userSystemFactory cannot be null");

        rwLock.writeLock().lock();
        try {
            if (isClosed) {
                throw new IllegalArgumentException("MultiUserSystemProvider is closed");
            }

            UserSystemFactory oldUserSystemFactory = registry.put(userSystemFactory.getUserSystemType(), userSystemFactory);
            if (oldUserSystemFactory != null) {
                LOG.warn("userSystemFactory replaced, userSystemType={}, oldUserSystemFactory={}, newUserSystemFactory={}",
                    userSystemFactory.getUserSystemType(), oldUserSystemFactory, userSystemFactory);
                try {
                    oldUserSystemFactory.close();
                } catch (Exception e) {
                    LOG.error("close oldUserSystem error", e);
                }
            }
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    /**
     * 通过用户系统类型获取注册的用户系统工厂。
     *
     * @param userSystemType not null
     * @return
     * @throws IllegalStateException 如果当前提供者已经被关闭，将抛出该异常。
     */
    public UserSystemFactory get(String userSystemType) {
        Objects.requireNonNull(userSystemType, "userSystemType cannot be null");

        rwLock.readLock().lock();
        try {
            if (isClosed) {
                throw new IllegalArgumentException("MultiUserSystemProvider is closed");
            }

            return registry.get(userSystemType);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    /**
     * 通过用户系统类型获取注册的用户系统工厂，并使用action函数对其进行操作。
     *
     * @param userSystemType not null
     * @param action not null
     * @param <T>
     * @return
     */
    public <T> T get(String userSystemType, Function<UserSystemFactory, T> action) {
        Objects.requireNonNull(userSystemType, "userSystemType cannot be null");
        Objects.requireNonNull(action, "action cannot be null");

        rwLock.readLock().lock();
        try {
            if (isClosed) {
                throw new IllegalArgumentException("MultiUserSystemProvider is closed");
            }

            UserSystemFactory userSystemFactory = registry.get(userSystemType);
            return action.apply(userSystemFactory);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    @Override
    public void close() throws Exception {
        rwLock.writeLock().lock();
        try {
            isClosed = true;
            LOG.info("MultiUserSystemProvider closed");

            List<Exception> exceptionList = new ArrayList<>();
            for (Map.Entry<String, UserSystemFactory> entry : registry.entrySet()) {
                try {
                    entry.getValue().close();
                } catch (Exception ex) {
                    exceptionList.add(ex);
                }
            }

            if (!exceptionList.isEmpty()) {
                Exception ex = new Exception("MultiUserSystemProvider close error");
                for (Exception suppressed : exceptionList) {
                    ex.addSuppressed(suppressed);
                }
                throw ex;
            }
        } finally {
            rwLock.writeLock().unlock();
        }
    }


    @Override
    public Iterator<UserSystemFactory> iterator() {
        return registry.values().iterator();
    }

}
