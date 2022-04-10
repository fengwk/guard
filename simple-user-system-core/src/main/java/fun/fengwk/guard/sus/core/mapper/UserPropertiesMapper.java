package fun.fengwk.guard.sus.core.mapper;

import fun.fengwk.guard.sus.core.model.UserPropertiesBO;
import org.apache.ibatis.annotations.Param;

/**
 * @author fengwk
 */
public interface UserPropertiesMapper {

    /**
     *
     * @param namespace
     * @param userId
     * @return
     */
    UserPropertiesBO findByUserId(@Param("namespace") String namespace, @Param("userId") long userId);

}
