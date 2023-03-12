package fun.fengwk.guard.sus.core.service;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fun.fengwk.convention4j.common.idgen.NamespaceIdGenerator;
import fun.fengwk.convention4j.common.page.LitePage;
import fun.fengwk.convention4j.common.page.LitePageQuery;
import fun.fengwk.convention4j.common.page.Page;
import fun.fengwk.convention4j.common.page.PageQuery;
import fun.fengwk.guard.sus.core.dao.UserDAO;
import fun.fengwk.guard.sus.core.dao.UserIdentityDAO;
import fun.fengwk.guard.sus.core.dao.UserPropertiesDAO;
import fun.fengwk.guard.sus.core.model.UserDO;
import fun.fengwk.guard.sus.core.model.UserIdentityBO;
import fun.fengwk.guard.sus.core.model.UserPropertiesBO;
import fun.fengwk.guard.sus.share.model.UserCreatorDTO;
import fun.fengwk.guard.sus.share.model.UserDTO;
import fun.fengwk.guard.sus.share.model.UserIdentityDTO;
import fun.fengwk.guard.sus.share.model.UserModifierDTO;
import fun.fengwk.guard.sus.share.model.UserPropertiesDTO;
import fun.fengwk.guard.sus.share.model.UserQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fengwk
 */
@Service
public class UserServiceImpl implements UserService {

    private static final long INITIAL_VERSION = 0;

    /**
     * 配置是否允许动态初始化命名空间，默认允许。
     */
    private final boolean isAllowDynamicInitialize;

    private final NamespaceIdGenerator<Long> idGenerator;
    private final Gson gson;

    private final UserDAO userDAO;
    private final UserIdentityDAO userIdentityDAO;
    private final UserPropertiesDAO userPropertiesDAO;

    public UserServiceImpl(@Value("${simple-user-system.is-allow-dynamic-initialize:true}") boolean isAllowDynamicInitialize,
                           NamespaceIdGenerator<Long> idGenerator, Gson gson, UserDAO userDAO,
                           UserIdentityDAO userIdentityDAO, UserPropertiesDAO userPropertiesDAO) {
        this.isAllowDynamicInitialize = isAllowDynamicInitialize;
        this.idGenerator = idGenerator;
        this.gson = gson;
        this.userDAO = userDAO;
        this.userIdentityDAO = userIdentityDAO;
        this.userPropertiesDAO = userPropertiesDAO;
    }

    @Override
    public void initNamespaceIfNecessary(String namespace) {
        if (isAllowDynamicInitialize) {
            userDAO.createIfNotExists(namespace);
        }
    }

    @Override
    public UserDTO createUser(String namespace, UserCreatorDTO userCreatorDTO) {
        UserDO userDO = createUserDO(userCreatorDTO);
        userDAO.insert(namespace, userDO);
        return convert(userDO);
    }

    @Override
    public UserDTO modifyUser(String namespace, UserModifierDTO userModifierDTO) {
        UserDO userDO = userDAO.findByUserIdAndVersion(namespace, userModifierDTO.getUserId(), userModifierDTO.getVersion());
        if (userDO == null) {
            return null;
        }

        // 使用userModifierDTO中的非空数据覆盖原有数据
        if (userModifierDTO.getUsername() != null) {
            userDO.setUsername(userModifierDTO.getUsername());
        }
        if (userModifierDTO.getPasswordDigest() != null) {
            userDO.setPasswordDigest(userModifierDTO.getPasswordDigest());
        }
        if (userModifierDTO.getMobile() != null) {
            userDO.setMobile(userModifierDTO.getMobile());
        }
        if (userModifierDTO.getEmail() != null) {
            userDO.setEmail(userModifierDTO.getEmail());
        }
        if (userModifierDTO.getProperties() != null) {
            ImmutableMap<String, String> oldProperties = toProperties(userDO.getProperties());
            userDO.setProperties(toPropertiesJson(cover(oldProperties, userModifierDTO.getProperties())));
        }
        // 更新修改时间
        userDO.setGmtModified(LocalDateTime.now());

        // 进行更新，如果版本号变更，产生了并发更新直接返回null
        if (!userDAO.updateByIdSelectiveWithVersionIfNecessary(namespace, userDO)) {
            return null;
        }
        // 更新成功，增加数据版本号，并返回更新后的用户信息
        userDO.setVersion(userDO.getVersion() + 1);
        return convert(userDO);
    }

