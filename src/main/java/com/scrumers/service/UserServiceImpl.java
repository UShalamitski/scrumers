package com.scrumers.service;

import com.scrumers.api.dao.*;
import com.scrumers.api.service.UserService;
import com.scrumers.model.Role;
import com.scrumers.model.TeamMember;
import com.scrumers.model.User;
import com.scrumers.util.ImageUtils;
import net.sourceforge.cobertura.CoverageIgnore;
import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

public class UserServiceImpl implements UserService {

    @Autowired
    private Configuration conf;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private OrganizationDao organizationDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private IterationDao iterationDao;
    @Value("prefixAvatars")
    private String prefixAvatar;

    @CoverageIgnore
    private void saveAvatars(final User user, final MultipartFile file) throws IOException {
        File tmp = new File(prefixAvatar + File.separatorChar + user.getId() + ".dat");

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
        return organizationDao.read(userDao.read(id).getActualOrganization()).getName();
    }

    @Override
    public String getActualProductName(Long pid) {
        return productDao.read(userDao.read(pid).getActualProduct()).getName();
    }

    @Override
    public String getActualIterationName(Long iid) {
        return iterationDao.read(userDao.read(iid).getActualIteration()).getName();
    }

    @Override
    public void saveUser(final User user) {
        checkArgument(nonNull(user));
        if (nonNull(user.getId())) {
            userDao.create(user);
        } else {
            userDao.update(user);
        }
    }

    public void saveUser(final User user, final Long[] roles, MultipartFile file) {
        saveUser(user);
        roleDao.deleteUserRoles(user.getId());
        for (Long role : roles) {
            roleDao.addUserRole(user.getId(), role);
        }

        if (file != null && !file.isEmpty())
            try {
                saveAvatars(user, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void deleteUser(final Long[] userIds) {
        for (Long userId : userIds) {
            userDao.delete(userId);
        }
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
        return userDao.findByPasswd(user) != null;
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
