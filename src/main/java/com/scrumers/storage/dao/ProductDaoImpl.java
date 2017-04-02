package com.scrumers.storage.dao;

import java.util.List;
import com.google.common.collect.ImmutableMap;
import com.scrumers.api.dao.ProductDao;
import com.scrumers.model.PlotData;
import com.scrumers.model.Product;
import com.scrumers.model.ProductView;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class ProductDaoImpl extends SqlSessionDaoSupport implements ProductDao {

    @Override
    public void create(Product p) {
        getSqlSession().insert("Product.create", p);
    }

    @Override
    public Product read(Long id) {
        return getSqlSession().selectOne("Product.read", id);
    }

    @Override
    public List<Product> readAll() {
        return getSqlSession().selectList("Product.readAll");
    }

    @Override
    public List<ProductView> readAllView() {
        return getSqlSession().selectList("Product.readAllView");
    }

    @Override
    public List<Product> readByStoryId(Long sid) {
        return getSqlSession().selectList("Product.readByStoryId", sid);
    }

    @Override
    public List<ProductView> readViewByStoryId(Long sid) {
        return getSqlSession().selectList("Product.readViewByStoryId", sid);
    }

    @Override
    public List<Product> readByIterationId(Long iid) {
        return getSqlSession().selectList("Product.readByIterationId", iid);
    }

    @Override
    public List<ProductView> readViewByIterationId(Long iid) {
        return getSqlSession().selectList("Product.readViewByIterationId", iid);
    }

    @Override
    public List<Product> readAllByUserId(Long uid) {
        return getSqlSession().selectList("Product.readAllByUserId", uid);
    }

    @Override
    public List<ProductView> readAllViewByUserId(Long uid) {
        return getSqlSession().selectList("Product.readAllViewByUserId", uid);
    }

    @Override
    public List<Product> readByUserIdAndOrganizationId(Long uid, Long oid) {
        return getSqlSession().selectList("Product.readByUserIdAndOrgaId", ImmutableMap.of("uid", uid, "oid", oid));
    }

    @Override
    public List<ProductView> readViewByUserIdAndOrganizationId(Long uid, Long oid) {
        return getSqlSession().selectList("Product.readViewByUserIdAndOrgId", ImmutableMap.of("uid", uid, "oid", oid));
    }

    @Override
    public void update(Product s) {
        getSqlSession().update("Product.update", s);
    }

    @Override
    public void delete(final Long id) {
        getSqlSession().delete("Product.delete", id);
    }

    @Override
    public void addStoryToAProduct(Long pid, Long sid) {
        getSqlSession().insert("Product.addStoryToAProduct", ImmutableMap.of("pid", pid, "sid", sid));
    }

    @Override
    public Long selectId() {
        return getSqlSession().selectOne("Product.selectId");
    }

    @Override
    public void updatePriorityInPS(Long sid, Long prid) {
        getSqlSession().update("Product.updatePriorityInPS", ImmutableMap.of("sid", sid, "prid", prid));
    }

    @Override
    public void addedProductOwner(Long pid, Long uid) {
        getSqlSession().update("Product.addedProductOwner", ImmutableMap.of("pid", pid, "uid", uid));
    }

    @Override
    public List<PlotData> readProductDiadgramData1(Long pid) {
        return getSqlSession().selectList("Product.readProductDiadgramData1", pid);
    }

    @Override
    public List<PlotData> readProductDiadgramData2(Long pid) {
        return getSqlSession().selectList("Product.readProductDiadgramData2", pid);
    }

    @Override
    public Long readAllDelHoursForToday(Long pid) {
        return getSqlSession().selectOne("Product.readAllDelHoursForToday", pid);
    }

    @Override
    public List<PlotData> readIterationDoneHours(Long pid) {
        return getSqlSession().selectList("Product.readIterationDoneHours", pid);
    }

}
