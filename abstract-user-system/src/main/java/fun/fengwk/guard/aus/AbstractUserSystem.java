package fun.fengwk.guard.aus;

import fun.fengwk.convention4j.api.page.LitePage;
import fun.fengwk.convention4j.api.page.LitePageQuery;
import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.guard.aus.model.UserCreator;
import fun.fengwk.guard.aus.model.UserIdentityView;
import fun.fengwk.guard.aus.model.UserModifier;
import fun.fengwk.guard.aus.model.UserPropertiesView;
import fun.fengwk.guard.aus.model.UserQuery;
import fun.fengwk.guard.aus.model.UserView;
import fun.fengwk.guard.aus.util.PasswordDigester;

/**
 * 抽象用户系统。
 *
 * @author fengwk
 */
public interface AbstractUserSystem extends AutoCloseable {

    /**
     * 获取当前用户系统的密码摘要器。
     *
     * @return not null
     */
    PasswordDigester getPasswordDigester();

    /**
     * 创建一个用户，并返回创建后用户的不可变视图。
     *
     * @param userCreator not null
     * @return
     */
    UserView createUser(UserCreator userCreator);

    /**
     * 修改指定userModifier.userId的用户，并返回修改后用户的不可变视图。
     * 注意，修改只会变更userModifier中非null的值。
     * 如果修改用户失败，可能是用户已不存在或者数据版本号不一致，返回null值。
     *
     * @param userModifier not null
     * @return
     */
    UserView modifyUser(UserModifier userModifier);

    /**
     * 覆盖指定userModifier.userId的用户，并返回修改后用户的不可变视图。
     * 注意，覆盖会以为userModifier中的值为准修改原有用户，如果其中包含null值也会覆盖原值。
     * 如果修改用户失败，可能是用户已不存在或者数据版本号不一致，返回null值。
     *
     * @param userModifier not null
     * @return
     */
    UserView coverUser(UserModifier userModifier);

    /**
     * 通过用户唯一标识和数据版本号删除用户，如果成功删除返回true，否则返回false。
     *
     * @param userId not null
     * @param version 如果为null，表示删除时不考虑version信息。
     */
    boolean deleteUser(String userId, Long version);

    /**
     * 通过指定的userId获取用户身份信息不可变视图。
     *
     * @param userId not null
     * @return
     */
    UserIdentityView getUserIdentityViewByUserId(String userId);

    /**
     * 通过指定的username获取用户身份信息不可变视图。
     *
     * @param username not null
     * @return
     */
    UserIdentityView getUserIdentityViewByUsername(String username);

    /**
     * 通过指定的mobile获取用户身份信息不可变视图。
     *
     * @param mobile not null
     * @return
     */
    UserIdentityView getUserIdentityViewByMobile(String mobile);

    /**
     * 通过指定的email获取用户身份信息不可变视图。
     *
     * @param email not null
     * @return
     */
    UserIdentityView getUserIdentityViewByEmail(String email);

    /**
     * 通过指定的userId获取用户属性集合的不可变视图。
     *
     * @param userId not null
     * @return
     */
    UserPropertiesView getUserPropertiesViewByUserId(String userId);

    /**
     * 通过指定的userId获取用户不可变视图。
     *
     * @param userId not null
     * @return
     */
    UserView getUserViewByUserId(String userId);

    /**
     * 通过指定的username获取用户不可变视图。
     *
     * @param username not null
     * @return
     */
    UserView getUserViewByUsername(String username);

    /**
     * 通过指定的mobile获取用户不可变视图。
     *
     * @param mobile not null
     * @return
     */
    UserView getUserViewByMobile(String mobile);

    /**
     * 通过指定的email获取用户不可变视图。
     *
     * @param email not null
     * @return
     */
    UserView getUserViewByEmail(String email);

    /**
     * 分页查询用户视图。
     *
     * @param pageQuery not null.
     * @param userQuery 如果为null表示不进行条件查询，如果非空是否能进行条件查询取决于抽象用户系统的具体实现，
     *                  如果不支持相应查询将抛出错误码异常。
     * @return
     */
    Page<UserView> pageUserViews(PageQuery pageQuery, UserQuery userQuery);

    /**
     * 简化的分页查询用户视图。
     *
     * @param litePageQuery not null.
     * @param userQuery 如果为null表示不进行条件查询，如果非空是否能进行条件查询取决于抽象用户系统的具体实现，
     *                  如果不支持相应查询将抛出错误码异常。
     * @return
     */
    LitePage<UserView> litePageUserViews(LitePageQuery litePageQuery, UserQuery userQuery);

}
