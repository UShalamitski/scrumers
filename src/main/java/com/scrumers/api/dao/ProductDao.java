package com.scrumers.api.dao;

import java.util.List;

import com.scrumers.model.PlotData;
import com.scrumers.model.Product;
import com.scrumers.model.ProductView;

public interface ProductDao extends GenericDao<Long, Product> {

    Long selectId();

    List<Product> readAll();

    void createWithUserId(Product p);

    void updatePriorityInPS(Long sid, Long prid);

    Long readAllDelHoursForToday(Long pid);

    List<PlotData> readIterationDoneHours(Long pid);

    List<Product> readByIterationId(Long iid);

    List<ProductView> readViewByIterationId(Long iid);

    List<Product> readByStoryId(Long sid);

    List<ProductView> readViewByStoryId(Long sid);

    List<Product> readAllByUserId(Long uid);

    List<ProductView> readAllViewByUserId(Long uid);

    List<Product> readByUserIdAndOrganizationId(Long uid, Long oid);

    List<ProductView> readViewByUserIdAndOrganizationId(Long uid, Long oid);

    void addStoryToAProduct(Long pid, Long sid);

    void addIterationToAProduct(Long pid, Long iid);

    void addedProductOwner(Long pid, Long uid);

    List<ProductView> readAllView();

    List<PlotData> readProductDiadgramData1(Long pid);

    List<PlotData> readProductDiadgramData2(Long pid);
}
