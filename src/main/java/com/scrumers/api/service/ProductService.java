package com.scrumers.api.service;

import java.util.List;

import com.scrumers.model.PlotData;
import com.scrumers.model.Product;
import com.scrumers.model.ProductView;

public interface ProductService {

    void addedProductOwner(Long pid, Long uid);

    Product getProduct(Long id);

    void saveProduct(Product p);

    void updatePriorityInPS(Long[] ids);

    void saveProduct(Product p, Long oid, Long uid);

    void deleteProduct(Long id);

    void deleteProductByOwner(Long[] id);

    void deleteProductByOwner(Long id);

    void deleteProductByMember(Long id, Long uid);

    void deleteTeamFromProduct(Long tid, Long pid);

    List<Product> getProducts();

    List<Product> getProductsByIterationId(Long iid);

    List<ProductView> getProductsViewByIterationId(Long iid);

    List<Product> getProductsByStoryId(Long sid);

    List<ProductView> getProductsViewByStoryId(Long sid);

    List<Product> getAllProductsByUserId(Long uid);

    List<ProductView> getAllProductsViewByUserId(Long uid);

    List<Product> getProductsByUserIdAndOrganizationId(Long uid, Long oid);

    List<ProductView> getProductsViewByUserIdAndOrganizationId(Long uid,
            Long oid);

    void addStoryToAProduct(Long pid, Long sid);

    void addIterationToAProduct(Long pid, Long iid);

    void addNewTeam(Long pid, Long tid);

    List<ProductView> getProductsView();

    List<PlotData> getProductPlotData1(Long pid);
    
    List<PlotData> getProductPlotData2(Long pid);
}