package fun.fengwk.guard.core.mapper;

import fun.fengwk.automapper.annotation.AutoMapper;
import fun.fengwk.guard.core.model.ClientDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fengwk
 */
@AutoMapper
public interface ClientMapper {

    int insert(ClientDO clientDO);

    int deleteByClientId(String clientId);

    int updateByClientId(ClientDO clientDO);

    ClientDO findByClientId(String clientId);

    List<ClientDO> page(@Param("offset") long offset, @Param("limit") long limit, @Param("namePrefix") String namePrefix);

    long count(String namePrefix);

}
