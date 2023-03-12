package fun.fengwk.sus.aus.injvm;

import com.google.common.base.Preconditions;
import fun.fengwk.convention4j.common.page.LitePage;
import fun.fengwk.convention4j.common.page.LitePageQuery;
import fun.fengwk.convention4j.common.page.Page;
import fun.fengwk.convention4j.common.page.PageQuery;
import fun.fengwk.guard.aus.AbstractUserSystem;
import fun.fengwk.guard.aus.model.UserCreator;
import fun.fengwk.guard.aus.model.UserIdentityView;
import fun.fengwk.guard.aus.model.UserModifier;
import fun.fengwk.guard.aus.model.UserPropertiesView;
import fun.fengwk.guard.aus.model.UserQuery;
import fun.fengwk.guard.aus.model.UserView;
import fun.fengwk.guard.aus.util.DefaultPasswordDigester;
import fun.fengwk.guard.aus.util.HmacSHA256Digester;
import fun.fengwk.guard.aus.util.PasswordDigester;
import fun.fengwk.guard.sus.core.service.UserService;
import fun.fengwk.guard.sus.share.model.UserCreatorDTO;
import fun.fengwk.guard.sus.share.model.UserDTO;
import fun.fengwk.guard.sus.share.model.UserIdentityDTO;
import fun.fengwk.guard.sus.share.model.UserModifierDTO;
import fun.fengwk.guard.sus.share.model.UserPropertiesDTO;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author fengwk
 */
public class SimpleUserSystem implements AbstractUserSystem {

    private static final String SECRET_KEY = "CC4F4229CC0D0E581ABA2ED6FFB9107635BFD83A3A8846509252E7A691DB0E63";
    private static final PasswordDigester DIGESTER = new DefaultPasswordDigester(
        new HmacSHA256Digester(HmacSHA256Digester.deserializeSecretKey(SECRET_KEY)));

    private final String namespace;
    private final UserService userService;

    public SimpleUserSystem(String namespace, UserService userService) {
        Preconditions.checkArgument(!StringUtils.isEmpty(namespace), "namespace cannot be empty");
        Preconditions.checkNotNull(userService, "userService cannot be null");

        // 尝试初始化
        userService.initNamespaceIfNecessary(namespace);

        this.namespace = namespace;
        this.userService = userService;
    }

    @Override
    public PasswordDigester getPasswordDigester() {
        return DIGESTER;
    }

    @Override
    public UserView createUser(UserCreator userCreator) {
        Preconditions.checkNotNull(userCreator, "userCreator cannot be null");

        UserCreatorDTO userCreatorDTO = convert(userCreator);
        UserDTO userDTO = userService.createUser(namespace, userCreatorDTO);
        return convert(userDTO);
    }

    @Override
    public UserView modifyUser(UserModifier userModifier) {
        Preconditions.checkNotNull(userModifier, "userModifier cannot be null");

        UserModifierDTO userModifierDTO = convert(userModifier);
        UserDTO userDTO = userService.modifyUser(namespace, userModifierDTO);
        return convert(userDTO);
    }

    @Override
    public UserView coverUser(UserModifier userModifier) {
        Preconditions.checkNotNull(userModifier, "userModifier cannot be null");

        UserModifierDTO userModifierDTO = convert(userModifier);
        UserDTO userDTO = userService.coverUser(namespace, userModifierDTO);
        return convert(userDTO);
    }

    @Override
    public boolean deleteUser(String userId, Long version) {
        Preconditions.checkNotNull(userId, "userId cannot be null");

        return userService.deleteUser(namespace, Long.parseLong(userId), version);
    }

    @Override
    public UserIdentityView getUserIdentityViewByUserId(String userId) {
        Preconditions.checkNotNull(userId, "userId cannot be null");

        UserIdentityDTO userIdentityDTO = userService.getUserIdentityByUserId(namespace, Long.parseLong(userId));
        return convert(userIdentityDTO);
    }

    @Override
    public UserIdentityView getUserIdentityViewByUsername(String username) {
        Objects.requireNonNull(username);

        UserIdentityDTO userIdentityDTO = userService.getUserIdentityByUsername(namespace, username);
        return convert(userIdentityDTO);
    }

    @Override
    public UserIdentityView getUserIdentityViewByMobile(String mobile) {
        Objects.requireNonNull(mobile);

        UserIdentityDTO userIdentityDTO = userService.getUserIdentityByMobile(namespace, mobile);
        return convert(userIdentityDTO);
    }

    @Override
    public UserIdentityView getUserIdentityViewByEmail(String email) {
        Objects.requireNonNull(email);

        UserIdentityDTO userIdentityDTO = userService.getUserIdentityByEmail(namespace, email);
        return convert(userIdentityDTO);
    }

    @Override
    public UserPropertiesView getUserPropertiesViewByUserId(String userId) {
        Preconditions.checkNotNull(userId, "userId cannot be null");

        UserPropertiesDTO userPropertiesDTO = userService.getUserPropertiesByUserId(namespace, Long.parseLong(userId));
        return convert(userPropertiesDTO);
    }

