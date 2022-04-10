package fun.fengwk.guard.aus.model;

import java.util.Objects;

/**
 * 用户查询器。
 *
 * @author fengwk
 */
public class UserQuery {

    /**
     * 部分用户名信息。
     */
    private final String usernamePrefix;

    /**
     * 部分手机号信息。
     */
    private final String mobilePrefix;

    /**
     * 部分邮箱信息。
     */
    private final String emailPrefix;

    /**
     * 构建用户查询器。
     *
     * @param usernamePrefix
     * @param mobilePrefix
     * @param emailPrefix
     */
    public UserQuery(String usernamePrefix,
                     String mobilePrefix,
                     String emailPrefix) {
        this.usernamePrefix = usernamePrefix;
        this.mobilePrefix = mobilePrefix;
        this.emailPrefix = emailPrefix;
    }

    /**
     * 获取部分用户名信息。
     *
     * @return
     */
    public String getUsernamePrefix() {
        return usernamePrefix;
    }

    /**
     * 获取部分手机号信息。
     *
     * @return
     */
    public String getMobilePrefix() {
        return mobilePrefix;
    }

    /**
     * 获取部分邮箱信息。
     *
     * @return
     */
    public String getEmailPrefix() {
        return emailPrefix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserQuery userQuery = (UserQuery) o;
        return Objects.equals(usernamePrefix, userQuery.usernamePrefix)
            && Objects.equals(mobilePrefix, userQuery.mobilePrefix)
            && Objects.equals(emailPrefix, userQuery.emailPrefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usernamePrefix, mobilePrefix, emailPrefix);
    }

    @Override
    public String toString() {
        return "UserQuery{" +
            "usernamePrefix='" + usernamePrefix + '\'' +
            ", mobilePrefix='" + mobilePrefix + '\'' +
            ", emailPrefix='" + emailPrefix + '\'' +
            '}';
    }

    /**
     * 用户查询器建筑者。
     */
    public static class Builder {

        private String usernamePrefix;
        private String mobilePrefix;
        private String emailPrefix;

        public Builder setUsernamePrefix(String usernamePrefix) {
            this.usernamePrefix = usernamePrefix;
            return this;
        }

        public Builder setMobilePrefix(String mobilePrefix) {
            this.mobilePrefix = mobilePrefix;
            return this;
        }

        public Builder setEmailPrefix(String emailPrefix) {
            this.emailPrefix = emailPrefix;
            return this;
        }

        public UserQuery build() {
            return new UserQuery(usernamePrefix, mobilePrefix, emailPrefix);
        }

    }

}