    @Override
    public UserDTO coverUser(String namespace, UserModifierDTO userModifierDTO) {
        UserDO userDO = userDAO.findByUserIdAndVersion(namespace , userModifierDTO.getUserId(), userModifierDTO.getVersion());
        if (userDO == null) {
            return null;
        }

        // 使用userModifierDTO中的数据覆盖原有数据
        userDO.setUsername(userModifierDTO.getUsername());
        userDO.setPasswordDigest(userModifierDTO.getPasswordDigest());
        userDO.setMobile(userModifierDTO.getMobile());
        userDO.setEmail(userModifierDTO.getEmail());
        userDO.setProperties(toPropertiesJson(userModifierDTO.getProperties()));
        // 更新修改时间
        userDO.setGmtModified(LocalDateTime.now());

        // 进行更新，如果版本号变更，产生了并发更新直接返回null
        if (!userDAO.updateByIdWithVersionIfNecessary(namespace, userDO)) {
            return null;
        }
        // 更新成功，增加数据版本号，并返回更新后的用户信息
        userDO.setVersion(userDO.getVersion() + 1);
        return convert(userDO);
    }

    @Override
    public boolean deleteUser(String namespace, long userId, Long version) {
        return userDAO.deleteByUserIdAndVersion(namespace, userId, version);
    }

    @Override
    public UserIdentityDTO getUserIdentityByUserId(String namespace, long userId) {
        UserIdentityBO userIdentityBO = userIdentityDAO.getById(namespace, userId);
        return convert(userIdentityBO);
    }

    @Override
    public UserIdentityDTO getUserIdentityByUsername(String namespace, String username) {
        UserIdentityBO userIdentityBO = userIdentityDAO.getByUsername(namespace, username);
        return convert(userIdentityBO);
    }

    @Override
    public UserIdentityDTO getUserIdentityByMobile(String namespace, String mobile) {
        UserIdentityBO userIdentityBO = userIdentityDAO.getByMobile(namespace, mobile);
        return convert(userIdentityBO);
    }

    @Override
    public UserIdentityDTO getUserIdentityByEmail(String namespace, String email) {
        UserIdentityBO userIdentityBO = userIdentityDAO.getByEmail(namespace, email);
        return convert(userIdentityBO);
    }

    @Override
    public UserPropertiesDTO getUserPropertiesByUserId(String namespace, long userId) {
        UserPropertiesBO userPropertiesBO = userPropertiesDAO.getById(namespace, userId);
        return convert(userPropertiesBO);
    }

    @Override
    public UserDTO getUserByUserId(String namespace, long userId) {
        UserDO userDO = userDAO.findByUserId(namespace, userId);
        return convert(userDO);
    }

    @Override
    public UserDTO getUserByUsername(String namespace, String username) {
        UserDO userDO = userDAO.findByUsername(namespace, username);
        return convert(userDO);
    }

    @Override
    public UserDTO getUserByMobile(String namespace, String mobile) {
        UserDO userDO = userDAO.findByMobile(namespace, mobile);
        return convert(userDO);
    }

    @Override
    public UserDTO getUserByEmail(String namespace, String email) {
        UserDO userDO = userDAO.findByEmail(namespace, email);
        return convert(userDO);
    }

    @Override
    public Page<UserDTO> pageUsers(String namespace, PageQuery pageQuery, UserQuery userQuery) {
        Page<UserDO> page = userDAO.page(namespace, pageQuery, userQuery);
        return page.map(this::convert);
    }

    @Override
    public LitePage<UserDTO> litePageUsers(String namespace, LitePageQuery litePageQuery, UserQuery userQuery) {
        LitePage<UserDO> litePage = userDAO.litePage(namespace, litePageQuery, userQuery);
        return litePage.map(this::convert);
    }

