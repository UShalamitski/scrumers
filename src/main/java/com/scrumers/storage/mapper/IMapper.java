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
public interface IMapper {

    void insert(@Param("id") Long id);

    void read(@Param("id") Long id);

    void update(@Param("id") Long id);

    void delete(@Param("id") Long id);

}