    @Override
    public UserView getUserViewByUserId(String userId) {
        Preconditions.checkNotNull(userId, "userId cannot be null");

        UserDTO userDTO = userService.getUserByUserId(namespace, Long.parseLong(userId));
        return convert(userDTO);
    }

    @Override
    public UserView getUserViewByUsername(String username) {
        Objects.requireNonNull(username);

        UserDTO userDTO = userService.getUserByUsername(namespace, username);
        return convert(userDTO);
    }

    @Override
    public UserView getUserViewByMobile(String mobile) {
        Objects.requireNonNull(mobile);

        UserDTO userDTO = userService.getUserByMobile(namespace, mobile);
        return convert(userDTO);
    }

    @Override
    public UserView getUserViewByEmail(String email) {
        Objects.requireNonNull(email);

        UserDTO userDTO = userService.getUserByEmail(namespace, email);
        return convert(userDTO);
    }

    @Override
    public Page<UserView> pageUserViews(PageQuery pageQuery, UserQuery userQuery) {
        Objects.requireNonNull(pageQuery);

        Page<UserDTO> page = userService.pageUsers(namespace, pageQuery, convert(userQuery));
        return page.map(this::convert);
    }

    @Override
    public LitePage<UserView> litePageUserViews(LitePageQuery litePageQuery, UserQuery userQuery) {
        Objects.requireNonNull(litePageQuery);

        LitePage<UserDTO> litePage = userService.litePageUsers(namespace, litePageQuery, convert(userQuery));
        return litePage.map(this::convert);
    }

    @Override
    public void close() throws Exception {
        // nothing to do
    }

    private UserCreatorDTO convert(UserCreator userCreator) {
        if (userCreator == null) {
            return null;
        }

        UserCreatorDTO userCreatorDTO = new UserCreatorDTO();
        userCreatorDTO.setUsername(userCreator.getUsername());
        userCreatorDTO.setPasswordDigest(userCreator.getPasswordDigest());
        userCreatorDTO.setMobile(userCreator.getMobile());
        userCreatorDTO.setEmail(userCreator.getEmail());
        userCreatorDTO.setProperties(userCreator.getProperties());
        return userCreatorDTO;
    }

    private UserModifierDTO convert(UserModifier userModifier) {
        if (userModifier == null) {
            return null;
        }

        UserModifierDTO userModifierDTO = new UserModifierDTO();
        userModifierDTO.setUserId(Long.valueOf(userModifier.getUserId()));
        userModifierDTO.setUsername(userModifier.getUsername());
        userModifierDTO.setPasswordDigest(userModifier.getPasswordDigest());
        userModifierDTO.setMobile(userModifier.getMobile());
        userModifierDTO.setEmail(userModifier.getEmail());
        userModifierDTO.setProperties(userModifier.getProperties());
        userModifierDTO.setVersion(userModifier.getVersion());
        return userModifierDTO;
    }

    private UserIdentityView convert(UserIdentityDTO userIdentityDTO) {
        if (userIdentityDTO == null) {
            return null;
        }

        return new UserIdentityView(
            String.valueOf(userIdentityDTO.getUserId()),
            userIdentityDTO.getUsername(),
            userIdentityDTO.getPasswordDigest(),
            userIdentityDTO.getMobile(),
            userIdentityDTO.getEmail(),
            userIdentityDTO.getCreatedTime(),
            userIdentityDTO.getModifiedTime(),
            userIdentityDTO.getVersion()
        );
    }

    private UserPropertiesView convert(UserPropertiesDTO userPropertiesDTO) {
        if (userPropertiesDTO == null) {
            return null;
        }

        return new UserPropertiesView(
            String.valueOf(userPropertiesDTO.getUserId()),
            userPropertiesDTO.getProperties(),
            userPropertiesDTO.getCreatedTime(),
            userPropertiesDTO.getModifiedTime(),
            userPropertiesDTO.getVersion()
        );
    }

    private UserView convert(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        return new UserView(
            String.valueOf(userDTO.getUserId()),
            userDTO.getUsername(),
            userDTO.getPasswordDigest(),
            userDTO.getMobile(),
            userDTO.getEmail(),
            userDTO.getProperties(),
            userDTO.getGmtCreate(),
            userDTO.getGmtModified(),
            userDTO.getVersion()
        );
    }

    private fun.fengwk.guard.sus.share.model.UserQuery convert(UserQuery userQuery) {
        if (userQuery == null) {
            return null;
        }

        fun.fengwk.guard.sus.share.model.UserQuery retUserQuery = new fun.fengwk.guard.sus.share.model.UserQuery();
        retUserQuery.setUsernamePrefix(userQuery.getUsernamePrefix());
        retUserQuery.setMobilePrefix(userQuery.getMobilePrefix());
        retUserQuery.setEmailPredix(userQuery.getEmailPrefix());
        return retUserQuery;
    }

}
