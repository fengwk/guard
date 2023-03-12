package fun.fengwk.guard.sus.core.dao;

import fun.fengwk.convention4j.common.page.LitePage;
import fun.fengwk.convention4j.common.page.LitePageQuery;
import fun.fengwk.convention4j.common.page.Page;
import fun.fengwk.convention4j.common.page.PageQuery;
import fun.fengwk.guard.sus.core.model.UserDO;
import fun.fengwk.guard.sus.share.model.UserQuery;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author fengwk
 */
public interface UserDAO {

    /**
     * 如果不存在制定命名空间的用户表则进行创建。
     *
     * @param namespace
     */
    void createIfNotExists(@NotEmpty String namespace);

    /**
     * 向数据库中插入userDO。
     *
     * @param namespace
     * @param userDO
     * @return
     */
    void insert(@NotEmpty String namespace, @NotNull UserDO userDO);

    /**
     * 通过id，全量更新userDO，
     * 如果指定了version更新时将考虑版本号信息，如果版本号不匹配更新也会失败，
     * 如果有记录修改返回true，否则返回false。
     * 注意一旦修改成功，数据版本号就会增加1。
     *
     * @param namespace
     * @param userDO
     * @return
     */
    boolean updateByIdWithVersionIfNecessary(@NotEmpty String namespace, @NotNull UserDO userDO);

    /**
     * 通过id，更新userDO中非null字段，
     * 如果指定了version更新时将考虑版本号信息，如果版本号不匹配更新也会失败，
     * 如果有记录修改返回true，否则返回false。
     * 注意一旦修改成功，数据版本号就会增加1。
     *
     * @param namespace
     * @param userDO
     * @return
     */
    boolean updateByIdSelectiveWithVersionIfNecessary(@NotEmpty String namespace, @NotNull UserDO userDO);

    /**
     * 通过id和版本号删除记录，如果有记录被删除返回true，否则返回false。
     *
     * @param namespace not empty
     * @param id
     * @param version 如果版本号为null，将不考虑版本号信息
     * @return
     */
    boolean deleteByUserIdAndVersion(@NotEmpty String namespace, long id, Long version);

    /**
     * 通过id查找用户记录。
     *
     * @param namespace
     * @param id
     * @return
     */
    default UserDO findByUserId(@NotEmpty String namespace, long id) {
        return findByUserIdAndVersion(namespace, id, null);
    }

    /**
     * 通过id和版本号查找用户记录。
     *
     * @param namespace
     * @param id
     * @param version 如果版本号为null，将不考虑版本号信息
     * @return
     */
    UserDO findByUserIdAndVersion(@NotEmpty String namespace, long id, Long version);

    /**
     * 通过username查找用户记录。
     *
     * @param namespace
     * @param username
     * @return
     */
    UserDO findByUsername(@NotEmpty String namespace, @NotEmpty String username);

    /**
     * 通过email查找用户记录。
     *
     * @param namespace
     * @param email
     * @return
     */
    UserDO findByEmail(@NotEmpty String namespace, @NotEmpty String email);

    /**
     * 通过mobile查找用户记录。
     *
     * @param namespace
     * @param mobile
     * @return
     */
    UserDO findByMobile(@NotEmpty String namespace, @NotEmpty String mobile);

    /**
     * 分页查询用户记录。
     *
     * @param namespace
     * @param pageQuery
     * @param userQuery
     * @return
     */
    Page<UserDO> page(@NotEmpty String namespace, @NotNull PageQuery pageQuery, UserQuery userQuery);

    /**
     * 游标分页查询用户记录。
     *
     * @param namespace
     * @param litePageQuery
     * @param userQuery
     * @return
     */
    LitePage<UserDO> litePage(@NotEmpty String namespace, @NotNull LitePageQuery litePageQuery, UserQuery userQuery);

}
