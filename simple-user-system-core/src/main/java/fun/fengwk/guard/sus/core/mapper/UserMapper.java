package fun.fengwk.guard.sus.core.mapper;

import fun.fengwk.guard.sus.core.model.UserDO;
import fun.fengwk.guard.sus.share.model.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fengwk
 */
public interface UserMapper {

    /**
     *
     * @param namespace
     * @return
     */
    void createIfNotExists(String namespace);

    /**
     *
     * @param namespace not null
     * @param userDO not null
     * @return
     */
    int insert(@Param("namespace") String namespace, @Param("userDO") UserDO userDO);

    /**
     * cas version
     *
     * @param namespace not null
     * @param userDO not null
     * @return
     */
    int updateByUserIdWithVersionIfNecessary(@Param("namespace") String namespace, @Param("userDO") UserDO userDO);

    /**
     * cas version
     *
     * @param namespace not null
     * @param userDO not null
     * @return
     */
    int updateByUserIdSelectiveWithVersionIfNecessary(@Param("namespace") String namespace, @Param("userDO") UserDO userDO);

    /**
     *
     * @param namespace not null
     * @param userId
     * @return
     */
    int deleteByUserIdAndVersion(@Param("namespace") String namespace, @Param("userId") long userId, @Param("version") Long version);

    /**
     *
     * @param namespace not null
     * @param userId
     * @param version
     * @return
     */
    UserDO findByUserIdAndVersion(@Param("namespace") String namespace, @Param("userId") long userId, @Param("version") Long version);

    /**
     *
     * @param namespace not null
     * @param username
     * @return
     */
    UserDO findByUsername(@Param("namespace") String namespace, @Param("username") String username);

    /**
     *
     * @param namespace not null
     * @param email
     * @return
     */
    UserDO findByEmail(@Param("namespace") String namespace, @Param("email") String email);

    /**
     *
     * @param namespace not null
     * @param mobile
     * @return
     */
    UserDO findByMobile(@Param("namespace") String namespace, @Param("mobile") String mobile);

    /**
     *
     * @param namespace not null
     * @param offset
     * @param limit
     * @param userQuery
     * @return
     */
    List<UserDO> page(@Param("namespace") String namespace, @Param("offset") long offset, @Param("limit") long limit, @Param("userQuery") UserQuery userQuery);

    /**
     *
     * @param namespace not null
     * @param userQuery
     * @return
     */
    long count(@Param("namespace") String namespace, @Param("userQuery") UserQuery userQuery);

}
