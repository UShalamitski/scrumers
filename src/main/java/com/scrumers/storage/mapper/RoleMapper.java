package com.scrumers.storage.mapper;

import com.scrumers.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The make {@link Role} mapper.
 * Date: 06/14/2016
 *
 * @author Uladzislau_Shalamitski
 */
public interface RoleMapper {

    void add(@Param("uid") Long uid, @Param("rid") Long rid);

    List<Role> findByUserId(@Param("id") Long id);

    List<Role> findAll();

    List<Role> findAllTeamRoles();

    void delete(@Param("uid") Long id);

}
