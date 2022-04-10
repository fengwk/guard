package fun.fengwk.guard.sus.core.dao;

import fun.fengwk.guard.sus.core.model.UserIdentityBO;

/**
 * @author fengwk
 */
public interface UserIdentityDAO {

    /**
     * 通过id查找用户身份信息。
     *
     * @param namespace not empty
     * @param id
     * @return
     */
    UserIdentityBO getById(String namespace, long id);

    /**
     * 通过username查找用户身份信息。
     *
     * @param namespace not empty
     * @param username
     * @return
     */
    UserIdentityBO getByUsername(String namespace, String username);

    /**
     * 通过email查找用户身份信息。
     *
     * @param namespace not empty
     * @param email
     * @return
     */
    UserIdentityBO getByEmail(String namespace, String email);

    /**
     * 通过mobile查找用户身份信息。
     *
     * @param namespace not empty
     * @param mobile
     * @return
     */
    UserIdentityBO getByMobile(String namespace, String mobile);

}
