package fun.fengwk.guard.sus.core.dao;

import fun.fengwk.guard.sus.core.model.UserPropertiesBO;

/**
 * @author fengwk
 */
public interface UserPropertiesDAO {

    /**
     * 通过id查找用户属性集。
     *
     * @param namespace not empty
     * @param id
     * @return
     */
    UserPropertiesBO getById(String namespace, long id);

}
