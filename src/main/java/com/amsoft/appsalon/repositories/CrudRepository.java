package com.amsoft.appsalon.repositories;

import java.util.List;

public interface CrudRepository<T, K> {
    T save(T obj);

    T findById(K id);

    List<T> findAll();

    void deleteById(K id);
}
