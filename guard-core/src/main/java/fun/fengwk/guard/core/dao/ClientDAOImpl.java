package fun.fengwk.guard.core.dao;

import fun.fengwk.convention4j.common.page.Page;
import fun.fengwk.convention4j.common.page.PageQuery;
import fun.fengwk.convention4j.common.page.Pages;
import fun.fengwk.guard.core.mapper.ClientMapper;
import fun.fengwk.guard.core.model.ClientDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author fengwk
 */
@Component
public class ClientDAOImpl implements ClientDAO {

    private final ClientMapper clientMapper;

    public ClientDAOImpl(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    @Override
    public void insert(ClientDO clientDO) {
        clientMapper.insert(clientDO);
    }

    @Override
    public boolean deleteByClientId(String clientId) {
        return clientMapper.deleteByClientId(clientId) > 0;
    }

    @Override
    public boolean updateByClientId(ClientDO clientDO) {
        return clientMapper.updateByClientId(clientDO) > 0;
    }

    @Override
    public ClientDO findByClientId(String clientId) {
        return clientMapper.findByClientId(clientId);
    }

    @Override
    public Page<ClientDO> page(PageQuery pageQuery, String namePrefix) {
        List<ClientDO> results = clientMapper.page(pageQuery.getOffset(), pageQuery.getLimit(), namePrefix);
        long totalCount = clientMapper.count(namePrefix);
        return Pages.page(pageQuery, results, totalCount);
    }

}
