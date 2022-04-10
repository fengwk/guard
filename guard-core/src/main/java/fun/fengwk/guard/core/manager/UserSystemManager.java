package fun.fengwk.guard.core.manager;

import fun.fengwk.guard.aus.AbstractUserSystem;

/**
 * 用户系统管理器。
 *
 * @author fengwk
 */
public interface UserSystemManager {

    /**
     * 通过用户命名空间获取相应的用户系统。
     *
     * @param userNamespace
     * @return
     */
    AbstractUserSystem getUserSystem(String userNamespace);

}
