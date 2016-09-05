package com.scrumers.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.scrumers.api.dao.OrganizationDao;
import com.scrumers.api.dao.ProductDao;
import com.scrumers.api.dao.TeamDao;
import com.scrumers.api.service.ProductService;
import com.scrumers.model.PlotData;
import com.scrumers.model.Product;
import com.scrumers.model.ProductView;
import com.scrumers.model.Team;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    private OrganizationDao organizationDao;

    private TeamDao teamDao;

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Override
    public void saveProduct(Product p) {
        if (p.getId() == null) {
            productDao.create(p);
        } else {
            productDao.update(p);
        }
    }

    @Override
    public void saveProduct(Product p, Long oid, Long uid) {
        if (p.getId() == null) {
            Long pid = productDao.selectId();
            p.setId(pid);
            productDao.createWithUserId(p);
            // productDao.addUserToAProduct(uid, pid);
            organizationDao.addProductToAnOrganization(oid, pid);
        } else {
            productDao.update(p);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        if (id != null) {
            productDao.delete(id);
        }
    }

    @Override
    public void deleteProductByOwner(Long[] id) {
        if (id != null)
            for (Long pid : id) {
                /*
                 * productDao.deleteFromProductStoryByProductId(pid);
                 * productDao.deleteFromProductIterationByProductId(pid);
                 * productDao.deleteFromUsersProductByProductId(pid);
                 * organizationDao
                 * .deleteFromOrganizationProductByProductId(pid);
                 */
                productDao.delete(pid);
            }
    }

    @Override
    public void deleteProductByOwner(Long pid) {
        if (pid != null)
            productDao.delete(pid);
    }

    @Override
    public void deleteProductByMember(Long pid, Long uid) {
        if (pid != null) {
            List<Team> teams = teamDao.readByProductId(pid);

            for (Team t : teams) {
                teamDao.deleteMemberFromTeam(uid, t.getId());
            }
        }
    }

    @Override
    public List<Product> getProducts() {
        return productDao.readAll();
    }

    @Override
    public List<ProductView> getProductsView() {
        return productDao.readAllView();
    }

    @Override
    public Product getProduct(Long id) {
        return productDao.read(id);
    }

    @Override
    public List<Product> getProductsByIterationId(Long iid) {
        return productDao.readByIterationId(iid);
    }

    @Override
    public List<ProductView> getProductsViewByIterationId(Long iid) {
        return productDao.readViewByIterationId(iid);
    }

    @Override
    public List<Product> getProductsByStoryId(Long sid) {
        return productDao.readByStoryId(sid);
    }

    @Override
    public List<ProductView> getProductsViewByStoryId(Long sid) {
        return productDao.readViewByStoryId(sid);
    }

    @Override
    public List<Product> getAllProductsByUserId(Long uid) {
        return productDao.readAllByUserId(uid);
    }

    @Override
    public List<ProductView> getAllProductsViewByUserId(Long uid) {
        return productDao.readAllViewByUserId(uid);
    }

    @Override
    public List<Product> getProductsByUserIdAndOrganizationId(Long uid, Long oid) {
        return productDao.readByUserIdAndOrganizationId(uid, oid);
    }

    @Override
    public List<ProductView> getProductsViewByUserIdAndOrganizationId(Long uid,
            Long oid) {
        return productDao.readViewByUserIdAndOrganizationId(uid, oid);
    }

    @Override
    public void addStoryToAProduct(Long pid, Long sid) {
        productDao.addStoryToAProduct(pid, sid);
    }

    @Override
    public void addIterationToAProduct(Long pid, Long iid) {
        productDao.addIterationToAProduct(pid, iid);
    }

    @Override
    public void updatePriorityInPS(Long[] ids) {
        Long n = 0L;
        for (Long id : ids) {
            productDao.updatePriorityInPS(id, ++n);
        }

    }

    @Override
    public void addedProductOwner(Long pid, Long uid) {
        if (pid != null && uid != null)
            productDao.addedProductOwner(pid, uid);
    }

    @Override
    public void addNewTeam(Long pid, Long tid) {
        if (pid != null && tid != null) {
            boolean flag = false;
            List<Team> teams = teamDao.readByProductId(pid);

            for (Team t : teams)
                if (t.getId() == tid)
                    flag = true;

            if (!flag)
                teamDao.addProductToATeam(tid, pid);
        }
    }

    @Override
    public void deleteTeamFromProduct(Long tid, Long pid) {
        if (pid != null && tid != null) {
            teamDao.deleteFromTeamProductByTeamIdAndProductId(tid, pid);
        }
    }

    @Override
    public List<PlotData> getProductPlotData1(Long pid) {
        List<PlotData> data = null;
        List<PlotData> data1 = new ArrayList<PlotData>();
        if (pid != null) {
            Calendar c = Calendar.getInstance();
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);
            Long h = 0L;

            data = productDao.readProductDiadgramData1(pid);

            if (data.size() > 0) {
                Calendar scal = Calendar.getInstance();
                scal.setTime(data.get(0).getDate());

                for (PlotData pd : data) {
                    c.setTime(pd.getDate());

                    while (scal.compareTo(c) < 0) {
                        data1.add(new PlotData(h, scal.getTime()));
                        scal.add(Calendar.DAY_OF_YEAR, 1);
                    }

//                    if (scal.compareTo(c) == 0) {
//                        h = h + pd.getHours() - pd.getDelHours();
//                        data1.add(new PlotData(h, scal.getTime()));
//                    }
                    scal.add(Calendar.DAY_OF_YEAR, 1);

                }
            }
        }
        return data1;
    }

    @Override
    public List<PlotData> getProductPlotData2(Long pid) {
        List<PlotData> data = null;
        List<PlotData> data1 = new ArrayList<PlotData>();
        if (pid != null) {

            Calendar c = Calendar.getInstance();
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);
            Long h = 0L;
            data = productDao.readProductDiadgramData2(pid);

            if (data.size() > 0) {
                Calendar scal = Calendar.getInstance();
                scal.setTime(data.get(0).getDate());

                for (PlotData pd : data) {
                    c.setTime(pd.getDate());

                    while (scal.compareTo(c) < 0) {
                        data1.add(new PlotData(h, scal.getTime()));
                        scal.add(Calendar.DAY_OF_YEAR, 1);
                    }

//                    if (scal.compareTo(c) == 0) {
//                        h = h + pd.getHours();
//                        data1.add(new PlotData(h, scal.getTime()));
//                    }
                    scal.add(Calendar.DAY_OF_YEAR, 1);
                }
            }
        }
        return data1;
    }
}
