package fun.fengwk.guard.aus.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * 用户属性集合的不可变视图。
 *
 * @author fengwk
 */
public class UserPropertiesView {

    /**
     * 当前用户属性集所属用户唯一标识。
     */
    private final String userId;

    /**
     * 用户属性集信息。
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
     * 构造用户信息集对象。
     * @param userId not null
     * @param properties not null.
     * @param createdTime
     * @param modifiedTime
     * @param version
     */
    public UserPropertiesView(String userId,
                              Map<String, String> properties,
                              LocalDateTime createdTime,
                              LocalDateTime modifiedTime,
                              Long version) {
        Objects.requireNonNull(userId, "userId cannot be null");
        Objects.requireNonNull(properties, "properties cannot be null");

        this.userId = userId;
        this.properties = Collections.unmodifiableMap(properties);
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.version = version;
    }

    /**
     * 获取当前用户属性集所属用户唯一标识。
     *
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 获取用户属性集信息。
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
        UserPropertiesView that = (UserPropertiesView) o;
        return Objects.equals(userId, that.userId) && Objects.equals(properties, that.properties)
            && Objects.equals(createdTime, that.createdTime) && Objects.equals(modifiedTime, that.modifiedTime)
            && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, properties, createdTime, modifiedTime, version);
    }

    @Override
    public String toString() {
        return "UserPropertiesView{" +
            "userId=" + userId +
            ", properties=" + properties +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", version=" + version +
            '}';
    }

}
