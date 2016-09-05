package com.scrumers.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import com.scrumers.api.service.UserService;
import net.sourceforge.cobertura.CoverageIgnore;

import org.apache.commons.configuration.Configuration;
import org.springframework.web.multipart.MultipartFile;

import com.scrumers.api.dao.IterationDao;
import com.scrumers.api.dao.OrganizationDao;
import com.scrumers.api.dao.ProductDao;
import com.scrumers.api.dao.RoleDao;
import com.scrumers.api.dao.UserDao;
import com.scrumers.model.Role;
import com.scrumers.model.TeamMember;
import com.scrumers.model.User;
import com.scrumers.util.ImageUtils;

public class UserServiceImpl implements UserService {

    private Configuration conf;

    private UserDao userDao;

    private RoleDao roleDao;

    private OrganizationDao organizationDao;

    private ProductDao productDao;

    private IterationDao iterationDao;

    private String prefixAvatar;

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public void setPrefixAvatar(String prefixAvatar) {
        this.prefixAvatar = prefixAvatar;
    }

    public void setUserDao(final UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoleDao(final RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setIterationDao(IterationDao iterationDao) {
        this.iterationDao = iterationDao;
    }

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void setProductDao(final ProductDao productDao) {
        this.productDao = productDao;
    }

    @CoverageIgnore
    private void saveAvatars(final User user, final MultipartFile file)
            throws IOException {
        File tmp = new File(prefixAvatar + File.separatorChar + user.getId()
                + ".dat");

        if (tmp.exists()) {
            tmp.delete();
        }

        BufferedImage image = ImageIO.read(file.getInputStream());
        image = ImageUtils.createResizedCopy1(image, false);
        // image = ImageUtils.createStandartImage(image,
        // conf.getString("imageSize"), conf.getString("imageFormate"));
        ImageIO.write(image, conf.getString("imageFormate"), tmp);

    }

    @CoverageIgnore
    public InputStream getImage(final Long id) {
        File tmp = new File(prefixAvatar + File.separatorChar + id + ".dat");

        InputStream is = null;

        if (tmp.exists()) {
            try {
                is = new FileInputStream(tmp);
            } catch (Exception e) {
            }
        }

        return is;
    }

    @Override
    public User getUser(final Long id) {
        return userDao.read(id);
    }

    @Override
    public User getUser(final String login) {
        return userDao.findByName(login);
    }

    @Override
    public String getActualOrganizationName(Long id) {
        return (organizationDao
                .read((userDao.read(id)).getActualOrganization())).getName();
    }

    @Override
    public String getActualProductName(Long pid) {
        return (productDao.read((userDao.read(pid)).getActualProduct()))
                .getName();
    }

    @Override
    public String getActualIterationName(Long iid) {
        return (iterationDao.read((userDao.read(iid)).getActualIteration()))
                .getName();
    }

    @Override
    public void saveUser(final User user) {
        if (user.getId() == null) {
            userDao.create(user);
        } else {
            userDao.update(user);
        }
    }

    public void saveUser(final User user, final Long[] rids, MultipartFile file) {
        saveUser(user);
        roleDao.deleteUserRoles(user.getId());
        if (rids != null) {
            for (Long rid : rids) {
                roleDao.addUserRole(user.getId(), rid);
            }
        }

        if (file != null)
            if (!file.isEmpty()) {
                try {
                    saveAvatars(user, file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public void deleteUser(final Long[] id) {
        if (id != null)
            for (Long i : id)
                userDao.delete(i);
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    public List<User> getUsers(final String name) {
        return userDao.find(name);
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> getUsersRoles(final Long id) {
        return roleDao.findByUserId(id);
    }

    @Override
    public Boolean chekUserPasswd(final User user) {
        return (userDao.findByPasswd(user) != null);
    }

    @Override
    public void saveUserPasswd(final User user) {
        userDao.updatePasswd(user);
    }

    @Override
    public void saveUserInfo(MultipartFile file, User user) {
        userDao.updateInfo(user);
        try {
            if (file.getSize() != 0) {
                saveAvatars(user, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUserInfo(User user) {
        userDao.updateInfo(user);
    }

    @Override
    public List<TeamMember> getUsersByTeam(Long tid) {
        return userDao.findByTeamId(tid);
    }

    @Override
    public List<Role> getTeamRoles() {
        return userDao.findAllTeamRoles();
    }

    @Override
    public List<User> readUsersByProductId(Long pid) {
        return userDao.readUsersByProductId(pid);
    }

}
