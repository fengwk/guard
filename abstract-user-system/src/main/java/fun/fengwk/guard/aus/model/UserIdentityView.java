package fun.fengwk.guard.aus.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 用户身份信息不可变视图。
 *
 * @author fengwk
 */
public class UserIdentityView {

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
     * 构造用户身份对象。
     *  @param userId not null
     * @param username
     * @param passwordDigest
     * @param mobile
     * @param email
     * @param createdTime
     * @param modifiedTime
     * @param version
     */
    public UserIdentityView(String userId,
                            String username,
                            String passwordDigest,
                            String mobile,
                            String email,
                            LocalDateTime createdTime,
                            LocalDateTime modifiedTime,
                            Long version) {
        Objects.requireNonNull(userId, "userId cannot be null");

        this.userId = userId;
        this.username = username;
        this.passwordDigest = passwordDigest;
        this.mobile = mobile;
        this.email = email;
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
        UserIdentityView that = (UserIdentityView) o;
        return Objects.equals(userId, that.userId) && Objects.equals(username, that.username)
            && Objects.equals(passwordDigest, that.passwordDigest) && Objects.equals(mobile, that.mobile)
            && Objects.equals(email, that.email) && Objects.equals(createdTime, that.createdTime)
            && Objects.equals(modifiedTime, that.modifiedTime) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, passwordDigest, mobile, email, createdTime, modifiedTime, version);
    }

    @Override
    public String toString() {
        return "UserIdentityView{" +
            "userId=" + userId +
            ", username='" + username + '\'' +
            ", passwordDigest='" + passwordDigest + '\'' +
            ", mobile='" + mobile + '\'' +
            ", email='" + email + '\'' +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", version=" + version +
            '}';
    }

}
