package fun.fengwk.guard.aus.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * 用户不可变视图。
 *
 * @author fengwk
 */
public class UserView {

    /**
     * 用户唯一标识。
     */
    private final String userId;

    /**
     * 用户名。
     */
    private final String username;

    /**
     * 密码摘要。
     */
    private final String passwordDigest;

    /**
     * 手机号。
     */
    private final String mobile;

    /**
     * 邮箱。
     */
    private final String email;

    /**
     * 用户属性集。
     */
    private final Map<String, String> properties;

    /**
     * 创建时间。
     */
    private final LocalDateTime createdTime;

    /**
     * 修改时间。
     */
    private final LocalDateTime modifiedTime;

    /**
     * 用户数据版本号。
     */
    private final Long version;

    /**
     * 构建用户视图。
     *
     * @param userId not null
     * @param username
     * @param passwordDigest
     * @param mobile
     * @param email
     * @param properties not null
     * @param createdTime
     * @param modifiedTime
     */
    public UserView(String userId,
                    String username,
                    String passwordDigest,
                    String mobile,
                    String email,
                    Map<String, String> properties,
                    LocalDateTime createdTime,
                    LocalDateTime modifiedTime,
                    Long version) {
        Objects.requireNonNull(userId, "userId cannot be null");
        Objects.requireNonNull(properties, "properties cannot be null");

        this.userId = userId;
        this.username = username;
        this.passwordDigest = passwordDigest;
        this.mobile = mobile;
        this.email = email;
        this.properties = Collections.unmodifiableMap(properties);
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.version = version;
    }

    /**
     * 获取用户唯一标识。
     *
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 获取用户名。
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * 获取密码摘要。
     *
     * @return
     */
    public String getPasswordDigest() {
        return passwordDigest;
    }

    /**
     * 获取手机号。
     *
     * @return
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 获取邮箱。
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * 获取用户属性集。
     *
     * @return
     */
    public Map<String, String> getProperties() {
        return properties;
    }

    /**
     * 获取创建时间。
     *
     * @return
     */
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    /**
     * 获取修改时间。
     *
     * @return
     */
    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    /**
     * 获取用户版本号。
     *
     * @return
     */
    public Long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserView userView = (UserView) o;
        return Objects.equals(userId, userView.userId) && Objects.equals(username, userView.username)
            && Objects.equals(passwordDigest, userView.passwordDigest) && Objects.equals(mobile, userView.mobile)
            && Objects.equals(email, userView.email) && Objects.equals(properties, userView.properties)
            && Objects.equals(createdTime, userView.createdTime) && Objects.equals(modifiedTime, userView.modifiedTime)
            && Objects.equals(version, userView.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, passwordDigest, mobile, email, properties, createdTime, modifiedTime, version);
    }

    @Override
    public String toString() {
        return "UserView{" +
            "userId=" + userId +
            ", username='" + username + '\'' +
            ", passwordDigest='" + passwordDigest + '\'' +
            ", mobile='" + mobile + '\'' +
            ", email='" + email + '\'' +
            ", properties=" + properties +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", version=" + version +
            '}';
    }

}