    private UserDO createUserDO(UserCreatorDTO userCreatorDTO) {
        if (userCreatorDTO == null) {
            return null;
        }

        LocalDateTime now = LocalDateTime.now();
        UserDO userDO = new UserDO();
        userDO.setUserId(idGenerator.next(UserDO.class));
        userDO.setGmtCreated(now);
        userDO.setGmtModified(now);
        userDO.setVersion(INITIAL_VERSION);
        userDO.setUsername(userCreatorDTO.getUsername());
        userDO.setPasswordDigest(userCreatorDTO.getPasswordDigest());
        userDO.setMobile(userCreatorDTO.getMobile());
        userDO.setEmail(userCreatorDTO.getEmail());
        userDO.setProperties(toPropertiesJson(userCreatorDTO.getProperties()));
        return userDO;
    }

    private UserDTO convert(UserDO userDO) {
        if (userDO == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userDO.getUserId());
        userDTO.setGmtCreate(userDO.getGmtCreated());
        userDTO.setGmtModified(userDO.getGmtModified());
        userDTO.setVersion(userDO.getVersion());
        userDTO.setUsername(userDO.getUsername());
        userDTO.setPasswordDigest(userDO.getPasswordDigest());
        userDTO.setMobile(userDO.getMobile());
        userDTO.setEmail(userDO.getEmail());
        userDTO.setProperties(toProperties(userDO.getProperties()));
        return userDTO;
    }

    private UserIdentityDTO convert(UserIdentityBO userIdentityBO) {
        if (userIdentityBO == null) {
            return null;
        }

        UserIdentityDTO userIdentityDTO = new UserIdentityDTO();
        userIdentityDTO.setUserId(userIdentityBO.getUserId());
        userIdentityDTO.setCreatedTime(userIdentityBO.getGmtCreated());
        userIdentityDTO.setModifiedTime(userIdentityBO.getGmtModified());
        userIdentityDTO.setVersion(userIdentityBO.getVersion());
        userIdentityDTO.setUsername(userIdentityBO.getUsername());
        userIdentityDTO.setPasswordDigest(userIdentityBO.getPasswordDigest());
        userIdentityDTO.setMobile(userIdentityBO.getMobile());
        userIdentityDTO.setEmail(userIdentityBO.getEmail());
        return userIdentityDTO;
    }

    private UserPropertiesDTO convert(UserPropertiesBO userPropertiesBO) {
        if (userPropertiesBO == null) {
            return null;
        }

        UserPropertiesDTO userPropertiesDTO = new UserPropertiesDTO();
        userPropertiesDTO.setUserId(userPropertiesBO.getUserId());
        userPropertiesDTO.setCreatedTime(userPropertiesBO.getGmtCreated());
        userPropertiesDTO.setModifiedTime(userPropertiesBO.getGmtModified());
        userPropertiesDTO.setVersion(userPropertiesBO.getVersion());
        userPropertiesDTO.setProperties(toProperties(userPropertiesBO.getProperties()));
        return userPropertiesDTO;
    }

    private ImmutableMap<String, String> toProperties(String propertiesJson) {
        return gson.fromJson(propertiesJson,
            new TypeToken<ImmutableMap<String, String>>() {}.getType());
    }

    private String toPropertiesJson(Map<String, String> properties) {
        return gson.toJson(properties);
    }

    /**
     * 使用modifier修改oldMap：
     * 1、如果modifier中存在oldMap不存在的key则新增。
     * 2、如果modifier中存在oldMap存在的key则覆盖。
     * 3、其它oldMap中的key都保留。
     *
     * @param oldMap
     * @param modifier
     * @return
     */
    private Map<String, String> cover(Map<String, String> oldMap, Map<String, String> modifier) {
        if (oldMap == null) {
            return modifier;
        }
        if (modifier == null) {
            return oldMap;
        }

        Map<String, String> map = new HashMap<>();
        map.putAll(oldMap);
        map.putAll(modifier);
        return map;
    }

}
