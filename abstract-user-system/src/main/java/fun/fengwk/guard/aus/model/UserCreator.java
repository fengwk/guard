package fun.fengwk.guard.aus.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 用户创建器。
 *
 * @author fengwk
 */
public class UserCreator {

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
     * 构建用户创建器。
     *
     * @param username
     * @param passwordDigest
     * @param mobile
     * @param email
     * @param properties not null
     */
    public UserCreator(String username,
                       String passwordDigest,
                       String mobile,
                       String email,
                       Map<String, String> properties) {
        Objects.requireNonNull(properties, "properties cannot be null");

        this.username = username;
        this.passwordDigest = passwordDigest;
        this.mobile = mobile;
        this.email = email;
        this.properties = Collections.unmodifiableMap(properties);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreator that = (UserCreator) o;
        return Objects.equals(username, that.username) && Objects.equals(passwordDigest, that.passwordDigest)
            && Objects.equals(mobile, that.mobile) && Objects.equals(email, that.email)
            && Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, passwordDigest, mobile, email, properties);
    }

    @Override
    public String toString() {
        return "UserCreator{" +
            "username='" + username + '\'' +
            ", passwordDigest='" + passwordDigest + '\'' +
            ", mobile='" + mobile + '\'' +
            ", email='" + email + '\'' +
            ", properties=" + properties +
            '}';
    }

    /**
     * 用户创建器建筑者。
     */
    public static class Builder {

        private String username;
        private String passwordDigest;
        private String mobile;
        private String email;
        private Map<String, String> properties = new HashMap<>();

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

        public UserCreator build() {
            return new UserCreator(username, passwordDigest, mobile, email, properties);
        }

    }

}
