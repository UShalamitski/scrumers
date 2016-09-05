package com.scrumers.api.dao;

public interface GenericDao<ID, T> extends Dao<T> {

    void create(T o);

    T read(ID id);

    void update(T o);

    void delete(ID id);
}
