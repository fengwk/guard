package fun.fengwk.guard.aus.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 用户修改器。
 *
 * @author fengwk
 */
public class UserModifier {

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
     * 用户数据版本号。
     */
    private final Long version;

    /**
     * 构建用户修改器。
     *  @param userId not null
     * @param username
     * @param passwordDigest
     * @param mobile
     * @param email
     * @param properties not null
     * @param version
     */
    public UserModifier(String userId,
                        String username,
                        String passwordDigest,
                        String mobile,
                        String email,
                        Map<String, String> properties,
                        Long version) {
        Objects.requireNonNull(userId, "userId cannot be null");
        Objects.requireNonNull(properties, "properties cannot be null");

        this.userId = userId;
        this.username = username;
        this.passwordDigest = passwordDigest;
        this.mobile = mobile;
        this.email = email;
        this.properties = Collections.unmodifiableMap(properties);
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
     * 用户属性集。
     *
     * @return
     */
    public Map<String, String> getProperties() {
        return properties;
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
        UserModifier that = (UserModifier) o;
        return Objects.equals(userId, that.userId) && Objects.equals(username, that.username)
            && Objects.equals(passwordDigest, that.passwordDigest) && Objects.equals(mobile, that.mobile)
            && Objects.equals(email, that.email) && Objects.equals(properties, that.properties)
            && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, passwordDigest, mobile, email, properties, version);
    }

    @Override
    public String toString() {
        return "UserModifier{" +
            "userId='" + userId + '\'' +
            ", username='" + username + '\'' +
            ", passwordDigest='" + passwordDigest + '\'' +
            ", mobile='" + mobile + '\'' +
            ", email='" + email + '\'' +
            ", properties=" + properties +
            ", version=" + version +
            '}';
    }

    /**
     * 用户修改器建筑者。
     */
    public static class Builder {

        private final String userId;
        private final Long version;
        private String username;
        private String passwordDigest;
        private String mobile;
        private String email;
        private final Map<String, String> properties = new HashMap<>();

        public Builder(String userId) {
            this(userId, null);
        }

        /**
         *
         * @param userId not null
         * @param version
         */
        public Builder(String userId, Long version) {
            Objects.requireNonNull(userId, "userId cannot be null");

            this.userId = userId;
            this.version = version;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPasswordDigest(String passwordDigest) {
            this.passwordDigest = passwordDigest;
            return this;
        }

        public Builder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder putProperty(String property, String value) {
            properties.put(property, value);
            return this;
        }

        public UserModifier build() {
            return new UserModifier(userId, username, passwordDigest, mobile, email, properties, version);
        }

    }

}
