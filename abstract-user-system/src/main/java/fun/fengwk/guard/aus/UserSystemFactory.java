package fun.fengwk.guard.aus;

import java.util.List;

/**
 * 用户系统工厂，每个用户系统工厂实现支持创建一种固定类型的用户系统。
 *
 * @author fengwk
 */
public interface UserSystemFactory extends AutoCloseable {

    /**
     * 获取当前工厂所能生产用户系统类型的名称。
     *
     * @return
     */
    String getUserSystemType();

    /**
     * 判断当前工厂是否支持动态命名空间。
     *
     * @return
     */
    default boolean isSupportedDynamicNamespace() {
        return getSupportedNamespaces() == null;
    }

    /**
     * 获取当前工厂支持的命名空间，范围结果有2种可能：
     * 1、如果当前工厂支持动态命名空间，返回null。
     * 2、如果当前工厂不支持动态命名空间，返回支持的命名空间列表。
     *
     * @return
     */
    List<String> getSupportedNamespaces();

    /**
     * 通过用户命名空间获取用户系统，通过namespace可以指定命名空间隔离。
     * 1、如果当前工厂支持动态命名空间，工厂会为调用者输入的任意命名空间创建新的命名空间隔离的用户系统实现。
     * 2、如果当前工厂不支持动态命名空间，那么调用者只能从getSupportedNamespaces返回的命名空间列表中选择其一作为输入，否则将抛出IllegalStateException。
     *
     * @param namespace
     * @return 返回指定namespace的用户系统，如果namespace未定义，将返回null。
     */
    AbstractUserSystem getUserSystem(String namespace);

}
