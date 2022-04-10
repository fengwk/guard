package fun.fengwk.guard.sus.core.mapper;

import fun.fengwk.guard.sus.core.model.UserIdentityBO;
import org.apache.ibatis.annotations.Param;

/**
 * @author fengwk
 */
public interface UserIdentityMapper {

    /**
     *
     * @param namespace
     * @param userId
     * @return
     */
    UserIdentityBO findByUserId(@Param("namespace") String namespace, @Param("userId") long userId);

    /**
     *
     * @param namespace
     * @param username
     * @return
     */
    UserIdentityBO findByUsername(@Param("namespace") String namespace, @Param("username") String username);

    /**
     *
     * @param namespace
     * @param email
     * @return
     */
    UserIdentityBO findByEmail(@Param("namespace") String namespace, @Param("email") String email);

    /**
     *
     * @param namespace
     * @param mobile
     * @return
     */
    UserIdentityBO findByMobile(@Param("namespace") String namespace, @Param("mobile") String mobile);

}
