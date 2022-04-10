package fun.fengwk.guard.core.dao;

import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.convention4j.api.page.Pages;
import fun.fengwk.guard.core.mapper.PermissionMapper;
import fun.fengwk.guard.core.model.PermissionDO;

import java.util.List;

/**
 * @author fengwk
 */
public class PermissionDAOImpl extends BaseNamespaceCapabilityDAO implements PermissionDAO {

    private final PermissionMapper permissionMapper;

    public PermissionDAOImpl(String namespace, PermissionMapper permissionMapper) {
        super(namespace);
        this.permissionMapper = permissionMapper;
    }

    @Override
    public void createIfNotExists() {
        permissionMapper.createIfNotExists(getNamespace());
    }

    @Override
    public void dropIfExists() {
        permissionMapper.dropIfExists(getNamespace());
    }

    @Override
    public void insert(PermissionDO permissionDO) {
        permissionMapper.insert(getNamespace(), permissionDO);
    }

    @Override
    public boolean deleteByPermissionId(long permissionId) {
        return permissionMapper.deleteByPermissionId(getNamespace(), permissionId) > 0;
    }

    @Override
    public boolean updateByPermissionId(PermissionDO permissionDO) {
        return permissionMapper.updateByPermissionId(getNamespace(), permissionDO) > 0;
    }

    @Override
    public PermissionDO findByPermissionId(long permissionId) {
        return permissionMapper.findByPermissionId(getNamespace(), permissionId);
    }

    @Override
    public Page<PermissionDO> page(PageQuery pageQuery, String permissionPrefix) {
        List<PermissionDO> results = permissionMapper.page(getNamespace(), pageQuery.getOffset(), pageQuery.getLimit(), permissionPrefix);
        long totalCount = permissionMapper.count(getNamespace(), permissionPrefix);
        return Pages.page(pageQuery, results, totalCount);
    }

}
