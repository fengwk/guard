package fun.fengwk.guard.sus.core.service;

import fun.fengwk.convention4j.api.page.LitePage;
import fun.fengwk.convention4j.api.page.LitePageQuery;
import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.guard.sus.share.model.UserCreatorDTO;
import fun.fengwk.guard.sus.share.model.UserDTO;
import fun.fengwk.guard.sus.share.model.UserIdentityDTO;
import fun.fengwk.guard.sus.share.model.UserModifierDTO;
import fun.fengwk.guard.sus.share.model.UserPropertiesDTO;
import fun.fengwk.guard.sus.share.model.UserQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author fengwk
 */
public interface UserService {

    /**
     * 如果制定命名空间还未初始化过，那么就初始化命名空间。
     *
     * @param namespace
     */
    void initNamespaceIfNecessary(@NotEmpty String namespace);

    /**
     * 创建用户。
     *
     * @param namespace
     * @param userCreatorDTO
     * @return
     */
    UserDTO createUser(@NotEmpty String namespace, @NotNull @Valid UserCreatorDTO userCreatorDTO);

    /**
     * 修改指定userModifier.userId的用户，并返回修改后的用户。
     * 另外如果指定了userModifier.version，那么修改时将比对version。
     * 注意，修改只会变更userModifier中非null的值。
     * 如果修改用户失败，可能是用户已不存在或者数据版本号不一致，返回null值。
     *
     * @param namespace
     * @param userModifierDTO
     * @return
     */
    UserDTO modifyUser(@NotEmpty String namespace, @NotNull @Valid UserModifierDTO userModifierDTO);

    /**
     * 覆盖指定userModifier.userId的用户，并返回修改后的用户。
     * 另外如果指定了userModifier.version，那么修改时将比对version。
     * 注意，覆盖会以为userModifier中的值为准修改原有用户，如果其中包含null值也会覆盖原值。
     * 如果修改用户失败，可能是用户已不存在或者数据版本号不一致，返回null值。
     *
     * @param namespace
     * @param userModifierDTO
     * @return
     */
    UserDTO coverUser(@NotEmpty String namespace, @NotNull @Valid UserModifierDTO userModifierDTO);

    /**
     * 通过用户id和数据版本号删除用户，如果成功删除返回true，否则返回false。
     *
     * @param namespace
     * @param userId
     * @param version 如果为null，表示删除时不考虑version信息。
     * @return
     */
    boolean deleteUser(@NotEmpty String namespace, long userId, Long version);

    /**
     * 通过指定的userId获取用户身份信息。
     *
     * @param namespace
     * @param userId
     * @return
     */
    UserIdentityDTO getUserIdentityByUserId(@NotEmpty String namespace, long userId);

    /**
     * 通过指定的username获取用户身份信息。
     *
     * @param namespace
     * @param username
     * @return
     */
    UserIdentityDTO getUserIdentityByUsername(@NotEmpty String namespace, @NotEmpty String username);

    /**
     * 通过指定的mobile获取用户身份信息。
     *
     * @param namespace
     * @param mobile
     * @return
     */
    UserIdentityDTO getUserIdentityByMobile(@NotEmpty String namespace, @NotEmpty String mobile);

    /**
     * 通过指定的email获取用户身份信息。
     *
     * @param namespace
     * @param email
     * @return
     */
    UserIdentityDTO getUserIdentityByEmail(@NotEmpty String namespace, @NotEmpty String email);

    /**
     * 通过指定的userId获取用户属性集合。
     *
     * @param namespace
     * @param userId
     * @return
     */
    UserPropertiesDTO getUserPropertiesByUserId(@NotEmpty String namespace, long userId);

    /**
     * 通过指定的userId获取用户。
     *
     * @param namespace
     * @param userId
     * @return
     */
    UserDTO getUserByUserId(@NotEmpty String namespace, long userId);

    /**
     * 通过指定的username获取用户。
     *
     * @param namespace
     * @param username
     * @return
     */
    UserDTO getUserByUsername(@NotEmpty String namespace, @NotEmpty String username);

    /**
     * 通过指定的mobile获取用户。
     *
     * @param namespace
     * @param mobile
     * @return
     */
    UserDTO getUserByMobile(@NotEmpty String namespace, @NotEmpty String mobile);

    /**
     * 通过指定的email获取用户。
     *
     * @param namespace
     * @param email
     * @return
     */
    UserDTO getUserByEmail(@NotEmpty String namespace, @NotEmpty String email);

    /**
     * 分页查询用户视图。
     *
     * @param namespace
     * @param pageQuery
     * @param userQuery 如果为空表示不进行条件查询。
     * @return
     */
    Page<UserDTO> pageUsers(@NotEmpty String namespace, @NotNull PageQuery pageQuery, UserQuery userQuery);

    /**
     * 简化的分页查询用户视图。
     *
     * @param namespace
     * @param litePageQuery
     * @param userQuery 如果为空表示不进行条件查询。
     * @return
     */
    LitePage<UserDTO> litePageUsers(@NotEmpty String namespace, @NotNull LitePageQuery litePageQuery, UserQuery userQuery);

}
